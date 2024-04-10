package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Like;
import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.rep.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public List<Like> findAllByPhoto(Photo photo) {
        Optional<List<Like>> likes = likeRepository.findAllByPhoto(photo);
        return likes.orElse(null);
    }

    public Like saveLike(Like like) {
        return likeRepository.save(like);
    }

    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }

    public Like updateLike(Like like) {
        return likeRepository.save(like);
    }
}
