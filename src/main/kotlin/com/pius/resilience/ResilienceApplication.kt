package com.pius.resilience

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ResilienceApplication

fun main(args: Array<String>) {
    runApplication<ResilienceApplication>(*args)
}
