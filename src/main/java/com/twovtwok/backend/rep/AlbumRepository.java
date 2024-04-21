package com.twovtwok.backend.rep;


import com.twovtwok.backend.dao.Album;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    Optional<Album> findByPhoto(Photo photo);
}
