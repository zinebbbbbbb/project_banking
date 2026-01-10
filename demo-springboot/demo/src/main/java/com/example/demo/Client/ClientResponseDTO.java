package com.example.demo.Client;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ClientResponseDTO {
    private Long id;
    private String phone;
    private LocalDate birthday;

    private BigDecimal limitBal;
    private Integer sex;
    private Integer education;
    private Integer marriage;
    private Integer age;
}