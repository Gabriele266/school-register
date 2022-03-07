package com.school.schoolregister.services.mail

data class MailTemplate(
    var objectHtml: String,
    var bodyHtml: String,
    var receiver: String
)