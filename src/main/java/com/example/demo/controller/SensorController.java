package com.example.demo.controller;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor) {
        return ResponseEntity.ok(sensorService.createSensor(sensor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.getSensorById(id));
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensors());
    }
}
