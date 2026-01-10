package com.example.demo.user;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CompleteProfileRequestDTO {
    private String phone;
    private LocalDate birthday;
    private BigDecimal limitBal;
    private Integer sex;
    private Integer education;
    private Integer marriage;
    private Integer age;
}