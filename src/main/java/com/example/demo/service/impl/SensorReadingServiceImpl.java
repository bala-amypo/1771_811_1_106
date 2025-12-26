package com.example.demo.service.impl;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorReadingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository readingRepo;
    private final SensorRepository sensorRepo;

    public SensorReadingServiceImpl(SensorReadingRepository readingRepo, SensorRepository sensorRepo) {
        this.readingRepo = readingRepo;
        this.sensorRepo = sensorRepo;
    }

    @Override
    public SensorReading submitReading(Long sensorId, SensorReading reading) {
        Sensor s = sensorRepo.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));

        if (reading.getReadingValue() == null) {
            throw new IllegalArgumentException("readingvalue required");
        }

        if (reading.getReadingTime() != null && reading.getReadingTime().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("readingtime future");
        }

        reading.setSensor(s);
        reading.setStatus("PENDING");
        return readingRepo.save(reading);
    }

    @Override
    public SensorReading getReading(Long id) {
        return readingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));
    }

    @Override
    public List<SensorReading> getReadingsBySensor(Long sensorId) {
        return readingRepo.findBySensor_Id(sensorId);
    }
}
