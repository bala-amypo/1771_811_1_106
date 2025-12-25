package com.example.demo.service.impl;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorReadingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository readingRepository;
    private final SensorRepository sensorRepository;

    public SensorReadingServiceImpl(
            SensorReadingRepository readingRepository,
            SensorRepository sensorRepository
    ) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public SensorReading addReading(Long sensorId, SensorReading reading) {

        if (reading.getReadingValue() == null) {
            throw new InvalidRequestException("Reading value cannot be null");
        }

        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sensor not found with id: " + sensorId));

        reading.setSensor(sensor);

        return readingRepository.save(reading);
    }

    @Override
    public SensorReading getReadingById(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reading not found with id: " + id));
    }

    @Override
    public List<SensorReading> getAllReadings() {
        return readingRepository.findAll();
    }

    @Override
    public List<SensorReading> getReadingsBySensor(Long sensorId) {

        sensorRepository.findById(sensorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sensor not found with id: " + sensorId));

        return readingRepository.findBySensorId(sensorId);
    }
}
