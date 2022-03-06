package com.school.schoolregister.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Vote(
    val studentID: String,
    val teacherID: String,
    val value: Float,
    val dateTime: Long,
    val description: String
) {
    @Id
    var id = ObjectId.get().toHexString()
}