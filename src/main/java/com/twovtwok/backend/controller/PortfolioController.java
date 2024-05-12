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
            @RequestParam(defaultValue = "10") int size) {
        List<Long> allPhotoIds = Objects.requireNonNull(photoService.findAllByUserId(userId).orElse(null)).stream()
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
