package com.example.demo.controller;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @PostMapping("/{locationId}")
    public Sensor create(@PathVariable Long locationId,
                         @RequestBody Sensor sensor) {
        return service.createSensor(locationId, sensor);
    }

    @GetMapping
    public List<Sensor> all() {
        return service.getAllSensors();
    }

    @GetMapping("/{id}")
    public Sensor get(@PathVariable Long id) {
        return service.getSensor(id);
    }
}
