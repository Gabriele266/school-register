package com.school.schoolregister.controllers.grades

import com.school.schoolregister.domain.Grade
import com.school.schoolregister.exceptions.InvalidStudentReferenceException
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.grades.GradesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/grades")
class GradesController(
    private val gradesService: GradesService
) {
    @GetMapping
    fun getVotes(): ResponseEntity<List<Grade>> =
        ResponseEntity.ok(
            gradesService.findAll()
        )

    @PostMapping
    fun addVote(@RequestBody grade: Grade): ResponseEntity<Any> =
        try {
            ResponseEntity.ok(
                gradesService.saveVote(grade)
            )
        } catch (e: InvalidStudentReferenceException) {
            ResponseEntity.badRequest().body(e)
        }

    @DeleteMapping("/{id}")
    fun removeVoteById(@PathVariable id: String): ResponseEntity<RemoveResult<Grade>> {
        val res = gradesService.removeVoteById(id)

        return if (res.successful) ResponseEntity.ok(res)
        else return ResponseEntity.badRequest().body(res)
    }

    @PutMapping
    fun updateVote(@RequestBody grade: Grade): ResponseEntity<UpdateResult<Grade>> {
        val res = gradesService.updateVote(grade)

        return if (res.successful) ResponseEntity.ok(res)
        else ResponseEntity.badRequest().body(res)
    }
}
