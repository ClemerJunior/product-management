package com.clemer.stock.controllers;

import com.clemer.stock.domain.dtos.AuthRequestDTO;
import com.clemer.stock.domain.dtos.AuthResponseDTO;
import com.clemer.stock.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public AuthResponseDTO authenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        return authenticationService.authenticate(authRequestDTO);
    }

}
