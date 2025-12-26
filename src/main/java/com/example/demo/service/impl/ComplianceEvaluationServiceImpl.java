package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

        // Check if log already exists for this reading
        List<ComplianceLog> existingLogs = logRepository.findBySensorReading_Id(readingId);
        if (!existingLogs.isEmpty()) {
            return existingLogs.get(0);
        }

        ComplianceThreshold threshold = thresholdRepository.findBySensorType(reading.getSensor().getSensorType())
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        ComplianceLog log = new ComplianceLog();
        log.setReading(reading);

        double value = reading.getReadingValue();
        if (value < threshold.getMinValue() || value > threshold.getMaxValue()) {
            log.setStatusAssigned("UNSAFE");
        } else {
            log.setStatusAssigned("SAFE");
        }

        return logRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return logRepository.findByReading_Id(readingId);

    }

    @Override
    public ComplianceLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compliance log not found"));
    }
}
