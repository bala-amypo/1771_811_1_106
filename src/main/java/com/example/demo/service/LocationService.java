package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Location;
import com.example.demo.repository.LocationRepository;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        String region = location.getRegion();

        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Region is required");
        }

        return locationRepository.save(location);
    }

    public List<Location> listLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Location not found"));
    }
}
