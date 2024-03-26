package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.service.PhotoService;
import com.twovtwok.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GalleryController {

    private final PhotoService photoService;
    private final UserService userService;

    @GetMapping("/photos")
    public ResponseEntity<List<Long>> getPhotos(){
        List<Long> photoIds = photoService.getAllPhotos().stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(photoIds);
    }


}
