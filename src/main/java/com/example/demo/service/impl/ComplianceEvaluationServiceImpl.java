package com.example.demo.service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final ComplianceThresholdService thresholdService;
    private final ComplianceLogService logService;

    public ComplianceEvaluationServiceImpl(ComplianceThresholdService thresholdService,
                                           ComplianceLogService logService) {
        this.thresholdService = thresholdService;
        this.logService = logService;
    }

    @Override
    public ComplianceLog evaluateCompliance(SensorReading reading) {

        ComplianceThreshold threshold =
                thresholdService.getBySensorType(
                        reading.getSensor().getSensorType());

        ComplianceLog log = new ComplianceLog();
        log.setSensor(reading.getSensor());
        log.setReadingValue(reading.getReadingValue());
        log.setLoggedAt(LocalDateTime.now());

        if (reading.getReadingValue() < threshold.getMinValue()
                || reading.getReadingValue() > threshold.getMaxValue()) {

            log.setComplianceStatus("NON_COMPLIANT");
            log.setSeverityLevel(threshold.getSeverityLevel());
        } else {
            log.setComplianceStatus("COMPLIANT");
            log.setSeverityLevel("NONE");
        }

        return logService.saveLog(log);
    }
}
