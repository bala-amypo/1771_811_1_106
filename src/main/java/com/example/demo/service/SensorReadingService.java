package com.example.demo.service;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorReadingService {

    private final SensorReadingRepository readingRepository;
    private final SensorRepository sensorRepository;

    public SensorReadingService(SensorReadingRepository readingRepository,
                                SensorRepository sensorRepository) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    public SensorReading submitReading(Long sensorId, SensorReading reading) {
        if (reading.getReadingValue() == null) {
            throw new IllegalArgumentException("readingvalue");
        }

        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        reading.setSensor(sensor);
        reading.setReadingTime(LocalDateTime.now());

        return readingRepository.save(reading);
    }

    public SensorReading getReading(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<SensorReading> listSensorReadings() {
        return readingRepository.findAll();
    }
}
