package com.school.schoolregister.configuration

import com.school.schoolregister.controllers.currentDateTimeMillis
import com.school.schoolregister.controllers.generateRandomString
import com.school.schoolregister.entities.Student
import com.school.schoolregister.entities.Vote
import com.school.schoolregister.entities.generateRandomStudent
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
open class TestEntitiesConfiguration {
    @Bean(name = ["test-vote"])
    open fun testingVote(): Vote =
        Vote(
            description = generateRandomString(),
            studentID = "",
            teacherID = "",
            value = 10F,
            dateTime = currentDateTimeMillis()
        )

    @Bean(name = ["test-student"])
    open fun testingStudent(): Student =
        generateRandomStudent()
}