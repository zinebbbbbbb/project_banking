package com.example.demo.MlModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PredResponse(
        Double probability_of_default,

        String decision
) {}
