package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @GetMapping("/{id}")
    public Location get(@PathVariable Long id) {
        return locationService.getLocation(id);
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAllLocations();
    }
}
