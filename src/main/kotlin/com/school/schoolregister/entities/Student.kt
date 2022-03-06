package com.school.schoolregister.entities

import com.school.schoolregister.controllers.generateRandomString
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime
import java.time.ZoneOffset

@Document
data class Student(
    val name: String,
    val surname: String,
    val birthDate: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
    val email: String? = null,
    val tel: String? = null,
    @DocumentReference
    var votes: MutableList<Vote> = mutableListOf()
) {
    @Id
    var id: String = ObjectId.get().toHexString()
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