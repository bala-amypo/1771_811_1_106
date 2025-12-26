package com.example.demo.repository;

import com.example.demo.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

    List<Sensor> findBySensorCode(String code);

    List<Sensor> findByLocation_LocationName(String locationName);

    List<Sensor> findByLocation_Region(String region);
}
