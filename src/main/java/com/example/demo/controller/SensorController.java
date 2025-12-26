package com.example.demo.controller;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    // Create a new Sensor
    @PostMapping("/sensors")
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorService.createSensor(sensor);
    }

    // Get a Sensor by ID
    @GetMapping("/sensors/{id}")
    public Sensor getSensor(@PathVariable Long id) {
        return sensorService.getSensor(id);
    }

    // Get all Sensors
    @GetMapping("/sensors")
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }
}
