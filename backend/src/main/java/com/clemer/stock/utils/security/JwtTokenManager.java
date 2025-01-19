package com.clemer.stock.utils.security;

import com.clemer.stock.configuration.StockProperties;
import com.clemer.stock.domain.entities.User;
import com.clemer.stock.utils.DateUtil;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenManager {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String PREFIX_AUTHORIZATION_HEADER = "Bearer ";

    private final StockProperties properties;

    public String generateToken(final Authentication authentication) {

        final var user = (User) authentication.getPrincipal();
        final var dataAcesso = DateUtil.localDateTimeToDate(user.getLastAccess());
        final var expiration = new Date(dataAcesso.getTime()+ properties.getJwt().getExpirationInMillis());

        return Jwts.builder()
                .setIssuer("stock")
                .setSubject(user.getId().toString())
                .setIssuedAt(dataAcesso)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, properties.getJwt().getSecret())
                .compact();

    }

    public Optional<String> getTokenFromRequest(HttpServletRequest request) {
        final var bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        return (StringUtils.hasText(bearerToken) && bearerToken.startsWith(PREFIX_AUTHORIZATION_HEADER)) ?
                Optional.of(bearerToken.substring(7)) : Optional.empty();
    }

    public boolean isValid(final String jwt) {
        try {
            Jwts.parser().setSigningKey(properties.getJwt().getSecret()).parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String jwt) {
        final var claims = Jwts.parser().setSigningKey(properties.getJwt().getSecret()).parseClaimsJws(jwt).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public LocalDateTime getTimeOfGeneration(String jwt) {
        final var claims = Jwts.parser().setSigningKey(properties.getJwt().getSecret()).parseClaimsJws(jwt).getBody();
        return DateUtil.dateToLocalDateTime(claims.getIssuedAt());
    }
}
