package com.school.schoolregister.exceptions

abstract class GradeServiceException(message: String) : Exception(message) {
    override fun toString(): String = "GradeServiceException $message"
}