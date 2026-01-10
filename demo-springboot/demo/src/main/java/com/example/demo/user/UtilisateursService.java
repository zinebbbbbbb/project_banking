package com.example.demo.user;


import com.example.demo.Client.Client;
import com.example.demo.Client.ClientRepository;
import com.example.demo.Client.ClientRequestDTO;
import com.example.demo.security.JwtService;
import com.example.demo.security.Utilisateurs;
import com.example.demo.security.UtilisateursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateursService {
    private final ClientRepository clientRepository;
    private final UtilisateursRepository utilisateursRepository;
    private final JwtService jwtService;


    public ClientResponseDTO completeProfile(String authHeader, ClientRequestDTO dto) {
        Utilisateurs user = getUserFromHeader(authHeader);

        if (clientRepository.findByUtilisateur(user).isPresent()) {
            throw new RuntimeException("Client profile already exists");
        }

        Client client = new Client();
        client.setUtilisateur(user);
        setClientFields(client, dto);

        Client saved = clientRepository.save(client);
        return mapToResponseDTO(saved);
    }

    // ----- UPDATE CLIENT PROFILE -----
    public ClientResponseDTO updateClient(String authHeader, ClientRequestDTO dto) {
        Utilisateurs user = getUserFromHeader(authHeader);

        Client client = clientRepository.findByUtilisateur(user)
                .orElseThrow(() -> new RuntimeException("Client profile does not exist"));

        setClientFields(client, dto);
        Client updated = clientRepository.save(client);
        return mapToResponseDTO(updated);
    }



    // ----- HELPER: extract email & get user -----
    private Utilisateurs getUserFromHeader(String authHeader) {
        if (!authHeader.startsWith("Bearer ")) throw new RuntimeException("Invalid Authorization header");
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return utilisateursRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ----- HELPER: set fields -----
    private void setClientFields(Client client, ClientRequestDTO dto) {
        client.setPhone(dto.getPhone());
        client.setBirthday(dto.getBirthday());
        client.setLimitBal(dto.getLimitBal());
        client.setSex(dto.getSex());
        client.setEducation(dto.getEducation());
        client.setMarriage(dto.getMarriage());
        client.setAge(dto.getAge());
    }

    // ----- HELPER: map to DTO -----
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
