package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.service.ComplianceEvaluationService;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.stereotype.Service;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final ComplianceThresholdService thresholdService;
    private final ComplianceLogRepository logRepository;

    public ComplianceEvaluationServiceImpl(ComplianceThresholdService thresholdService,
                                           ComplianceLogRepository logRepository) {
        this.thresholdService = thresholdService;
        this.logRepository = logRepository;
    }

    @Override
    public ComplianceLog evaluateReading(SensorReading reading) {

        String sType = reading.getSensor().getType();

        ComplianceThreshold threshold = thresholdService.getBySensorType(sType);

        ComplianceLog log = new ComplianceLog();
        log.setSensor(reading.getSensor());
        log.setReadingValue(reading.getValue());

        if (reading.getValue() >= threshold.getMinValue() &&
                reading.getValue() <= threshold.getMaxValue()) {
            log.setStatus("COMPLIANT");
        } else {
            log.setStatus("NON-COMPLIANT");
        }

        return logRepository.save(log);
    }
}
