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

    private final SensorReadingRepository readingRepository;
    private final SensorRepository sensorRepository;

    public SensorReadingServiceImpl(SensorReadingRepository readingRepository,
                                    SensorRepository sensorRepository) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public SensorReading submitReading(Long sensorId, SensorReading reading) {

        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));

        reading.setSensor(sensor);
        reading.setReadingTime(LocalDateTime.now());

        return readingRepository.save(reading);
    }

    @Override
    public SensorReading getReading(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));
    }

    @Override
    public List<SensorReading> getReadingsBySensor(Long sensorId) {
        return readingRepository.findBySensorId(sensorId);
    }

    @Override
    public List<SensorReading> listSensorReadings() {
        return readingRepository.findAll();
    }

    @Override
    public SensorReading getSensorReadingById(Long id) {
        return getReading(id);
    }

    @Override
    public SensorReading createSensorReading(SensorReading sensorReading) {
        sensorReading.setReadingTime(LocalDateTime.now());
        return readingRepository.save(sensorReading);
    }
}
