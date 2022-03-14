package com.school.schoolregister.repositories

import com.school.schoolregister.domain.entities.Student
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface StudentsRepository : MongoRepository<Student, ObjectId> {
    override fun <S : Student?> save(entity: S): S
}