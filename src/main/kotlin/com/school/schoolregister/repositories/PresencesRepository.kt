package com.school.schoolregister.repositories

import com.school.schoolregister.domain.entities.Presence
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PresencesRepository : MongoRepository<Presence, ObjectId> {
    override fun <S : Presence?> save(entity: S): S
    override fun findAll(): MutableList<Presence>
    override fun findAllById(ids: MutableIterable<ObjectId>): MutableIterable<Presence>
}