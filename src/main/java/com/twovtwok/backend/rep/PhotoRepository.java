package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Category;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<List<Photo>> findAllByCategory(Category category);
    Optional<List<Photo>> findAllByUserId(Long id);

}
