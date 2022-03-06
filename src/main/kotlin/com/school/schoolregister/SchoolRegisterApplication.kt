package com.school.schoolregister

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SchoolRegisterApplication: CommandLineRunner {
	override fun run(vararg args: String?) {
		println("Start school api")
	}
}

fun main(args: Array<String>) {
	runApplication<SchoolRegisterApplication>(*args)
}
