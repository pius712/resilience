package com.pius.resilience.domains.circuitbreaker

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CircuitBreakerService(
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @CircuitBreaker(name = "test3", fallbackMethod = "fallback")
    fun test(test:String): String {
        logger.info("test method called")
        if(test == "test") {
            throw RuntimeException("test")
        }
        return "test"
    }

    fun fallback(e: Exception):String {
        logger.error("fallback", e)
        return "fallback"
    }
}