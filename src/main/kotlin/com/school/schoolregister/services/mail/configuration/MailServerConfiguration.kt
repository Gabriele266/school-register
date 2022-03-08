package com.school.schoolregister.services.mail.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
open class MailServerConfiguration {
    @Bean
    open fun mailSender(): JavaMailSenderImpl {
        val sender = JavaMailSenderImpl()

        sender.apply {
            host = "smtp.gmail.com"
            port = 587
            username = "registroel.scuola@gmail.com"
            password = "7H64UYgCHvQA3hh"
        }

        var props = sender.javaMailProperties
        props.apply {
            put("mail.transport.protocol", "smtp");
            put("mail.smtp.auth", "true");
            put("mail.smtp.starttls.enable", "true");
            put("mail.debug", "true");
        }

        return sender
    }
}