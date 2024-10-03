package com.samuel_falla.batchOrches.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@Service
@EnableScheduling
public class ScheduledOrchestratorService {

    private final RestTemplate restTemplate;

    public ScheduledOrchestratorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 20000)
    public void callOrchestrator() {
        try {
            System.out.println("Making the respponse to orchestator...");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("id", "12345");
            headerMap.put("type", "TestGiraffeRefrigerator");

            Map<String, Object> dataItem = new HashMap<>();
            dataItem.put("header", headerMap);
            dataItem.put("step", "1");

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("data", new Object[]{dataItem});

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            String response = restTemplate.postForObject("http://localhost:8080/api/steps/orchestration", request, String.class);
            System.out.println("Response from orchestrator: " + response);
        } catch (Exception e) {
            System.err.println("Error calling orchestrator: " + e.getMessage());
        }
    }
}
