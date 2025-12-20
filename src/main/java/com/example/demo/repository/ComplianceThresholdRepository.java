package com.example.demo.repository;

import com.example.demo.entity.ComplianceThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplianceThresholdRepository
        extends JpaRepository<ComplianceThreshold, Long> {

    ComplianceThreshold findBySensorType(String sensorType);
}
