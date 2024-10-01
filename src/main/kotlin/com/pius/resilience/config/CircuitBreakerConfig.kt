package com.pius.resilience.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
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
        circuitBreaker.eventPublisher.onSuccess { event->
            logger.info("CircuitBreaker 성공: ${event}")
        }.onError { event->
            logger.error("CircuitBreaker 에러 발생: ${event.throwable.message}")
        }.onStateTransition { event ->
            logger.info("CircuitBreaker 상태 전환: ${event.stateTransition}")
        }.onIgnoredError { event->
            logger.error("CircuitBreaker 무시된 에러 발생: ${event.throwable.message}")
        }.onCallNotPermitted { event->
            logger.error("CircuitBreaker 호출 금지: ${event}")
        }.onReset { event->
            logger.info("CircuitBreaker 리셋: ${event}")
        }
        return circuitBreaker
    }
}