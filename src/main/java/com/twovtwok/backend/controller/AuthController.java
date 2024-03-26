package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Token;
import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.model.dto.LoginDto;
import com.twovtwok.backend.service.AuthService;
import com.twovtwok.backend.service.TokenService;
import com.twovtwok.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("authenticate")
    public ResponseEntity<Void> authenticate(@Validated @RequestBody LoginDto loginDto) {

        User user = authService.authenticate(loginDto.getUsernameOrEmail(), loginDto.getPassword());

        Token token = tokenService.generate(user.getId());

        return ResponseEntity.ok().header("X-Authentication", token.getValue()).build();
    }

    @GetMapping("logout")
    public void logout(Principal principal) {
        tokenService.clear(Long.valueOf(principal.getName()));
    }


}
