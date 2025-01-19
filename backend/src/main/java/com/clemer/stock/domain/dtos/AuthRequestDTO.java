package com.clemer.stock.domain.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class AuthRequestDTO {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }
}
