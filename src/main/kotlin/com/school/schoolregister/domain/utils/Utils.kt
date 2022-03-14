package com.school.schoolregister.domain.utils

import com.school.schoolregister.controllers.generateRandomString
import com.school.schoolregister.domain.entities.Student
import java.time.LocalDateTime
import java.time.ZoneOffset


fun generateRandomStudent(): Student =
    Student(
        generateRandomString(),
        generateRandomString(),
        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
    )

fun studentIsValid(student: Student): Boolean =
    student.name.matches("^[a-zA-Z ]*$".toRegex())
            && student.surname.matches("^[a-zA-Z ]*$".toRegex())