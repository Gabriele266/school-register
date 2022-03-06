package com.school.schoolregister.controllers.students

import com.school.schoolregister.entities.Student
import com.school.schoolregister.entities.studentIsValid
import com.school.schoolregister.services.students.StudentsService
import com.school.schoolregister.services.common.UpdateResult
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
    fun getStudentById(@PathVariable id: String): ResponseEntity<Student?> =
        if (studentsService.hasStudentWithId(id)) ResponseEntity.ok(studentsService.findStudentById(id))
        else ResponseEntity.badRequest().body(null)

    @DeleteMapping("/{id}")
    fun removeStudentById(@PathVariable id: String): ResponseEntity<Student?> =
        if (studentsService.hasStudentWithId(id)) ResponseEntity.ok(studentsService.removeStudentById(id))
        else ResponseEntity.badRequest().body(null)

    @PostMapping("/update")
    fun updateStud(@RequestBody student: Student): ResponseEntity<UpdateResult<Student>> {
        val updateResult = studentsService.updateStudent(student)

        return if (updateResult.isSuccessful()) ResponseEntity.ok(updateResult)
        else ResponseEntity.badRequest().body(updateResult)
    }
}