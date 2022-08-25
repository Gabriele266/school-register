package com.school.schoolregister.controllers.test.services

import com.school.schoolregister.service.teacher.TeacherService
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TeacherServiceTest(
    private val teachersService: TeacherService
) {
}