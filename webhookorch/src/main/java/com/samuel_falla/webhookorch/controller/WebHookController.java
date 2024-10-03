package com.samuel_falla.webhookorch.controller;

import com.samuel_falla.webhookorch.service.WebHookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebHookController {

    private static final Logger LOG = LoggerFactory.getLogger(WebHookController.class);

    private final WebHookService webHookService;

    public WebHookController(WebHookService webHookService) {
        this.webHookService = webHookService;
    }

    @PostMapping("/hook")
    public Mono<String> receiveWebhook() {
        LOG.info("Receiving webhook");

        return webHookService.processWebhook()
                .doOnSuccess(response -> LOG.info("Webhook processed: {}", response))
                .doOnError(error -> LOG.error("Error processing webhook: {}", error.getMessage()));
    }
}
