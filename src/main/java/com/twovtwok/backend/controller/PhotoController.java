package com.twovtwok.backend.controller;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {


    private final PhotoService photoService;
    private final String uploadPath = "photos/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath + fileName);
            Files.write(path, bytes);

            Photo photo = new Photo();
            photo.setFilename(fileName);
            photoService.savePhoto(photo);

            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPhoto(@PathVariable("id") Long id) throws MalformedURLException {
        Path path = Paths.get(uploadPath + photoService.getPhotoById(id).getFilename());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhoto(@PathVariable("id") Long id) {
        Photo photo = photoService.getPhotoById(id);
        if (photo == null) {
            return new ResponseEntity<>("Photo not found", HttpStatus.NOT_FOUND);
        }

        File file = new File(uploadPath + photo.getFilename());
        if (file.delete()) {
            photoService.deletePhoto(id);
            return new ResponseEntity<>("Photo deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete photo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
