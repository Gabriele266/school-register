package com.school.schoolregister.exceptions

class InvalidStudentReferenceException(studentID: String, serviceName: String, message: String?) :
    ServiceException(
        """
        Service $serviceName \n
        Unable to save entity. Invalid student id reference found. \nStudent id was $studentID. \nAdditional Message: ${message.orEmpty()}
        """.trimIndent()
    )