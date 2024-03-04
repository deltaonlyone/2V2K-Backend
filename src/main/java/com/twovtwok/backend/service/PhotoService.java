package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Photo;

import java.util.List;

public interface PhotoService {
    Photo savePhoto(Photo photo);
    List<Photo> getAllPhotos();
    Photo getPhotoById(Long id);
    void deletePhoto(Long id);
}
