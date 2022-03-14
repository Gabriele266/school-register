package com.school.schoolregister.domain.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Teacher(
    var name: String,
    var surname: String,
    var email: String,
    var tel: String,
    var address: String,
    var subject: String
) {
    @Id
    var id: String = ObjectId.get().toHexString()
}