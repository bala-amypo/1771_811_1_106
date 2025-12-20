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

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceEvaluationServiceImpl
        implements ComplianceEvaluationService {

    private final SensorReadingRepository readingRepo;
    private final ComplianceThresholdRepository thresholdRepo;
    private final ComplianceLogRepository logRepo;

    public ComplianceEvaluationServiceImpl(
            SensorReadingRepository r,
            ComplianceThresholdRepository t,
            ComplianceLogRepository l) {
        this.readingRepo = r;
        this.thresholdRepo = t;
        this.logRepo = l;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {

        SensorReading reading = readingRepo.findById(readingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reading not found"));

        ComplianceThreshold threshold =
                thresholdRepo.findBySensorType(
                        reading.getSensor().getSensorType());

        if (threshold == null) {
            throw new ResourceNotFoundException("Threshold not found");
        }

        String status =
                (reading.getReadingValue() < threshold.getMinValue()
                        || reading.getReadingValue() > threshold.getMaxValue())
                        ? "UNSAFE" : "SAFE";

        List<ComplianceLog> logs =
                logRepo.findBySensorReading_Id(readingId);

        ComplianceLog log;
        if (!logs.isEmpty()) {
            log = logs.get(0);
            log.setThresholdUsed(threshold);
            log.setStatusAssigned(status);
            log.setRemarks("Evaluated");
        } else {
            log = new ComplianceLog(
                    reading,
                    threshold,
                    status,
                    "Evaluated",
                    LocalDateTime.now()
            );
        }

        return logRepo.save(log);
    }

    @Override
    public ComplianceLog getComplianceLog(Long id) {
        return logRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Compliance log not found"));
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return logRepo.findBySensorReading_Id(readingId);
    }
}
