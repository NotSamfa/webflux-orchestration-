package com.samuel_falla.webfluxorch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class WebHookService {

    private final WebClient webClient;

    public WebHookService() {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8086").build();
    }

    public Mono<String> notifyHook() {
        return webClient.post()
                .uri("/hook")
                .retrieve()
                .bodyToMono(String.class);
    }

}
