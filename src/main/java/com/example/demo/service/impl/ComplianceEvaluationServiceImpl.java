package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceCheckResult;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.service.ComplianceEvaluationService;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final ComplianceThresholdService thresholdService;

    public ComplianceEvaluationServiceImpl(ComplianceThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    @Override
    public ComplianceCheckResult evaluateReading(SensorReading reading) {

        if (reading.getReadingValue() == null) {
            throw new InvalidRequestException("Reading value cannot be null");
        }

        ComplianceThreshold threshold =
                thresholdService.getBySensorType(reading.getSensorType());

        boolean compliant = reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue();

        return new ComplianceCheckResult(
                compliant,
                compliant ? "Reading is within threshold" : "Reading is out of range"
        );
    }
}
