package com.school.schoolregister.services.teacher

import com.school.schoolregister.domain.entities.Teacher
import com.school.schoolregister.repositories.TeacherRepository
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.mail.MailService
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class TeacherServiceImpl(
    private val teacherRepository: TeacherRepository,
    private val mailService: MailService,
    private val mongoTemplate: MongoTemplate
) : TeacherService {
    override fun saveTeacher(teacher: Teacher): Teacher {
        mailService.scheduleMail(
            teacher.email,
            subject = "Welcome in this school!",
            body = "Very important, do not forget to dry your plant!!"
        )

        return teacherRepository.save(teacher)
    }

    override fun findTeacherById(id: String): Teacher? =
        teacherRepository.findById(ObjectId(id)).orElse(null)

    override fun findTeachers(): List<Teacher> =
        teacherRepository.findAll()

    override fun removeTeacherById(id: String): RemoveResult<Teacher> {
        val teacher = teacherRepository.findById(ObjectId(id)).orElse(null)

        return if (teacher != null) {
            // Send deletions email
            mailService.scheduleMail(
                teacher.email,
                "Account deletion",
                "<h1>Oooops, your account got deleted</h1> <br> Maybe you forgot to dry your plant? In that case you should be vary <b>scared</b>. "
            )
            teacherRepository.deleteById(ObjectId(id))

            RemoveResult.successful(teacher)
        } else RemoveResult.failed("Invalid teacher ID")

    }

    override fun count(): Long =
        teacherRepository.count()

    override fun updateTeacher(teacher: Teacher): UpdateResult<Teacher> {
        val teacherID = ObjectId(teacher.id)
        val original = teacherRepository.findById(teacherID).orElse(null)

        return if (original != null) {
            teacherRepository.deleteById(teacherID)

            UpdateResult.successful(teacher)
        } else UpdateResult.failed()
    }

    override fun hasTeacherWithId(id: String): Boolean =
        findTeacherById(id) != null

    override fun findTeachersWithName(name: String): List<Teacher> {
        val query = Query()

        query.addCriteria(Criteria.where("name").`is`(name))

        return mongoTemplate.find(query, Teacher::class.java)
    }

    override fun findTeachersWithSurname(surname: String): List<Teacher> {
        val query = Query().addCriteria(Criteria.where("surname").`is`(surname))

        return mongoTemplate.find(query, Teacher::class.java)
    }

    override fun findTeachersOfSubject(subject: String): List<Teacher> {
        val query = Query().addCriteria(Criteria.where("subject").regex("^$subject"))

        return mongoTemplate.find(query, Teacher::class.java)
    }
}