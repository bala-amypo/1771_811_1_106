package com.example.demo.service.impl;

import com.example.demo.entity.Location;
import com.example.demo.entity.Sensor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepo;
    private final LocationRepository locationRepo;

    public SensorServiceImpl(SensorRepository sensorRepo, LocationRepository locationRepo) {
        this.sensorRepo = sensorRepo;
        this.locationRepo = locationRepo;
    }

    @Override
    public Sensor createSensor(Long locationId, Sensor sensor) {
        Location l = locationRepo.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));

        if (sensor.getSensorType() == null || sensor.getSensorType().isBlank()) {
            throw new IllegalArgumentException("sensorType");
        }

        sensor.setLocation(l);
        return sensorRepo.save(sensor);
    }

    @Override
    public Sensor getSensor(Long id) {
        return sensorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepo.findAll();
    }
}
