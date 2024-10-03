package com.samuel_falla.webhookorch.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class WebHookService {



    private static final Logger LOG = LoggerFactory.getLogger(WebHookService.class);

    public Mono<String> processWebhook() {

        LOG.info("Processing webhook");

        String responseMessage = "Hooked";

        return Mono.just(responseMessage);
    }

}
