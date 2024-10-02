package com.qmt.beermind

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BeermindApplication

fun main(args: Array<String>) {
    runApplication<BeermindApplication>(*args)
}
