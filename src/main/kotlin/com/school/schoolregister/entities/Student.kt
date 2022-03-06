package com.school.schoolregister.entities

import com.school.schoolregister.controllers.generateRandomInt
import com.school.schoolregister.controllers.generateRandomString
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Student(
    val name: String,
    val surname: String,
    val age: Int,
) {
    @Id
    var id: String = ObjectId.get().toHexString()

    companion object Factory {
        fun fromInput(input: StudentInput): Student = Student(input.name, input.surname, input.age)
    }
}

data class StudentInput(
    val name: String,
    val surname: String,
    val age: Int
)

fun generateRandomStudent(): Student =
    Student(
        generateRandomString(),
        generateRandomString(),
        generateRandomInt()
    )