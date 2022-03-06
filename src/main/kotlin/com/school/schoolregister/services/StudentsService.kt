package com.school.schoolregister.services

import com.school.schoolregister.entities.Student
import org.bson.types.ObjectId

interface StudentsService {
    fun saveStudent(input: Student): Student
    fun findStudentById(id: ObjectId): Student?
    fun findStudents(): List<Student>
    fun removeStudentById(id: ObjectId): Student?
    fun findStudentsCount(): Long
    fun updateStudent(student: Student): UpdateResult<Student>
    fun hasStudentWithId(id: ObjectId): Boolean
}