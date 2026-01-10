package com.example.demo.user;

import com.example.demo.Client.ClientRequestDTO;
import com.example.demo.security.Utilisateurs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UtilisateursController {


    private final UtilisateursService clientService;


    @PutMapping("/update")
    public ClientResponseDTO updateClient(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ClientRequestDTO dto
    ) {
        return clientService.updateClient(authHeader, dto);
    }

    @PostMapping("/complete-profile")
    public ClientResponseDTO completeProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ClientRequestDTO dto
    ) {
        return clientService.completeProfile(authHeader, dto);
    }
}
