package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Override
    public String evaluateReading(SensorReading reading, ComplianceThreshold threshold) {

        if (reading == null || reading.getReadingValue() == null) {
            throw new InvalidRequestException("Reading cannot be null");
        }

        if (threshold == null) {
            throw new InvalidRequestException("Threshold is required");
        }

        double value = reading.getReadingValue();
        double min = threshold.getMinValue();
        double max = threshold.getMaxValue();

        if (value < min) return "LOW";
        if (value > max) return "HIGH";
        return "NORMAL";
    }
}
