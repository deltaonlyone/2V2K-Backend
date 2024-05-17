package com.twovtwok.backend.service;

import com.twovtwok.backend.dao.Location;
import com.twovtwok.backend.rep.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public Optional<Location>  findLocationByLatitudeAndLongitude(double lat, double lng){
        return locationRepository.findLocationByLatitudeAndLongitude(lat,lng);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(Location location) {
        locationRepository.delete(location);
    }

    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }
}
