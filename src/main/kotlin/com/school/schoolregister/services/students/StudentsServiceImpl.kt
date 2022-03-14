package com.school.schoolregister.services.students

import com.school.schoolregister.domain.Student
import com.school.schoolregister.repositories.StudentsRepository
import com.school.schoolregister.services.common.UpdateResult
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class StudentsServiceImpl(
    private val studentsRepository: StudentsRepository
) : StudentsService {
    override fun saveStudent(input: Student): Student =
        studentsRepository.save(input)

    override fun findStudentById(id: String): Student? =
        studentsRepository.findById(ObjectId(id)).orElse(null)

    override fun findStudents(): List<Student> =
        studentsRepository.findAll()

    override fun findStudentsCount(): Long =
        studentsRepository.count()

    /**
     * Removes a student by id
     * @param id Id to remove
     */
    override fun removeStudentById(id: String): Student? {
        val student = studentsRepository.findById(ObjectId(id)).orElse(null)

        if (student != null)
            studentsRepository.deleteById(ObjectId(id))

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