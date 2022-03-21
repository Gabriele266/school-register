package com.school.schoolregister.repositories

import com.school.schoolregister.domain.entities.Grade
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface GradeRepository : MongoRepository<Grade, ObjectId> {
    override fun <S : Grade?> save(entity: S): S
}