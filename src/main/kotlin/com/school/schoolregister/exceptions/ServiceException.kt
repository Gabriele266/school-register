package com.school.schoolregister.exceptions

abstract class ServiceException(message: String) : Exception(message) {
    override fun toString(): String = "GradeServiceException $message"
}