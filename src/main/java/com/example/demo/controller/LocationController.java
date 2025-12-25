package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return service.createLocation(location);
    }

    @GetMapping
    public List<Location> all() {
        return service.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location get(@PathVariable Long id) {
        return service.getLocation(id);
    }
}
