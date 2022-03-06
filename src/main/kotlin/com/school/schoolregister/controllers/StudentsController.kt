package com.school.schoolregister.controllers

import com.school.schoolregister.entities.Student
import com.school.schoolregister.entities.StudentInput
import com.school.schoolregister.entities.studentIsValid
import com.school.schoolregister.services.StudentsService
import com.school.schoolregister.services.UpdateResult
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
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
    fun addStudent(@RequestBody student: Student): ResponseEntity<Any> =
        if (studentIsValid(student)) ResponseEntity.ok(studentsService.saveStudent(student))
        else ResponseEntity.badRequest().body("Invalid student input")

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: String): Student? =
        studentsService.findStudentById(id)

    @DeleteMapping("/{id}")
    fun removeStudentById(@PathVariable id: String): Student? =
        studentsService.removeStudentById(id)

    @PostMapping("/update")
    fun updateStud(@RequestBody student: Student): ResponseEntity<UpdateResult<Student>> {
        val updateResult = studentsService.updateStudent(student)

        return if (updateResult.isSuccessful()) ResponseEntity.ok(updateResult)
        else ResponseEntity.badRequest().body(updateResult)
    }
}