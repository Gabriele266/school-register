package com.school.schoolregister.services

import com.school.schoolregister.entities.Student
import com.school.schoolregister.repositories.StudentsRepository
import org.bson.types.ObjectId
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class StudentsServiceImpl(
    private val studentsRepository: StudentsRepository
) : StudentsService {
    override fun saveStudent(input: Student): Student =
        studentsRepository.save(input)

    override fun findStudentById(id: ObjectId): Student? =
        studentsRepository.findById(id).orElse(null)

    override fun findStudents(): List<Student> =
        studentsRepository.findAll()

    override fun findStudentsCount(): Long =
        studentsRepository.count()

    /**
     * Removes a student by id
     * @param id Id to remove
     */
    override fun removeStudentById(id: ObjectId): Student? {
        val student = studentsRepository.findById(id).orElse(null)

        if (student != null)
            studentsRepository.deleteById(id)

        return student
    }

    override fun hasStudentWithId(id: ObjectId): Boolean =
        studentsRepository.findById(id).isPresent

    override fun updateStudent(student: Student): UpdateResult<Student> {
        if (!hasStudentWithId(student.id)) return UpdateResult.failed()

        // Remove by id
        removeStudentById(student.id)

        saveStudent(student)
        return UpdateResult.successful(student)
    }
}