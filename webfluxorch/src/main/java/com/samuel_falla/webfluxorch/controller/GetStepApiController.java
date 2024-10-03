package com.samuel_falla.webfluxorch.controller;

import com.samuel_falla.webfluxorch.model.GetEnigmaRequest;
import com.samuel_falla.webfluxorch.model.GetEnigmaStepResponse;
import com.samuel_falla.webfluxorch.model.JsonApiBodyRequest;
import com.samuel_falla.webfluxorch.service.StepOneService;
import com.samuel_falla.webfluxorch.service.StepsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/steps")
public class GetStepApiController {

    private final StepsService stepsService;

    public GetStepApiController(StepsService stepsService) {
        this.stepsService = stepsService;
    }



    @PostMapping("/orchestration")
    public Mono<String> combineSteps(@RequestBody JsonApiBodyRequest request) {
        return stepsService.callAllSteps(request);
    }
}
