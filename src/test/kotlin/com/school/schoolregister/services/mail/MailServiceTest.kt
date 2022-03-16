package com.school.schoolregister.services.mail

import com.school.schoolregister.services.mail.validation.mailIsValid
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MailServiceTest {
    @Test
    fun `It should detect a correct email`() {
        assertThat(mailIsValid("gabri.cabal@gmail.com")).isTrue
    }

    @Test
    fun `It should detect an incorrect email`() {
        assertThat(mailIsValid("jfdks./cana@mimmo.com")).isFalse
    }

    @Test
    fun `This mail should be valid`() {
        assertThat(mailIsValid("username@domain.com")).isTrue
    }

    @Test
    fun `This mail should also be valid`() {
        assertThat(mailIsValid("mimmo.pippolololololo@gmail.com.net")).isTrue
    }
}