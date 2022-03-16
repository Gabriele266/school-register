package com.school.schoolregister.exceptions

class InvalidTeacherReferenceException(teacherID: String, serviceName: String, message: String?) : Exception(
    """
    Service $serviceName\n
    Unable to save entity. Invalid teacher id reference found. Teacher ID: $teacherID. Additional message: $message
""".trimIndent()
)