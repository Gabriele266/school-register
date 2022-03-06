package com.school.schoolregister.controllers

import com.school.schoolregister.entities.Student
import com.school.schoolregister.services.StudentsService
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class StudentsController(
    private val studentsService: StudentsService
) {
    @GetMapping
    fun getStudents(): List<Student> =
        studentsService.findStudents()

    @PostMapping
    fun addStudent(@RequestBody student: Student): Student =
        studentsService.saveStudent(student)

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: String): Student? =
        studentsService.findStudentById(ObjectId(id))

    @DeleteMapping("/{id}")
    fun removeStudentById(@PathVariable id: String): Student? =
        studentsService.removeStudentById(ObjectId(id))
}