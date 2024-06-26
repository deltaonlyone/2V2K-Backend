package com.twovtwok.backend.controller;

import com.sun.net.httpserver.Headers;
import com.twovtwok.backend.dao.Token;
import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.model.dto.LoginDto;
import com.twovtwok.backend.service.AuthService;
import com.twovtwok.backend.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("authenticate")
    public ResponseEntity<Headers> authenticate(@Validated @RequestBody LoginDto loginDto) {

        User user = authService.authenticate(loginDto.getUsernameOrEmail(), loginDto.getPassword());

        Token token = tokenService.generate(user.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Authentication", token.getValue());
        headers.add("Access-Control-Expose-Headers", "*");
        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("logout")
    public void logout(Principal principal) {
        tokenService.clear(Long.valueOf(principal.getName()));
    }


}
