package com.school.schoolregister.services

import com.school.schoolregister.entities.Student

interface StudentsService {
    fun saveStudent(input: Student): Student
    fun findStudentById(id: String): Student?
    fun findStudents(): List<Student>
    fun removeStudentById(id: String): Student?
    fun findStudentsCount(): Long
    fun updateStudent(student: Student): UpdateResult<Student>
    fun hasStudentWithId(id: String): Boolean
}