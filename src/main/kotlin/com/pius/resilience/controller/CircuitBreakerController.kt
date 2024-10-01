package com.pius.resilience.controller

import com.pius.resilience.domains.circuitbreaker.CircuitBreakerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/circuitbreaker")
class CircuitBreakerController(
    private val circuitBreakerService: CircuitBreakerService
) {

    @GetMapping
    fun test(
        @RequestParam("test") test: String
    ) :String {
        return circuitBreakerService.test(test)
    }
}