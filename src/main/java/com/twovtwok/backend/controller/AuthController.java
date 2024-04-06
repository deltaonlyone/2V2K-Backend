package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Token;
import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.model.dto.LoginDto;
import com.twovtwok.backend.service.AuthService;
import com.twovtwok.backend.service.EmailService;
import com.twovtwok.backend.service.TokenService;
import com.twovtwok.backend.service.UserService;
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
    private final UserService userService;
    private final TokenService tokenService;
    private final EmailService emailService;

    @PostMapping("authenticate")
    public ResponseEntity<Void> authenticate(@Validated @RequestBody LoginDto loginDto) {
        emailService.sendSimpleMessage("deltaonlyone@gmail.com","hi","hello world");
        User user = authService.authenticate(loginDto.getUsernameOrEmail(), loginDto.getPassword());

        Token token = tokenService.generate(user.getId());
        Token refreshToken = tokenService.generateRefreshToken(user.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Authentication", token.getValue());
        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("logout")
    public void logout(Principal principal) {
        tokenService.clear(Long.valueOf(principal.getName()));
    }


}
