package com.school.schoolregister

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SchoolRegisterApplication

fun main(args: Array<String>) {
	runApplication<SchoolRegisterApplication>(*args)
}
