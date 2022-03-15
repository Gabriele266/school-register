package com.school.schoolregister.services.students

import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.repositories.StudentsRepository
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.mail.MailService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class StudentsServiceImpl(
    private val studentsRepository: StudentsRepository,
    private val mailService: MailService
) : StudentsService {
    override fun saveStudent(input: Student): Student {
        // Schedule an email to welcome
        if (input.email != null)
            mailService.scheduleMail(
                input.email, "Welcome in this school",
                """Welcome ${input.name}, we are glad to have you here!!!
                    
                    This is a learning project, not a production one!
                """.trimIndent()
            )

        return studentsRepository.save(input)
    }

    override fun findStudentById(id: String): Student? =
        studentsRepository.findById(ObjectId(id)).orElse(null)

    override fun findStudents(): List<Student> =
        studentsRepository.findAll()

    override fun count(): Long =
        studentsRepository.count()

    /**
     * Removes a student by id
     * @param id Id to remove
     */
    override fun removeStudentById(id: String): Student? {
        val student = studentsRepository.findById(ObjectId(id)).orElse(null)

        if (student != null)
            studentsRepository.deleteById(ObjectId(id))

        if (student.email != null)
            mailService.scheduleMail(
                student.email, "Good bye!", """
                It has been a pleasure to have you here. Goodbye and see you later.
            """.trimIndent()
            )
        return student
    }

    override fun hasStudentWithId(id: String): Boolean =
        studentsRepository.findById(ObjectId(id)).isPresent

    override fun findFirst(): Student? =
        studentsRepository.findAll()[0]

    override fun updateStudent(student: Student): UpdateResult<Student> {
        if (!hasStudentWithId(student.id)) return UpdateResult.failed()

        // Remove by id
        removeStudentById(student.id)

        saveStudent(student)
        return UpdateResult.successful(student)
    }
}