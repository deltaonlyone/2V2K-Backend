package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.dao.User;
import com.twovtwok.backend.service.PhotoService;
import com.twovtwok.backend.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserService userService;
    private final PhotoService photoService;


    @GetMapping("/photographers/paged")
    public ResponseEntity<List<User>> getPagedUserList(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size){
        List<User> allUsers = userService.getAllUsers();

        int start = page * size;
        int end = Math.min(start + size, allUsers.size());

        if (start > allUsers.size()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<User> pagedPhotoIds = allUsers.subList(start, end);

        return ResponseEntity.ok(pagedPhotoIds);
    }

    @GetMapping("/homepage")
    public ResponseEntity<List<Long>> getPhotos(@RequestParam("count") int count){
        List<Long> homepagePhotos = photoService.getAllPhotos().stream()
                .filter(photo -> photo.getCategory()!=null)
                .filter(photo -> photo.getCategory().getName().equals("HomePage") && photo.getUser()==null)
                .map(Photo::getId)
                .collect(Collectors.toList());

        Collections.shuffle(homepagePhotos);

        List<Long> randomPhotoIds = new ArrayList<>();
        for (int i = 0; i < count && i < homepagePhotos.size(); i++) {
            randomPhotoIds.add(homepagePhotos.get(i));
        }

        return ResponseEntity.ok(randomPhotoIds);
    }
}
