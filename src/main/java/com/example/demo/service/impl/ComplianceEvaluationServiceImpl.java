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
    public ComplianceLog getLog(Long readingId) {
        // Fetch the reading
        SensorReading reading = readingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found with id: " + readingId));

        // Check if log exists for this reading
        List<ComplianceLog> existingLogs = logRepository.findBySensorReading_Id(readingId);
        if (!existingLogs.isEmpty()) {
            return existingLogs.get(0);
        }

        // Fetch threshold for the sensor type
        ComplianceThreshold threshold = thresholdRepository.findBySensorType(reading.getSensor().getSensorType())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Threshold not found for sensor type: " + reading.getSensor().getSensorType()));

        // Create new compliance log
        ComplianceLog log = new ComplianceLog();
        log.setReading(reading);  // <-- matches your entity
        if (reading.getReadingValue() >= threshold.getMinValue() &&
            reading.getReadingValue() <= threshold.getMaxValue()) {
            log.setStatusAssigned("SAFE"); // <-- matches your entity method
        } else {
            log.setStatusAssigned("UNSAFE");
        }

        logRepository.save(log);
        return log;
    }
}
