package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;

import java.time.LocalDateTime;
import java.util.List;

public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final SensorReadingRepository readingRepository;
    private final ComplianceThresholdRepository thresholdRepository;
    private final ComplianceLogRepository logRepository;

    public ComplianceEvaluationServiceImpl(SensorReadingRepository readingRepository,
                                           ComplianceThresholdRepository thresholdRepository,
                                           ComplianceLogRepository logRepository) {
        this.readingRepository = readingRepository;
        this.thresholdRepository = thresholdRepository;
        this.logRepository = logRepository;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        SensorReading reading = readingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        ComplianceThreshold threshold = thresholdRepository
                .findBySensorType(reading.getSensor().getSensorType())
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        List<ComplianceLog> existingLogs =
                logRepository.findBySensorReading_Id(readingId);

        if (!existingLogs.isEmpty()) {
            return existingLogs.get(0);
        }

        String status = (reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue())
                ? "SAFE" : "UNSAFE";

        ComplianceLog log = new ComplianceLog();
        log.setSensorReading(reading);
        log.setThresholdUsed(threshold);
        log.setStatusAssigned(status);
        log.setLoggedAt(LocalDateTime.now());

        return logRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return logRepository.findBySensorReading_Id(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
