package com.example.demo.Card;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CardResponseDTO {
    private Long id;
    private String cardNumber;
    private String type;
    private LocalDate expirationDate;
    private String status;
}