package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.exception.UserAlreadyExistException;
import com.twovtwok.backend.rep.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public User register(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistException("Username already in use");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException("Email already in use");
        }

//            emailService.sendSimpleMessage("skillful02@gmail.com","Count to 5",""+i);

        user.setPermissions("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public void updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return;
        }
        user.setId(id);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username,email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
