package com.school.schoolregister.domain.entities

import com.school.schoolregister.domain.inputs.GradeInput
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Grade(
    var studentID: String,
    var teacherID: String,
    var value: Float,
    val subject: String,
    var dateTime: Long,
    var description: String
) {
    @Id
    var id = ObjectId.get().toHexString()
}

fun inputGrade(input: GradeInput): Grade =
    Grade(input.studentID, input.teacherID, input.value, input.subject, input.dateTime, input.description)