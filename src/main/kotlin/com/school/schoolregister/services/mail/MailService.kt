package com.school.schoolregister.services.mail

import com.school.schoolregister.services.mail.templates.MailTemplate
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*

@Service
@EnableAsync
@EnableScheduling
class MailService(
    private val mailSender: JavaMailSender
) {
    private var queue: Queue<MailTemplate> = LinkedList()

    /**
     * Schedules a new email to send
     */
    fun scheduleMail(receiver: String, subject: String, body: String?) {
        queue.add(MailTemplate(objectHtml = subject, bodyHtml = body ?: "", receiver = receiver))
    }

    @Scheduled(fixedDelay = 5000)
    private fun lookupQueue() {
        val head = queue.poll()

        if (head != null)
            performMail(head)
    }

    private fun performMail(template: MailTemplate): Boolean {
        return try {
            val mail = mailSender.createMimeMessage()
            val messageHelper = MimeMessageHelper(mail, true)
            messageHelper.setTo(template.receiver)
            messageHelper.setSubject(template.objectHtml)
            messageHelper.setText(template.bodyHtml, true)
            mailSender.send(mail)

            true
        } catch (e: MailException) {
            e.printStackTrace()
            println(template)
            false
        }
    }
}