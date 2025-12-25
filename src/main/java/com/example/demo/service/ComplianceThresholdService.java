package com.example.demo.service;

import com.example.demo.entity.ComplianceThreshold;

public interface ComplianceThresholdService {
    ComplianceThreshold saveThreshold(ComplianceThreshold threshold);
    ComplianceThreshold getBySensorType(String sensorType);
}
