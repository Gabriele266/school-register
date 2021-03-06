package com.school.schoolregister.controllers.teacher

import com.school.schoolregister.domain.entities.Teacher
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.teacher.TeacherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teachers")
class TeacherController(
    private val teachersService: TeacherService
) {
    @GetMapping
    fun getTeahers(): ResponseEntity<List<Teacher>> =
        ResponseEntity.ok(teachersService.findTeachers())

    @PostMapping
    fun addTeacher(@RequestBody teacher: Teacher): ResponseEntity<Teacher> =
        ResponseEntity.ok(teachersService.saveTeacher(teacher))

    @GetMapping("/{id}")
    fun getTeacherDetails(@PathVariable id: String): ResponseEntity<Teacher?> {
        val teacherDetail = teachersService.findTeacherById(id)

        return if (teacherDetail != null)
            ResponseEntity.ok(teacherDetail)
        else ResponseEntity.badRequest().body(null)
    }

    @DeleteMapping("/{id}")
    fun deleteTeacherById(@PathVariable id: String): ResponseEntity<RemoveResult<Teacher>> {
        val result = teachersService.removeTeacherById(id)

        return if (result.successful) ResponseEntity.ok(result)
        else ResponseEntity.badRequest().body(result)
    }
}