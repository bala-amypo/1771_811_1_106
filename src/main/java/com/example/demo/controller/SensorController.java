package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    // Create sensor for a specific location
    @PostMapping("/location/{locationId}")
    public Sensor createSensor(
            @PathVariable Long locationId,
            @RequestBody Sensor sensor) {

        return sensorService.createSensor(locationId, sensor);
    }

    // Get all sensors
    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    // Get sensor by id
    @GetMapping("/{id}")
    public Sensor getSensor(@PathVariable Long id) {
        return sensorService.getSensor(id);
    }
}
