package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {

    private final SensorReadingService readingService;

    public SensorReadingController(SensorReadingService readingService) {
        this.readingService = readingService;
    }

    // Create a new sensor reading
    @PostMapping
    public ResponseEntity<SensorReading> createReading(@RequestBody SensorReading reading) {
        SensorReading createdReading = readingService.createReading(reading);
        return new ResponseEntity<>(createdReading, HttpStatus.CREATED);
    }

    // Get a sensor reading by ID
    @GetMapping("/{id}")
    public ResponseEntity<SensorReading> getReading(@PathVariable Long id) {
        SensorReading reading = readingService.getReading(id);
        return new ResponseEntity<>(reading, HttpStatus.OK);
    }

    // Get all sensor readings
    @GetMapping
    public ResponseEntity<List<SensorReading>> getAllReadings() {
        List<SensorReading> readings = readingService.getAllReadings();
        return new ResponseEntity<>(readings, HttpStatus.OK);
    }
}
