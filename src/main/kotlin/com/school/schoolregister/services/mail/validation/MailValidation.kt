package com.school.schoolregister.services.mail.validation

import java.util.regex.Pattern

/**
 * Checks if the email is correct or not
 */
fun mailIsValid(mail: String): Boolean = patternMatches(
    mail, "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
)

/**
 *
 */
fun patternMatches(emailAddress: String, regexPattern: String): Boolean {
    return Pattern.compile(regexPattern).matcher(emailAddress).matches()
}