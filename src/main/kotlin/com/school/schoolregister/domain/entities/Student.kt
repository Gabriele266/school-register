package com.school.schoolregister.domain.entities

import com.school.schoolregister.domain.inputs.StudentInput
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