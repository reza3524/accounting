package com.reza.accounting.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class Resilience4jConfig {

    public Customizer<Resilience4JCircuitBreakerFactory> getResilience4J() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(3))
                        .build())
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(10)
                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                        .minimumNumberOfCalls(5)
                        .failureRateThreshold(25)
                        .build())
                .build());
    }
}
