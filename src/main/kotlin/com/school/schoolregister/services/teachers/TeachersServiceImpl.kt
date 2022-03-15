package com.school.schoolregister.services.teachers

import com.school.schoolregister.domain.entities.Teacher
import com.school.schoolregister.repositories.TeacherRepository
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.mail.MailService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class TeachersServiceImpl(
    private val teacherRepository: TeacherRepository,
    private val mailService: MailService
) : TeachersService {
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

}