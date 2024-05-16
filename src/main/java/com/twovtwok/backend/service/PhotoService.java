package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.rep.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {

    
    private final PhotoRepository photoRepository;

    public Optional<List<Photo>> findAllByUserId(Long id) {
        return photoRepository.findAllByUserId(id);
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
