package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    private final ComplianceThresholdRepository repo;

    public ComplianceThresholdServiceImpl(ComplianceThresholdRepository repo) {
        this.repo = repo;
    }

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold t) {
        if (t.getMinValue() >= t.getMaxValue()) {
            throw new IllegalArgumentException("minvalue must be less than max");
        }
        return repo.save(t);
    }

    @Override
    public ComplianceThreshold getThreshold(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public ComplianceThreshold getThresholdBySensorType(String sensorType) {
        return repo.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return repo.findAll();
    }
}
