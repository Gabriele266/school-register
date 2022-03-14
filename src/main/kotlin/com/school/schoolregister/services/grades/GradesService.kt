package com.school.schoolregister.services.grades

import com.school.schoolregister.domain.entities.Grade
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult

interface GradesService {
    fun saveVote(vote: Grade): Grade
    fun updateVote(vote: Grade): UpdateResult<Grade>
    fun removeVoteById(voteId: String): RemoveResult<Grade>
    fun hasVoteWithId(voteId: String): Boolean
    fun findVoteById(voteId: String): Grade?
    fun findAll(): List<Grade>
    fun count(): Long
}