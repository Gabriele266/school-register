package com.school.schoolregister.services.mail

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service


@Service
class MailServiceImpl(
    private val mailSender: JavaMailSender
) : MailService {

    override fun sendEMailTo(receiver: String, subject: String, body: String?) = runBlocking {
        launch {
            try {
                val mail = mailSender.createMimeMessage()
                val messageHelper = MimeMessageHelper(mail, true)
                messageHelper.setTo(receiver)
                messageHelper.setSubject(subject)
                messageHelper.setText(body ?: "", true)
                mailSender.send(mail)
            } catch (e: MailException) {
                e.printStackTrace()
            }
        }
    }

    override fun init() {

    }

    override fun stop() {
        TODO("Not yet implemented")
    }

}