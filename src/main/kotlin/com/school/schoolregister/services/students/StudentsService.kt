package com.school.schoolregister.services.students

import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.services.common.UpdateResult

interface StudentsService {
    fun saveStudent(input: Student): Student
    fun findStudentById(id: String): Student?
    fun findStudents(): List<Student>
    fun removeStudentById(id: String): Student?
    fun findStudentsCount(): Long
    fun updateStudent(student: Student): UpdateResult<Student>
    fun hasStudentWithId(id: String): Boolean
    fun findFirst(): Student?
}