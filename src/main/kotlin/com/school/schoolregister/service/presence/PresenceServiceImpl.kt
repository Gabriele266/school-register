package com.school.schoolregister.service.presence

import com.school.schoolregister.domain.entities.Presence
import com.school.schoolregister.exceptions.InvalidStudentReferenceException
import com.school.schoolregister.repositories.PresenceRepository
import com.school.schoolregister.service.common.RemoveResult
import com.school.schoolregister.service.student.StudentService
import com.school.schoolregister.service.teacher.TeacherService
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
private class PresenceServiceImpl(
    private val presencesRepository: PresenceRepository,
    private val studentsService: StudentService,
    private val teachersService: TeacherService,
    private val mongoTemplate: MongoTemplate
) : PresenceService {
    override fun findAll(): List<Presence> =
        presencesRepository.findAll()

    override fun count(): Long =
        presencesRepository.count()

    override fun savePresence(presence: Presence): Presence {
        val studentID = presence.studentID
        val teacherID = presence.teacherID

        // Validate student id
        if (!studentsService.hasStudentWithId(studentID)) throw InvalidStudentReferenceException(
            studentID,
            "PresenceService",
            null
        )

        // Validate teacher id
        if (!teachersService.hasTeacherWithId(teacherID)) throw InvalidStudentReferenceException(
            teacherID,
            "PresenceService",
            null
        )

        return presencesRepository.save(presence)
    }

    override fun updatePresence(presence: Presence): Presence? {
        TODO("Not yet implemented")
    }

    override fun hasPresenceWithID(presenceID: String): Boolean =
        ObjectId.isValid(presenceID) && presencesRepository.findById(ObjectId(presenceID)).isPresent

    override fun removePresenceByID(presenceID: String): RemoveResult<Presence> {
        val presenceIDObj = ObjectId(presenceID)
        val presence = presencesRepository.findById(presenceIDObj).orElse(null)

        return if (presence != null) {
            presencesRepository.deleteById(presenceIDObj)
            RemoveResult.successful(presence)
        } else RemoveResult.failed(null)
    }

    override fun findPresenceById(presenceID: String): Presence? =
        presencesRepository.findById(ObjectId(presenceID)).orElse(null)

    override fun findPresencesOfStudent(studentID: String): List<Presence> {
        if (!studentsService.hasStudentWithId(studentID)) throw InvalidStudentReferenceException(
            studentID,
            "PresenceService",
            null
        )

        val query = Query()
        query.addCriteria(Criteria.where("studentID").`is`(studentID))
        query.addCriteria(Criteria.where("present").`is`(true))
        return mongoTemplate.find(query, Presence::class.java)
    }

    override fun findAbsencesOfStudent(studentID: String): List<Presence> {
        TODO("Not yet implemented")
    }
}