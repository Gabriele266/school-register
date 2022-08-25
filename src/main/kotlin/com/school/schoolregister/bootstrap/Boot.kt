package com.school.schoolregister.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Boot : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Start school api")
    }
}