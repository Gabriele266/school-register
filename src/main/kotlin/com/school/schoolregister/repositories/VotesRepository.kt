package com.school.schoolregister.repositories

import com.school.schoolregister.entities.Vote
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface VotesRepository : MongoRepository<Vote, ObjectId> {
    override fun <S : Vote?> save(entity: S): S
}