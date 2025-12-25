package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    private final ComplianceThresholdRepository repository;

    public ComplianceThresholdServiceImpl(ComplianceThresholdRepository repository) {
        this.repository = repository;
    }

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        if (threshold.getMinValue() >= threshold.getMaxValue()) {
            throw new IllegalArgumentException("minvalue");
        }
        return repository.save(threshold);
    }

    @Override
    public ComplianceThreshold getThreshold(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public ComplianceThreshold getThresholdBySensorType(String sensorType) {
        return repository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return repository.findAll();
    }
}
