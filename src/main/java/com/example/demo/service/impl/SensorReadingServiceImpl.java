package com.example.demo.service.impl;

import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.SensorReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository readingRepository;

    public SensorReadingServiceImpl(SensorReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Override
    public SensorReading createReading(SensorReading reading) {
        return readingRepository.save(reading);
    }

    @Override
    public SensorReading getReading(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor reading not found"));
    }

    @Override
    public List<SensorReading> getAllReadings() {
        return readingRepository.findAll();
    }
}
