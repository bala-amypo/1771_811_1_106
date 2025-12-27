package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;
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

    @PostMapping
    public ResponseEntity<SensorReading> submitReading(
            @RequestParam Long sensorId,
            @RequestBody SensorReading reading) {
        return ResponseEntity.ok(readingService.submitReading(sensorId, reading));
    }

    @GetMapping
    public ResponseEntity<List<SensorReading>> getReadingsBySensor(
            @RequestParam Long sensorId) {
        return ResponseEntity.ok(readingService.getReadingsBySensor(sensorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorReading> getReadingById(@PathVariable Long id) {
        return ResponseEntity.ok(readingService.getReading(id));
    }
}
