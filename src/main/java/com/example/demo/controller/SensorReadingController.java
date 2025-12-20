package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;

@RestController
@RequestMapping("/sensor-readings")
public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    public SensorReadingController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    // Submit a reading for a sensor
    @PostMapping("/sensor/{sensorId}")
    public SensorReading submitReading(
            @PathVariable Long sensorId,
            @RequestBody SensorReading sensorReading) {

        return sensorReadingService.submitReading(sensorId, sensorReading);
    }

    // Get all readings for a sensor
    @GetMapping("/sensor/{sensorId}")
    public List<SensorReading> getReadingsBySensor(@PathVariable Long sensorId) {
        return sensorReadingService.getReadingsBySensor(sensorId);
    }

    // Get a reading by id
    @GetMapping("/{id}")
    public SensorReading getReading(@PathVariable Long id) {
        return sensorReadingService.getReading(id);
    }
}
