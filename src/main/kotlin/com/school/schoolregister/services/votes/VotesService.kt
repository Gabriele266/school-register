package com.school.schoolregister.services.votes

import com.school.schoolregister.entities.Vote
import com.school.schoolregister.services.common.RemoveResult
import com.school.schoolregister.services.common.UpdateResult

interface VotesService {
    fun saveVote(vote: Vote): Vote
    fun updateVote(vote: Vote): UpdateResult<Vote>
    fun removeVoteById(voteId: String): RemoveResult<Vote>
    fun hasVoteWithId(voteId: String): Boolean
    fun findVoteById(voteId: String): Vote?
    fun findAll(): List<Vote>
}