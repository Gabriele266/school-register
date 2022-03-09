package com.school.schoolregister.repositories

import com.school.schoolregister.domain.Teacher
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TeacherRepository : MongoRepository<Teacher, ObjectId> {
    override fun <S : Teacher?> save(entity: S): S
    override fun findAll(): MutableList<Teacher>
    override fun findById(id: ObjectId): Optional<Teacher>
}