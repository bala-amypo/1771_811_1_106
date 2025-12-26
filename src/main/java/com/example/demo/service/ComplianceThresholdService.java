package com.example.demo.service;

import com.example.demo.entity.ComplianceThreshold;
import java.util.List;

public interface ComplianceThresholdService {

    List<ComplianceThreshold> getAllThresholds();

    ComplianceThreshold createThreshold(ComplianceThreshold threshold);

    ComplianceThreshold getThresholdBySensorType(String sensorType);
}
