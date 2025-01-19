package com.clemer.stock.services;

import com.clemer.stock.domain.dtos.AuthRequestDTO;
import com.clemer.stock.utils.security.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authManager;
    private final JwtTokenManager jwtTokenManager;

    public String authenticate(AuthRequestDTO authRequest) {
        var authentication = authManager.authenticate(authRequest.build());
        return jwtTokenManager.generateToken(authentication);
    }
}
