package com.samuel_falla.webfluxorch.service;

import com.samuel_falla.webfluxorch.model.JsonApiBodyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Service
public class StepsService {

    private static final Logger LOG = LoggerFactory.getLogger(StepsService.class);


    private final StepOneService stepOneService;
    private final StepTwoService stepTwoService;
    private final StepThreeService stepThreeService;

    private final WebHookService webHookService;

    public StepsService(StepOneService stepOneService, StepTwoService stepTwoService, StepThreeService stepThreeService, WebHookService webHookService) {
        this.stepOneService = stepOneService;
        this.stepTwoService = stepTwoService;
        this.stepThreeService = stepThreeService;
        this.webHookService = webHookService;
    }

    public Mono<String> callAllSteps(JsonApiBodyRequest request) {


        webHookService.notifyHook().subscribe(response -> LOG.info("Webhook call: {}", response));


        return Mono.zip(
                stepOneService.callStepOne(request),
                stepTwoService.callStepTwo(request),
                stepThreeService.callStepThree(request)
        ).map(tuple -> {
            String step1 = tuple.getT1();
            String step2 = tuple.getT2();
            String step3 = tuple.getT3();

            return String.format(
                    "{\"data\":[{\"header\":{\"id\":\"12345\",\"type\":\"TestGiraffeRefrigerator\"},\"answer\":\"Step1: %s - Step2: %s - Step3: %s\"}]}",
                    extractAnswer(step1),
                    extractAnswer(step2),
                    extractAnswer(step3)
            );
        });
    }


    private String extractAnswer(String response) {
        int indexAnswer = response.indexOf("\"answer\":\"");

        if(indexAnswer != -1) {
            int start =  indexAnswer + "\"answer\":\"".length();
            int end = response.indexOf("\"", start);
            response = response.substring(start, end);
        }

        return response;
    }
}
