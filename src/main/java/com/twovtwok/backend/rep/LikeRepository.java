package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Like;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    Optional<List<Like>> findAllByPhoto(Photo photo);
}
