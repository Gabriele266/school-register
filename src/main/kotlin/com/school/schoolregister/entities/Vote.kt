package com.school.schoolregister.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Vote(
    var studentID: String,
    var teacherID: String,
    var value: Float,
    var dateTime: Long,
    var description: String
) {
    @Id
    var id = ObjectId.get().toHexString()
}