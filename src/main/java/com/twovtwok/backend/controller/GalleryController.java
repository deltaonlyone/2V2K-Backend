package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.service.PhotoService;
import com.twovtwok.backend.service.UserService;
import jakarta.persistence.Cacheable;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class GalleryController {

    private final PhotoService photoService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<Long>> getPhotos(){
        List<Long> photoIds = photoService.getAllPhotos().stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(photoIds);
    }


    @GetMapping("/homepage")
    public ResponseEntity<List<Long>> getPhotos(@RequestParam("count") int count){
        List<Long> allPhotoIds = photoService.getAllPhotos().stream()
                .map(Photo::getId)
                .collect(Collectors.toList());

        Collections.shuffle(allPhotoIds);

        List<Long> randomPhotoIds = new ArrayList<>();
        for (int i = 0; i < count && i < allPhotoIds.size(); i++) {
            randomPhotoIds.add(allPhotoIds.get(i));
        }

        return ResponseEntity.ok(randomPhotoIds);
    }


}
