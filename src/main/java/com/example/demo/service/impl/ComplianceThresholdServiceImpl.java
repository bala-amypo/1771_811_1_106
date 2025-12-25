package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    private final ComplianceThresholdRepository repo;

    public ComplianceThresholdServiceImpl(ComplianceThresholdRepository repo) {
        this.repo = repo;
    }

    @Override
    public ComplianceThreshold saveThreshold(ComplianceThreshold threshold) {
        return repo.save(threshold);
    }

    @Override
public ComplianceThreshold getThresholdById(Long id) {
    return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Threshold not found with id: " + id));
}

    @Override
    public ComplianceThreshold getBySensorType(String sensorType) {
        return repo.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("No threshold found for sensor type: " + sensorType));
    }
}

