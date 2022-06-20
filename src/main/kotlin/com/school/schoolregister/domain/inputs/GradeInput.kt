package com.school.schoolregister.domain.inputs

data class GradeInput(
    var studentID: String,
    var teacherID: String,
    var value: Float,
    var subject: String,
    var dateTime: Long,
    var description: String
)