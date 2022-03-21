package com.school.schoolregister.controllers.test.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GradeControllerTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    /**
     * Accesses to an endpoint using the current local server address.
     *
     * @param endpoint The endpoint to use
     */
    private fun access(endpoint: String) = "http://localhost:${port}$endpoint"

    @Test
    fun `It should correctly load all grades`() {
        val response = restTemplate.getForEntity<String>(access("/grades"), String::class)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).startsWith("[").endsWith("]")
    }
}