package com.example.demo.MlModel;

public record PredResponse(
        Double defaultProbability,
        String decisison
) {
}
