package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    @Autowired
    private ComplianceThresholdRepository repository;

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return repository.findAll();
    }

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        return repository.save(threshold);
    }

    @Override
    public ComplianceThreshold getThresholdBySensorType(String sensorType) {
        return repository.findBySensorType(sensorType);
    }
}
