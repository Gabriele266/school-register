package com.school.schoolregister.services.mail.templates

data class MailTemplate(
    var objectHtml: String,
    var bodyHtml: String,
    var receiver: String
) {
    override fun toString(): String =
        "MailTemplate to $receiver with object $objectHtml and body $bodyHtml"
}