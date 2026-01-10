package com.example.demo.MlModel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class webService {

    private final WebClient webClient;

    public PredResponse postData(Prediction prediction) {
        return webClient
                .post()
                .uri("/credit-score")
                .bodyValue(prediction)
                .retrieve()
                .bodyToMono(PredResponse.class) // Changed from Prediction.class to PredResponse.class
                .block();
    }
}