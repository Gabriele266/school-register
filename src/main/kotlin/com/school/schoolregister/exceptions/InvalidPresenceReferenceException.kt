package com.school.schoolregister.exceptions

class InvalidPresenceReferenceException(presenceID: String, serviceName: String, message: String? = null) : Exception(
    """
    Invalid reference to a presence. Presence id: $presenceID
    Service is: $serviceName
""".trimIndent()
)