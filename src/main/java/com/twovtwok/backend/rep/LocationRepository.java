package com.twovtwok.backend.rep;

import com.twovtwok.backend.dao.Location;
import com.twovtwok.backend.dao.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findLocationByLatitudeAndLongitude(double lat,double lng);
}
