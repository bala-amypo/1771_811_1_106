package com.example.demo.service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;

public interface ComplianceEvaluationService {

    ComplianceLog evaluateCompliance(SensorReading reading);
}
