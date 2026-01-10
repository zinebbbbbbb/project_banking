package com.example.demo.Client;

import com.example.demo.security.JwtService;
import com.example.demo.security.Utilisateurs;
import com.example.demo.security.UtilisateursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UtilisateursRepository utilisateurRepository;
    private final JwtService jwtService;

    public ClientResponseDTO createClient(String authHeader, ClientRequestDTO dto) {

        String email = extractEmail(authHeader);
        Utilisateurs user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if client already exists
        if (clientRepository.findByUtilisateur(user).isPresent()) {
            throw new RuntimeException("Client already exists for this user");
        }

        Client client = new Client();
        client.setUtilisateur(user);
        setClientFields(client, dto);

        clientRepository.save(client);

        return mapToResponseDTO(client);
    }

    // ----- UPDATE -----
    public ClientResponseDTO updateClient(String authHeader, ClientRequestDTO dto) {

        String email = extractEmail(authHeader);
        Utilisateurs user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Client client = clientRepository.findByUtilisateur(user)
                .orElseThrow(() -> new RuntimeException("Client profile does not exist"));

        setClientFields(client, dto);
        clientRepository.save(client);

        return mapToResponseDTO(client);
    }

    // ----- HELPER: extract email from JWT -----
    private String extractEmail(String authHeader) {
        if (!authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization header");
        }
        String token = authHeader.substring(7);
        return jwtService.extractUsername(token);
    }

    // ----- HELPER: set client fields -----
    private void setClientFields(Client client, ClientRequestDTO dto) {
        client.setPhone(dto.getPhone());
        client.setBirthday(dto.getBirthday());
        client.setLimitBal(dto.getLimitBal());
        client.setSex(dto.getSex());
        client.setEducation(dto.getEducation());
        client.setMarriage(dto.getMarriage());
        client.setAge(dto.getAge());
    }

    // ----- HELPER: map to response DTO -----
    private ClientResponseDTO mapToResponseDTO(Client client) {
        return ClientResponseDTO.builder()
                .id(client.getId())
                .phone(client.getPhone())
                .birthday(client.getBirthday())
                .limitBal(client.getLimitBal())
                .sex(client.getSex())
                .education(client.getEducation())
                .marriage(client.getMarriage())
                .age(client.getAge())
                .build();
    }
}

