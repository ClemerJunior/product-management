package com.clemer.stock.services;

import com.clemer.stock.domain.dtos.AuthRequestDTO;
import com.clemer.stock.domain.dtos.AuthResponseDTO;
import com.clemer.stock.utils.security.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authManager;
    private final JwtTokenManager jwtTokenManager;

    public AuthResponseDTO authenticate(AuthRequestDTO authRequest) {
        var authentication = authManager.authenticate(authRequest.build());
        String token = jwtTokenManager.generateToken(authentication);

        return new AuthResponseDTO(authRequest.getUsername(), token);
    }
}
