package com.example.demo.MlModel;

import com.example.demo.Account.Account;

import java.math.BigDecimal;

public record Prediction(

          Integer pay0,
         Integer pay2,
        Integer pay3,
        Integer pay4,
        Integer pay5,
        Integer pay6,

        BigDecimal billAmt1,
        BigDecimal billAmt2,
        BigDecimal billAmt3,
        BigDecimal billAmt4,
        BigDecimal billAmt5,
        BigDecimal billAmt6,

        BigDecimal payAmt1,
        BigDecimal payAmt2,
        BigDecimal payAmt3,
        BigDecimal payAmt4,
       BigDecimal payAmt5,
        BigDecimal payAmt6,

       Boolean defaultNextMonth
) {
}
