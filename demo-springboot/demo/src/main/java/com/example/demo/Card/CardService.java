package com.example.demo.Card;

import com.example.demo.Client.Client;
import com.example.demo.Client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;

    public CardResponseDTO requestCard(CardRequestDTO dto) {
        Optional<Client> clientOpt = clientRepository.findById(dto.getClientId());
        if (clientOpt.isEmpty()) throw new RuntimeException("Client not found");
        Card card = Card.builder()
                .cardNumber(generateCardNumber())
                .type(dto.getType())
                .expirationDate(LocalDate.now().plusYears(4))
                .status("ACTIVE")
                .client(clientOpt.get())
                .build();
        cardRepository.save(card);
        return toDTO(card);
    }

    public List<Card> getCardsByClient(Long clientId) {
        return cardRepository.findByClientId(clientId);
    }

    private String generateCardNumber() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) sb.append(r.nextInt(10));
        return sb.toString();
    }

    private CardResponseDTO toDTO(Card card) {
        CardResponseDTO dto = new CardResponseDTO();
        dto.setId(card.getId());
        dto.setCardNumber(card.getCardNumber());
        dto.setType(card.getType());
        dto.setExpirationDate(card.getExpirationDate());
        dto.setStatus(card.getStatus());
        return dto;
    }

    public CardResponseDTO declareCardLost(CardLossRequestDTO dto) {
        Card card = cardRepository.findById(dto.getCardId())
                .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setStatus("LOST");
        cardRepository.save(card);
        // TODO: notifier l'administration ici (email, notification, etc.)
        return toDTO(card);
    }
}