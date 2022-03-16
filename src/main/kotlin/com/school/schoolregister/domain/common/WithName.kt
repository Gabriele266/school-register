package com.school.schoolregister.domain.common

import org.springframework.data.mongodb.core.mapping.Document

@Document
class WithName(
    val name: String
) : WithID()