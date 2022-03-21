package com.school.schoolregister.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidTeacherReferenceException(teacherID: String, serviceName: String, message: String?) : Exception(
    """
    Service $serviceName\n
    Unable to save entity. Invalid teacher id reference found. Teacher ID: $teacherID. Additional message: $message
""".trimIndent()
)