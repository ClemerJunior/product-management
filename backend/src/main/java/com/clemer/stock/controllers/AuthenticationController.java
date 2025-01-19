package com.clemer.stock.controllers;

import com.clemer.stock.domain.dtos.AuthRequestDTO;
import com.clemer.stock.domain.entities.User;
import com.clemer.stock.services.AuthenticationService;
import com.clemer.stock.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public String authenticate(@RequestBody AuthRequestDTO authRequestDTO) {
        return authenticationService.authenticate(authRequestDTO);
    }

}
