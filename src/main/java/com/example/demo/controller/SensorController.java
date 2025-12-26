package com.example.demo.controller;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    // CREATE sensor (locationId comes from query param or path param)
    @PostMapping
    public ResponseEntity<Sensor> createSensor(
            @RequestParam Long locationId,
            @RequestBody Sensor sensor) {
        return ResponseEntity.ok(sensorService.createSensor(locationId, sensor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.getSensor(id));
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensors());
    }
}
