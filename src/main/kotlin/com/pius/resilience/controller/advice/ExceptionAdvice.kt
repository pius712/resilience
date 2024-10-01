package com.pius.resilience.controller.advice

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionAdvice {

    private val log = LoggerFactory.getLogger(javaClass)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<Any> {

        log.error("Unexpected error", e)

        return ResponseEntity(
            mutableMapOf(
                "message" to e.message
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}