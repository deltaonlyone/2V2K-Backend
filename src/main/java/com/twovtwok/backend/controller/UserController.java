package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.model.dto.GetUserDto;
import com.twovtwok.backend.service.AuthService;
import com.twovtwok.backend.service.PhotoService;
import com.twovtwok.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PhotoService photoService;
    private final AuthService authService;


    @GetMapping("/{id}")
    public ResponseEntity<GetUserDto> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GetUserDto getUserDto= GetUserDto.builder()
                .id(user.getId())
                .bio(user.getBio())
                .phone(user.getPhone())
                .price(user.getPrice())
                .city(user.getCity())
                .country(user.getCountry())
                .email(user.getEmail())
                .name(user.getName())
                .permissions(user.getPermissions())
                .profilePhoto(user.getProfilePhoto())
                .telegramLink(user.getTelegramLink())
                .whatsappLink(user.getWhatsappLink())
                .username(user.getUsername())
                .build();
        return ResponseEntity.ok(getUserDto);
    }

    @GetMapping("/my")
    public ResponseEntity<GetUserDto> getUserByToken(@RequestHeader("X-Authorization") String token) {
            User user = userService.getUserById(authService.authenticate(token).getId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GetUserDto getUserDto= GetUserDto.builder()
                .id(user.getId())
                .bio(user.getBio())
                .phone(user.getPhone())
                .price(user.getPrice())
                .city(user.getCity())
                .country(user.getCountry())
                .email(user.getEmail())
                .name(user.getName())
                .permissions(user.getPermissions())
                .profilePhoto(user.getProfilePhoto())
                .telegramLink(user.getTelegramLink())
                .whatsappLink(user.getWhatsappLink())
                .username(user.getUsername())
                .build();
        return ResponseEntity.ok(getUserDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userService.updateUser(id, updatedUser);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        if (userService.getUserById(id) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void singUp(@RequestBody User user) {
        userService.register(user);
    }

    @PostMapping("/addIcon/{id}")
    public ResponseEntity<String> uploadPhoto(
            @RequestParam("file") MultipartFile file
            , @PathVariable("id") Long id) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get("photos/" + fileName);
            Files.write(path, bytes);

            User user = userService.getUserById(id);

            Photo photo = new Photo();
            photo.setFilename(fileName);
            photoService.savePhoto(photo);
            user.setProfilePhoto(photo);
            userService.updateUser(id,user);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
