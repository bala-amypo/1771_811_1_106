package com.example.demo.service;

import com.example.demo.entity.SensorReading;
import com.example.demo.entity.ComplianceCheckResult;

public interface ComplianceEvaluationService {

    ComplianceCheckResult evaluateReading(SensorReading reading);
}
