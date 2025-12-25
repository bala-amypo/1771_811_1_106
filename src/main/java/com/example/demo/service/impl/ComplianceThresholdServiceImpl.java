package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    private final ComplianceThresholdRepository thresholdRepository;

    public ComplianceThresholdServiceImpl(ComplianceThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    @Override
    public ComplianceThreshold saveThreshold(ComplianceThreshold threshold) {

        if (threshold.getSensorType() == null || threshold.getSensorType().trim().isEmpty()) {
            throw new IllegalArgumentException("Sensor type cannot be empty");
        }

        return thresholdRepository.save(threshold);
    }

    @Override
    public ComplianceThreshold getThresholdById(Long id) {
        return thresholdRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Threshold not found with id: " + id));
    }

    @Override
    public ComplianceThreshold getBySensorType(String sensorType) {
        return thresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No threshold found for sensor type: " + sensorType));
    }

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }
}
