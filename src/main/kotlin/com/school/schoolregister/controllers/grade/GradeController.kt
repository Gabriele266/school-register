package com.school.schoolregister.controllers.grade

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.grade.GradeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/grades")
class GradeController(
    private val gradeService: GradeService
) {
    @GetMapping
    fun getVotes(): ResponseEntity<List<Grade>> =
        ResponseEntity.ok(
            gradeService.findAll()
        )

    @PostMapping
    fun saveAllGrades(@RequestBody gradeList: List<Grade>): ResponseEntity<List<Grade>> =
        ResponseEntity.ok(gradeService.saveGrades(gradeList))

    @DeleteMapping("/{id}")
    fun removeVoteById(@PathVariable id: String): ResponseEntity<RemoveResult<Grade>> {
        val res = gradeService.removeGradeByID(id)

        return if (res.successful) ResponseEntity.ok(res)
        else return ResponseEntity.badRequest().body(res)
    }

    @PutMapping
    fun updateVote(@RequestBody grade: Grade): ResponseEntity<UpdateResult<Grade>> {
        val res = gradeService.updateGrade(grade)

        return if (res.successful) ResponseEntity.ok(res)
        else ResponseEntity.badRequest().body(res)
    }
}
