package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PhotoService photoService;


    @GetMapping("{userId}/paged")
    public ResponseEntity<List<Long>> getPhotosPaged(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String category) {
        List<Photo> allPhotos = photoService.findAllByUserId(userId).orElse(Collections.emptyList());

        if (category != null && !category.isEmpty()) {
            if (!Objects.equals(category, "All")) {
                allPhotos = allPhotos.stream()
                        .filter(photo -> photo.getCategory()!=null)
                        .filter(photo -> category.equals(photo.getCategory().getName()))
                        .toList();
            }
        }

        List<Long> allPhotoIds = allPhotos.stream()
                .map(Photo::getId)
                .collect(Collectors.toList());
        int start = page;
        int end = Math.min(page + size, allPhotoIds.size());

        if (start > allPhotoIds.size()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<Long> pagedPhotoIds = allPhotoIds.subList(start, end);

        return ResponseEntity.ok(pagedPhotoIds);
    }


}
