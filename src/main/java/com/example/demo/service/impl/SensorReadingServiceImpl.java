package com.example.demo.service.impl;

import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.SensorReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository repo;

    public SensorReadingServiceImpl(SensorReadingRepository repo) {
        this.repo = repo;
    }

    @Override
    public SensorReading addReading(Long sensorId, SensorReading reading) {
        reading.setSensorId(sensorId);
        return repo.save(reading);
    }

    @Override
    public SensorReading getReadingById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found with id: " + id));
    }

    @Override
    public List<SensorReading> getAllReadings() {
        return repo.findAll();
    }
}
