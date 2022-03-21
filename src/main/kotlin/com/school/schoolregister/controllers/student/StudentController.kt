package com.school.schoolregister.controllers.student

import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.domain.utils.studentIsValid
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.student.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentsService: StudentService
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