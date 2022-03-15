package com.school.schoolregister.services.mail.validation

/**
 * Checks if the email is correct or not
 */
fun mailIsValid(mail: String): Boolean =
    mail.matches(Regex("^(.+)@(\\\\S+)\$"))