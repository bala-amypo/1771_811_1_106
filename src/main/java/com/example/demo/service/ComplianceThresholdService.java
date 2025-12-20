package com.example.demo.service;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceThresholdService {

    private final ComplianceThresholdRepository repository;

    public ComplianceThresholdService(ComplianceThresholdRepository repository) {
        this.repository = repository;
    }

    public ComplianceThreshold create(ComplianceThreshold threshold) {
        if (threshold.getMinValue() >= threshold.getMaxValue()) {
            throw new IllegalArgumentException("minvalue");
        }
        return repository.save(threshold);
    }

    public ComplianceThreshold getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public List<ComplianceThreshold> getAll() {
        return repository.findAll();
    }

    public ComplianceThreshold getBySensorType(String sensorType) {
        return repository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
