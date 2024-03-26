package com.twovtwok.backend.filter;

import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-Authentication");
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try{
            User user = authService.authenticate(token);
            UserPrincipal principal = new UserPrincipal(user);
            TokenAuthenticationToken tokenAuthenticationToken =
                    new TokenAuthenticationToken(token, principal,principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(tokenAuthenticationToken);
        }catch (Exception e){
            log.warn(e.getMessage());
        }
        filterChain.doFilter(request, response);


    }
}
