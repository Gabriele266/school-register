package com.school.schoolregister.services.grade

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.exceptions.InvalidStudentReferenceException
import com.school.schoolregister.repositories.GradeRepository
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.student.StudentService
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
private class GradesServiceImpl(
    private val gradeRepository: GradeRepository,
    private val studentsService: StudentService,
    private val mongoTemplate: MongoTemplate
) : GradeService {

    @Throws(InvalidStudentReferenceException::class)
    override fun saveGrade(grade: Grade): Grade {
        val studentID = grade.studentID

        // Check if student reference is valid or throw exception
        studentsService.findStudentById(studentID) ?: throw InvalidStudentReferenceException(
            studentID,
            "GradeService",
            null
        )

        gradeRepository.save(grade)

        return grade
    }

    @Throws(InvalidStudentReferenceException::class)
    override fun saveGrades(gradesList: List<Grade>): List<Grade> {
        // Validate all grades
        gradesList.forEach {
            val studentID = it.studentID
            if (!studentsService.hasStudentWithId(studentID)) throw InvalidStudentReferenceException(
                studentID,
                "GradeService",
                null
            )
        }

        gradeRepository.saveAll(gradesList)

        return gradesList
    }

    override fun count(): Long = gradeRepository.count()

    override fun updateGrade(grade: Grade): UpdateResult<Grade> {
        val voteID = ObjectId(grade.id)

        val voteOriginal = gradeRepository.findById(voteID).orElse(null)

        return if (voteOriginal != null) {
            gradeRepository.deleteById(voteID)
            gradeRepository.save(grade)
            UpdateResult.successful(grade)
        } else UpdateResult.failed()
    }

    override fun removeGradeByID(gradeID: String): RemoveResult<Grade> {
        val vote: Grade? = gradeRepository.findById(ObjectId(gradeID)).orElse(null)
        return if (vote != null) {
            gradeRepository.deleteById(ObjectId(gradeID))

            RemoveResult.successful(vote)
        } else RemoveResult.failed("Invalid remove id")
    }

    override fun hasGradeWithID(gradeID: String): Boolean = gradeRepository.findById(ObjectId(gradeID)).isPresent

    override fun findGradeByID(gradeID: String): Grade? = gradeRepository.findById(ObjectId(gradeID)).orElse(null)

    override fun findAll(): List<Grade> = gradeRepository.findAll()

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