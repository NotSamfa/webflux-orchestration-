package com.samuel_falla.webfluxorch.service;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samuel_falla.webfluxorch.model.JsonApiBodyRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StepOneService {

    private static final Logger LOG = LoggerFactory.getLogger(StepOneService.class);

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;

    public StepOneService(CircuitBreakerRegistry circuitBreakerRegistry, RetryRegistry retryRegistry) {
        this.webClient = WebClient.builder().baseUrl("http://step-one:8081").build();
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("one");
        this.retry = retryRegistry.retry("one");


        this.retry.getEventPublisher()
                .onRetry(event -> LOG.info("Retrying step one... Attempt: {}", event.getNumberOfRetryAttempts()));

        this.circuitBreaker.getEventPublisher()
                .onStateTransition(event -> LOG.info("Circuit Breaker State Transition for step one: from {} to {}",
                        event.getStateTransition().getFromState(),
                        event.getStateTransition().getToState()));

    }


    public Mono<String> callStepOne(JsonApiBodyRequest request) {
        return webClient.post()
                .uri("/getStep")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .transformDeferred(RetryOperator.of(retry))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(throwable -> {
                    LOG.warn("Too many retries for step one");
                    return Mono.just("Step one not found");
                });
    }
}