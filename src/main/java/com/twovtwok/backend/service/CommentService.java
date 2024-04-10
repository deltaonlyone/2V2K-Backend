package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Comment;
import com.twovtwok.backend.dao.Photo;
import com.twovtwok.backend.rep.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> findAllByPhoto(Photo photo) {
        Optional<List<Comment>> comments = commentRepository.findAllByPhoto(photo);
        return comments.orElse(null);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
