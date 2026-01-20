package com.example.demo.MlModel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:8082"})
public class CreditPredictionController {

    private final webService webService;

    @PostMapping("/predict")
    public ResponseEntity<PredResponse> predictCreditScore(@RequestBody Prediction prediction) {
        try {
            PredResponse response = webService.postData(prediction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error calling Python ML service: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Credit prediction service is running");
    }
}