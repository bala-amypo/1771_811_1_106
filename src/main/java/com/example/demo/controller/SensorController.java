package com.example.demo.controller;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;
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
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorService.createSensor(sensor);
    }

    @GetMapping("/{id}")
    public Sensor getSensorById(@PathVariable Long id) {
        return sensorService.getSensorById(id);
    }

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }
}
