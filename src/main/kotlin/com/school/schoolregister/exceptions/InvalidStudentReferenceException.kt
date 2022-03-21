package com.school.schoolregister.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidStudentReferenceException(studentID: String, serviceName: String, message: String?) :
    ServiceException(
        """
        Service $serviceName \n
        Unable to save entity. Invalid student id reference found. \nStudent id was $studentID. \nAdditional Message: ${message.orEmpty()}
        """.trimIndent()
    )