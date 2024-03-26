package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.rep.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

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
