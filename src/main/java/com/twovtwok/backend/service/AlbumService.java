package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Album;
import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.rep.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void deleteAlbum(Album album) {
        albumRepository.delete(album);
    }

    public Album updateAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void addPhotoToAlbum(Album album, Photo photo) {
        album.getPhotos().add(photo);
        albumRepository.save(album);
    }

}
