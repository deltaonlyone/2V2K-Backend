package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Comment;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<List<Comment>> findAllByPhoto(Photo photo);
}
