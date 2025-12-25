package com.example.demo.service;

import com.example.demo.entity.SensorReading;
import java.util.List;

public interface SensorReadingService {
    SensorReading addReading(Long sensorId, SensorReading reading);
    List<SensorReading> getReadingsBySensor(Long sensorId);
}
