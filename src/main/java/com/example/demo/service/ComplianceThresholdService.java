package com.example.demo.service;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.repository.ComplianceThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceThresholdService {

    @Autowired
    private ComplianceThresholdRepository repository;

    public List<ComplianceThreshold> getAllThresholds() {
        return repository.findAll();
    }

    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        return repository.save(threshold);
    }

    public ComplianceThreshold getThresholdBySensorType(String sensorType) {
        return repository.findBySensorType(sensorType);
    }
}
