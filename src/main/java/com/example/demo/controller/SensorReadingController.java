package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {

    private final SensorReadingService readingService;

    public SensorReadingController(SensorReadingService readingService) {
        this.readingService = readingService;
    }

    @PostMapping("/{sensorId}")
    public SensorReading submit(@PathVariable Long sensorId,
                                @RequestBody SensorReading reading) {
        return readingService.submitReading(sensorId, reading);
    }

    @GetMapping("/{id}")
    public SensorReading get(@PathVariable Long id) {
        return readingService.getReading(id);
    }
}
