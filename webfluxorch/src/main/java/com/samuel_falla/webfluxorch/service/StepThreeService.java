package com.samuel_falla.webfluxorch.service;

import com.samuel_falla.webfluxorch.model.JsonApiBodyRequest;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StepThreeService {

    private static final Logger LOG = LoggerFactory.getLogger(StepThreeService.class);

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;

    public StepThreeService(CircuitBreakerRegistry circuitBreakerRegistry, RetryRegistry retryRegistry) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("three");
        this.retry = retryRegistry.retry("three");

        this.retry.getEventPublisher()
                .onRetry(event -> LOG.info("Retrying step three... Attempt: {}", event.getNumberOfRetryAttempts()));

        this.circuitBreaker.getEventPublisher()
                .onStateTransition(event -> LOG.info("Circuit Breaker State Transition for step three: from {} to {}",
                        event.getStateTransition().getFromState(),
                        event.getStateTransition().getToState()));

    }

    public Mono<String> callStepThree(JsonApiBodyRequest request) {
        return webClient.post()
                .uri("/getStep")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .transformDeferred(RetryOperator.of(retry))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(throwable -> {
                    LOG.warn("Too many retries for step three");
                    return Mono.just("Step three not found");
                });
    }

}