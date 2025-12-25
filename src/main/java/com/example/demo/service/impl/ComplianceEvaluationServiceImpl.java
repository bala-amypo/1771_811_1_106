package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceCheckResult;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final ComplianceThresholdRepository thresholdRepository;

    public ComplianceEvaluationServiceImpl(ComplianceThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    @Override
    public ComplianceCheckResult evaluateReading(SensorReading reading) {

        if (reading == null) {
            throw new IllegalArgumentException("Reading cannot be null");
        }

        if (reading.getSensor() == null) {
            throw new IllegalArgumentException("Sensor data missing in reading");
        }

        String type = reading.getSensor().getSensorType();

        ComplianceThreshold threshold = thresholdRepository.findBySensorType(type)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No threshold found for sensor type: " + type));

        boolean isCompliant =
                reading.getReadingValue() >= threshold.getMinValue()
                        && reading.getReadingValue() <= threshold.getMaxValue();

        ComplianceCheckResult result = new ComplianceCheckResult();

        result.setReadingId(reading.getId());
        result.setSensorType(type);
        result.setCompliant(isCompliant);
        result.setMessage(isCompliant ? "Reading is within safe limits"
                                      : "Reading exceeds allowed threshold");

        return result;
    }
}
