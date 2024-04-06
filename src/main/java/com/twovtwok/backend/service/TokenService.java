package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Token;
import com.twovtwok.backend.rep.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private static final char[] ALPHANUMERIC_CHARS = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    private final TokenRepository tokenRepository;
    private static final SecureRandom secureRandom = new SecureRandom();

    public Token generate(Long userId) {

        Token token = Token.builder()
                .userId(userId)
                .value(randomString(32))
                .expireAt(LocalDateTime.now().plusHours(1))
                .build();

        token = tokenRepository.save(token);
        token.setValue(token.getId()+"."+token.getValue());
        return tokenRepository.save(token);
    }

    public Token generateRefreshToken(Long userId) {

        Token token = Token.builder()
                .userId(userId)
                .value(randomString(32))
                .expireAt(LocalDateTime.now().plusMonths(6))
                .build();

        token = tokenRepository.save(token);
        token.setValue(token.getId()+"."+token.getValue());
        return tokenRepository.save(token);
    }


    public void clear(Long userId) {
        log.debug("Clear auth tokens for user: {}", userId);

        tokenRepository.deleteByUserId(userId);
    }

    public List<Token> getAllTokens(){
        return tokenRepository.findAll();
    }

    private static String randomString(int length) {
        return RandomStringUtils.random(length, 0, 0, true, true, ALPHANUMERIC_CHARS, secureRandom);
    }

}
