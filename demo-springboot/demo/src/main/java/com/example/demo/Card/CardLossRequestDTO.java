package com.example.demo.Card;

import lombok.Data;

@Data
public class CardLossRequestDTO {
    private Long cardId;
    private String description;
}