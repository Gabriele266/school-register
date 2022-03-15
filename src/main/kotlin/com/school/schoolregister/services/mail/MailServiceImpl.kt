package com.school.schoolregister.services.mail

import com.school.schoolregister.SchoolRegisterApplication
import com.school.schoolregister.services.mail.templates.MailTemplate
import com.school.schoolregister.services.mail.validation.mailIsValid
import org.slf4j.LoggerFactory
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
class MailServiceImpl(
    private val mailSender: JavaMailSender
) : MailService {
    private var queue: Queue<MailTemplate> = LinkedList()

    override fun scheduleMail(receiver: String, subject: String, body: String?) {
        queue.add(MailTemplate(objectHtml = subject, bodyHtml = body ?: "", receiver = receiver))
    }

    @Scheduled(fixedDelay = 5000)
    private fun lookupQueue() {
        val head = queue.poll()

        if (head != null)
            performMail(head)
    }

    /**
     * Sends an email starting from its template and returns true if the operation is successull
     *
     * @param template Template to use for sending emails
     * @throws This method does not throw nothing
     */
    private fun performMail(template: MailTemplate): Boolean {
        val logger = LoggerFactory.getLogger(SchoolRegisterApplication::class.java)

        if (!mailIsValid(template.receiver)) {
            logger.error("Unable to send mail to ${template.receiver}")
            return false
        }

        return try {
            val mail = mailSender.createMimeMessage()
            val messageHelper = MimeMessageHelper(mail, true)
            messageHelper.setTo(template.receiver)
            messageHelper.setSubject(template.objectHtml)
            messageHelper.setText(template.bodyHtml, true)
            mailSender.send(mail)

            logger.info("Successfully sent mail to ${template.receiver}")
            true
        } catch (e: MailException) {
            logger.error("Unable to send mail to ${template.receiver}. Reason is: $e")
            false
        }
    }
}