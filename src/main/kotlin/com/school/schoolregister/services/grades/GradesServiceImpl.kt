package com.school.schoolregister.services.grades

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.exceptions.InvalidStudentReferenceException
import com.school.schoolregister.repositories.VotesRepository
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.students.StudentsService
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class GradesServiceImpl(
    private val votesRepository: VotesRepository,
    private val studentsService: StudentsService,
    private val mongoTemplate: MongoTemplate
) : GradesService {

    @Throws(InvalidStudentReferenceException::class)
    override fun saveGrade(grade: Grade): Grade {
        val studentID = grade.studentID

        // Check if student reference is valid or throw exception
        studentsService.findStudentById(studentID) ?: throw InvalidStudentReferenceException(studentID, null)

        votesRepository.save(grade)

        return grade
    }

    override fun count(): Long = votesRepository.count()

    override fun updateGrade(grade: Grade): UpdateResult<Grade> {
        val voteID = ObjectId(grade.id)

        val voteOriginal = votesRepository.findById(voteID).orElse(null)

        return if (voteOriginal != null) {
            votesRepository.deleteById(voteID)
            votesRepository.save(grade)
            UpdateResult.successful(grade)
        } else UpdateResult.failed()
    }

    override fun removeGradeByID(gradeID: String): RemoveResult<Grade> {
        val vote: Grade? = votesRepository.findById(ObjectId(gradeID)).orElse(null)
        return if (vote != null) {
            votesRepository.deleteById(ObjectId(gradeID))

            RemoveResult.successful(vote)
        } else RemoveResult.failed("Invalid remove id")
    }

    override fun hasGradeWithID(gradeID: String): Boolean = votesRepository.findById(ObjectId(gradeID)).isPresent

    override fun findGradeByID(gradeID: String): Grade? = votesRepository.findById(ObjectId(gradeID)).orElse(null)

    override fun findAll(): List<Grade> = votesRepository.findAll()

    override fun findByStudentID(studentID: String): Array<Grade> {
        val query = Query()
        query.addCriteria(Criteria.where("studentID").`is`(studentID))

        val grades = mongoTemplate.find(query, Grade::class.java)

        return grades.toTypedArray()
    }

    override fun findByTeacherID(teacherID: String): Array<Grade> {
        val query = Query()

        query.addCriteria(Criteria.where("teacherID").`is`(teacherID))
        return mongoTemplate.find(query, Grade::class.java).toTypedArray()
    }
}