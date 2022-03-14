package com.school.schoolregister.controllers.test

import com.school.schoolregister.domain.Student
import com.school.schoolregister.domain.studentIsValid
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StudentTest {

    @Test
    fun `It should correctly validate this student`() {
        val student = Student("Gabriele", "Cavallo", 25)
        assertThat(studentIsValid(student)).isTrue
    }

    @Test
    fun `This student is not valid`() {
        val student = Student("Gabriele2$", "Cavallo", 1111)

        assertThat(studentIsValid(student)).isFalse
    }

    @Test
    fun `This student should be valid`() {
        val student = Student("Simone Magellano", "Arcano", 11)

        assertThat(studentIsValid(student)).isTrue
    }
}