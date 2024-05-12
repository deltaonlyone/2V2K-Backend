package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Comment;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findAllByPhoto(Photo photo);
}
