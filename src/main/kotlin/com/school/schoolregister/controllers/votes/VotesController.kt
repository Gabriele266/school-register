package com.school.schoolregister.controllers.votes

import com.school.schoolregister.domain.Grade
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult
import com.school.schoolregister.services.votes.VotesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/votes")
class VotesController(
    private val votesService: VotesService
) {
    @GetMapping
    fun getVotes(): ResponseEntity<List<Grade>> =
        ResponseEntity.ok(
            votesService.findAll()
        )

    @PostMapping
    fun addVote(@RequestBody vote: Grade): ResponseEntity<Grade> =
        ResponseEntity.ok(
            votesService.saveVote(vote)
        )

    @DeleteMapping("/{id}")
    fun removeVoteById(@PathVariable id: String): ResponseEntity<RemoveResult<Grade>> {
        val res = votesService.removeVoteById(id)

        return if (res.successful) ResponseEntity.ok(res)
        else return ResponseEntity.badRequest().body(res)
    }

    @PutMapping
    fun updateVote(@RequestBody grade: Grade): ResponseEntity<UpdateResult<Grade>> {
        val res = votesService.updateVote(grade)

        return if (res.successful) ResponseEntity.ok(res)
        else ResponseEntity.badRequest().body(res)
    }
}
