package com.school.schoolregister.services.mail

/**
 * Service to manage automatic email sending and scheduling
 */
interface MailService {
    /**
     * Schedules a new email to send. Every email is send every 5 seconds and stored into a blocking
     * queue.
     *
     * @param receiver The receiver for this mail
     * @param subject The subject of the mail to send
     * @param body An optional body for the email (html document)
     */
    fun scheduleMail(receiver: String, subject: String, body: String?)
}