package com.example.demo.service;

import com.example.demo.entity.SensorReading;
import java.util.List;

public interface SensorReadingService {
    SensorReading createReading(SensorReading reading);
    SensorReading getReading(Long id);
    List<SensorReading> getAllReadings();
}
