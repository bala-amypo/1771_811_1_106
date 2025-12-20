package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceEvaluationService;
import com.example.demo.service.ComplianceLogService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final ComplianceThresholdRepository thresholdRepository;
    private final ComplianceLogService complianceLogService;

    public ComplianceEvaluationServiceImpl(ComplianceThresholdRepository thresholdRepository,
                                           ComplianceLogService complianceLogService) {
        this.thresholdRepository = thresholdRepository;
        this.complianceLogService = complianceLogService;
    }

    @Override
    public ComplianceLog evaluateCompliance(SensorReading reading) {

        ComplianceThreshold threshold =
                thresholdRepository.findBySensorType(
                        reading.getSensor().getSensorType()
                );

        ComplianceLog log = new ComplianceLog();
        log.setSensor(reading.getSensor());
        log.setReadingValue(reading.getReadingValue());

        if (reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue()) {
            log.setStatus("COMPLIANT");
        } else {
            log.setStatus("NON_COMPLIANT");
        }

        return complianceLogService.saveLog(log);
    }
}
