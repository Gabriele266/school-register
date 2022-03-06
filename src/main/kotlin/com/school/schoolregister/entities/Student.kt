package com.school.schoolregister.entities

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.school.schoolregister.controllers.generateRandomInt
import com.school.schoolregister.controllers.generateRandomString
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Student(
    val name: String,
    val surname: String,
    val age: Int,
    @Id val id: ObjectId = ObjectId.get()
)

fun generateRandomStudent(): Student =
    Student(
        generateRandomString(),
        generateRandomString(),
        generateRandomInt()
    )