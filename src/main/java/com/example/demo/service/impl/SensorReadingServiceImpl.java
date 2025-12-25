package com.example.demo.service.impl;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorRepository sensorRepository;
    private final SensorReadingRepository readingRepository;

    public SensorReadingServiceImpl(SensorRepository sensorRepository,
                                    SensorReadingRepository readingRepository) {
        this.sensorRepository = sensorRepository;
        this.readingRepository = readingRepository;
    }

    @Override
    public SensorReading addReading(SensorReading reading) {

        Long sensorId = reading.getSensor().getId();
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with ID: " + sensorId));

        reading.setSensor(sensor);
        return readingRepository.save(reading);
    }

    @Override
    public List<SensorReading> getReadingsBySensor(Long sensorId) {
        return readingRepository.findBySensorId(sensorId);
    }
}
