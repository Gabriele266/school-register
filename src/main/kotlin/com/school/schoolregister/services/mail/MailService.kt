package com.school.schoolregister.services.mail

import kotlinx.coroutines.Job

interface MailService {
    fun sendEMailTo(receiver: String, subject: String, body: String?): Job
    fun init()
    fun stop()
}