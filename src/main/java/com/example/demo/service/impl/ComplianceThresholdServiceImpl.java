package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    private final ComplianceThresholdRepository repository;

    public ComplianceThresholdServiceImpl(ComplianceThresholdRepository repository) {
        this.repository = repository;
    }

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        if (threshold.getSensorType() == null || threshold.getSensorType().trim().isEmpty()) {
            throw new RuntimeException("Sensor type cannot be empty");
        }
        return repository.save(threshold);
    }

    @Override
    public ComplianceThreshold getBySensorType(String type) {
        return repository.findBySensorType(type)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No threshold found for sensor type: " + type
                        ));
    }
}
