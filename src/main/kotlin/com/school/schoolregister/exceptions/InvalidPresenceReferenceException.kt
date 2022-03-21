package com.school.schoolregister.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidPresenceReferenceException(presenceID: String, serviceName: String, message: String? = null) : Exception(
    """
    Invalid reference to a presence. Presence id: $presenceID
    Service is: $serviceName
""".trimIndent()
)