package com.school.schoolregister.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.school.schoolregister.entities.Student
import com.school.schoolregister.services.StudentsService
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.CoreMatchers.startsWith
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity.status
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest
class StudentsControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var studentsService: StudentsService

    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `It should return all the students of this school`() {
        mockMvc.get("/students").andExpect {
            status {
                isOk()
            }

            content {
                contentType(MediaType.APPLICATION_JSON)
                startsWith("[")
                endsWith("]")
            }
        }
    }
}