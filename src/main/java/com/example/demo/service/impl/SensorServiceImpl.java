package com.example.demo.service.impl;

import com.example.demo.entity.Sensor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Sensor createSensor(Sensor sensor) {
        if (sensor.getSensorType() == null || sensor.getSensorType().trim().isEmpty()) {
            throw new RuntimeException("Sensor type cannot be empty");
        }
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sensor not found with id: " + id
                        ));
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}
