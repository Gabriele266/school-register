package com.school.schoolregister.domain

import com.school.schoolregister.controllers.generateRandomString
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZoneOffset

@Document
data class Student(
    val name: String,
    val surname: String,
    val birthDate: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
    val email: String? = null,
    val tel: String? = null,
) {
    @Id
    var id: String = ObjectId.get().toHexString()

    constructor(input: StudentInput) : this(
        input.name,
        input.surname,
        input.birthDate,
        input.email,
        input.tel,
    )
}

fun generateRandomStudent(): Student =
    Student(
        generateRandomString(),
        generateRandomString(),
        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
    )

fun studentIsValid(student: Student): Boolean =
    student.name.matches("^[a-zA-Z ]*$".toRegex())
            && student.surname.matches("^[a-zA-Z ]*$".toRegex())