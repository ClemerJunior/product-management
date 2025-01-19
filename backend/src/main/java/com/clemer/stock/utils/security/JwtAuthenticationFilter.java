package com.clemer.stock.utils.security;

import com.clemer.stock.domain.entities.User;
import com.clemer.stock.services.UserService;
import com.clemer.stock.utils.DateUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenManager tokenManager;
    private final UserService  userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        tokenManager.getTokenFromRequest(request)
                .ifPresent(jwt -> {
                    if (tokenManager.isValid(jwt)) {
                        final var userId = tokenManager.getUserIdFromToken(jwt);
                        final var userDetails = userService.loadUserById(userId);
                        final var dataAcessoBanco = DateUtil.getLocalDateTimeWithZeroMillis(((User) userDetails).getLastAccess());

                        if (!tokenManager.getTimeOfGeneration(jwt).isBefore(dataAcessoBanco)) {
                            final var authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                    null, userDetails.getAuthorities());

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                });
        filterChain.doFilter(request, response);
    }
}
