package com.school.schoolregister.domain.inputs

import com.school.schoolregister.domain.entities.Teacher

data class TeacherInput(
    var name: String,
    var surname: String,
    var email: String,
    var tel: String,
    var address: String,
    var subject: String
)

fun inputTeacher(input: TeacherInput): Teacher =
    Teacher(input.name, input.surname, input.email, input.tel, input.address, input.subject)