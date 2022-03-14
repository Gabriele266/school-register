package com.school.schoolregister.domain.inputs

import java.time.LocalDateTime
import java.time.ZoneOffset

data class StudentInput(
    val name: String,
    val surname: String,
    val birthDate: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
    val email: String? = null,
    val tel: String? = null,
)