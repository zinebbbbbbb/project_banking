package com.example.demo.Card;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/request")
    public ResponseEntity<CardResponseDTO> requestCard(@RequestBody CardRequestDTO dto) {
        return ResponseEntity.ok(cardService.requestCard(dto));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Card>> getCardsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(cardService.getCardsByClient(clientId));
    }

    @PostMapping("/loss")
    public ResponseEntity<CardResponseDTO> declareCardLost(@RequestBody CardLossRequestDTO dto) {
        return ResponseEntity.ok(cardService.declareCardLost(dto));
    }
}