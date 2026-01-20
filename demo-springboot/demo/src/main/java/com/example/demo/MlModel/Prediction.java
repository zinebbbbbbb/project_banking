package com.example.demo.MlModel;

import java.math.BigDecimal;

public record Prediction(

        BigDecimal LIMIT_BAL,
        Integer SEX,
        Integer EDUCATION,
        Integer MARRIAGE,
        Integer AGE,

        Integer PAY_0,
        Integer PAY_2,
        Integer PAY_3,
        Integer PAY_4,
        Integer PAY_5,
        Integer PAY_6,

        BigDecimal BILL_AMT1,
        BigDecimal BILL_AMT2,
        BigDecimal BILL_AMT3,
        BigDecimal BILL_AMT4,
        BigDecimal BILL_AMT5,
        BigDecimal BILL_AMT6,

        BigDecimal PAY_AMT1,
        BigDecimal PAY_AMT2,
        BigDecimal PAY_AMT3,
        BigDecimal PAY_AMT4,
        BigDecimal PAY_AMT5,
        BigDecimal PAY_AMT6
) {}
