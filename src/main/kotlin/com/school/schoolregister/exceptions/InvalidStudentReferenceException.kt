package com.school.schoolregister.exceptions

class InvalidStudentReferenceException(studentID: String, message: String?) :
    GradeServiceException(
        """
        Unable to save grade. Invalid student id reference found. \nStudent id was $studentID. \nAdditional Message: ${message.orEmpty()}
        """.trimIndent()
    )