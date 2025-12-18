package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Location;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
public Location createLocation(Location location) {
    if (location.getRegion() == null || location.getRegion().isBlank()) {
        throw new IllegalArgumentException("Region is required");
    }
    return locationRepository.save(location);
}


    @Override
    public List<Location> listLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Location not found"));
    }
}
