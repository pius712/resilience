package com.pius.resilience.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CircuitBreakerConfig {

    private val logger = LoggerFactory.getLogger(javaClass)
    @Bean
    fun circuitBreakerEventListener(circuitBreakerRegistry: CircuitBreakerRegistry):CircuitBreaker {
        val circuitBreaker: CircuitBreaker = circuitBreakerRegistry.circuitBreaker("test")

        // 상태 전환 이벤트 리스너
        circuitBreaker.eventPublisher.onStateTransition { event ->
            logger.info("CircuitBreaker 상태 전환: ${event.stateTransition}")
        }

        // 에러 발생 이벤트 리스너
        circuitBreaker.eventPublisher.onError { event->
            logger.error("CircuitBreaker 에러 발생: ${event.throwable.message}")
        }

        // 기타 필요한 이벤트 리스너를 추가할 수 있습니다.
        return circuitBreaker
    }
}