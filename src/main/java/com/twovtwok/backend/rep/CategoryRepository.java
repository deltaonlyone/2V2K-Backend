package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Category;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends CrudRepository<Category, Long> {
    Optional<Category> findByPhoto(Photo photo);
}
