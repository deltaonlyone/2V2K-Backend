package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Token;
import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.exception.UserAuthenticationException;
import com.twovtwok.backend.exception.UserNotFoundException;
import com.twovtwok.backend.rep.TokenRepository;
import com.twovtwok.backend.rep.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * The plaintext password used to perform {@link PasswordEncoder#matches(CharSequence,
     * String)} on when the user is not found to avoid timing attack.
     */
    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    private String userNotFoundEncodedPassword;

    @PostConstruct
    public void initialize() {
        // prepare timing attack protection
        userNotFoundEncodedPassword = passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
    }

    public User authenticate(String usernameOrEmail, String password) {
        User user;

        try {
            // verify identifier
            user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                    .orElseThrow(() -> new UserNotFoundException("User '" + usernameOrEmail + "' not found"));

            // verify password
            if (!passwordEncoder.matches(password, user.getPassword())) {
                log.info("Credentials authentication failed since password does not match");
                throw new UserAuthenticationException("Credentials is invalid");
            }
        } catch (UserNotFoundException ex) {
            log.info("Credentials authentication failed since user was not found");

            // mitigate against timing attack
            passwordEncoder.matches(password, userNotFoundEncodedPassword);

            throw new UserAuthenticationException("Credentials is invalid");
        }

//        user.eraseCredentials();
        return user;
    }


    public User authenticate(String token) {

        if (token.contains(".")) {
            Long tokenId = Long.valueOf(StringUtils.substringBefore(token, "."));
            Token existingToken = tokenRepository.findById(tokenId).orElseThrow(() -> new UserAuthenticationException("Token not fount"));
            if (token.equals(existingToken.getValue())) {
                User user = userRepository.findById(existingToken.getUserId()).orElseThrow(() -> new UserAuthenticationException("User not found"));
//                user.eraseCredentials();
                return user;
            }
        }

        throw new UserAuthenticationException("Invalid token value");

    }

}
