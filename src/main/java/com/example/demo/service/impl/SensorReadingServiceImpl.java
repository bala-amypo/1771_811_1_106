package com.example.demo.service.impl;

import com.example.demo.entity.SensorReading;
import com.example.demo.entity.Sensor;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.SensorReadingService;
import com.example.demo.service.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository readingRepository;
    private final SensorService sensorService;

    public SensorReadingServiceImpl(SensorReadingRepository readingRepository, SensorService sensorService) {
        this.readingRepository = readingRepository;
        this.sensorService = sensorService;
    }

    @Override
    public SensorReading createSensorReading(SensorReading sensorReading) {

        if (sensorReading.getReadingValue() == null) {
            throw new InvalidRequestException("Reading value cannot be null");
        }

        if (sensorReading.getSensor() == null ||
                sensorReading.getSensor().getId() == null) {
            throw new InvalidRequestException("Sensor ID is required");
        }

        // Verify sensor exists
        Sensor sensor = sensorService.getSensorById(sensorReading.getSensor().getId());

        sensorReading.setSensor(sensor);

        return readingRepository.save(sensorReading);
    }

    @Override
    public SensorReading submitReading(Long sensorId, SensorReading reading) {
        if (reading.getReadingValue() == null) {
            throw new InvalidRequestException("Reading value cannot be null");
        }

        Sensor sensor = sensorService.getSensorById(sensorId);
        reading.setSensor(sensor);
        return readingRepository.save(reading);
    }

    @Override
    public SensorReading getReading(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reading not found with id: " + id));
    }

    @Override
    public List<SensorReading> getReadingsBySensor(Long sensorId) {
        sensorService.getSensorById(sensorId); // validate existence
        return readingRepository.findBySensorId(sensorId);
    }

    @Override
    public List<SensorReading> listSensorReadings() {
        return readingRepository.findAll();
    }

    @Override
    public SensorReading getSensorReadingById(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reading not found with id: " + id));
    }
}
