package com.example.demo.service;

import com.example.demo.entity.Location;
import com.example.demo.entity.Sensor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final LocationRepository locationRepository;

    public SensorService(SensorRepository sensorRepository,
                         LocationRepository locationRepository) {
        this.sensorRepository = sensorRepository;
        this.locationRepository = locationRepository;
    }

    public Sensor create(Long locationId, Sensor sensor) {
        if (sensor.getSensorType() == null || sensor.getSensorType().isEmpty()) {
            throw new IllegalArgumentException("sensorType");
        }

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        sensor.setLocation(location);
        return sensorRepository.save(sensor);
    }

    public Sensor getById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }
}
