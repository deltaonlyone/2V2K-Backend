package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Like;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<List<Like>> findAllByPhoto(Photo photo);
}
