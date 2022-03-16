package com.school.schoolregister.domain.entities

import com.school.schoolregister.domain.common.WithID
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Presence(
    val date: Long,
    val studentID: String,
    val teacherID: String,
    val present: Boolean
) : WithID()