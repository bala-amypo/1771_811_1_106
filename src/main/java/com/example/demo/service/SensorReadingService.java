package com.example.demo.service;

import com.example.demo.entity.SensorReading;
import java.util.List;

public interface SensorReadingService {

    SensorReading submitReading(Long sensorId, SensorReading reading);

    SensorReading getReading(Long id);

    List<SensorReading> getReadingsBySensor(Long sensorId);

    List<SensorReading> listSensorReadings();

    SensorReading getSensorReadingById(Long id);

    SensorReading createSensorReading(SensorReading sensorReading);
}
