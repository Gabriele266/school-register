package com.school.schoolregister.controllers.student

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.domain.entities.Student
import com.school.schoolregister.domain.utils.studentIsValid
import com.school.schoolregister.service.common.UpdateResult
import com.school.schoolregister.service.grade.GradeService
import com.school.schoolregister.service.student.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService,
    private val gradeService: GradeService
) {
    @GetMapping
    fun getStudents(): List<Student> =
        studentService.findStudents()

    @PostMapping
    fun addStudent(@RequestBody student: Student): ResponseEntity<Any> =
        if (studentIsValid(student)) ResponseEntity.ok(studentService.saveStudent(student))
        else ResponseEntity.badRequest().body("Invalid student input")

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: String): ResponseEntity<Any?> =
        if (studentService.hasStudentWithId(id)) ResponseEntity.ok(studentService.findStudentById(id))
        else ResponseEntity.badRequest().body("Invalid id")

    @DeleteMapping("/{id}")
    fun removeStudentById(@PathVariable id: String): ResponseEntity<Student?> =
        if (studentService.hasStudentWithId(id)) ResponseEntity.ok(studentService.removeStudentById(id))
        else ResponseEntity.badRequest().body(null)

    @PostMapping("/update")
    fun updateStud(@RequestBody student: Student): ResponseEntity<UpdateResult<Student>> {
        val updateResult = studentService.updateStudent(student)

        return if (updateResult.isSuccessful()) ResponseEntity.ok(updateResult)
        else ResponseEntity.badRequest().body(updateResult)
    }

    @GetMapping("/{id}/grades")
    fun getStudentGrades(@PathVariable id: String, @RequestParam(name = "teacher") teacherId: String?): List<Grade> {
        val result = gradeService.findByStudentID(id)

        if (teacherId != null)
            return result.filter { it.teacherID == teacherId }

        return result
    }
}