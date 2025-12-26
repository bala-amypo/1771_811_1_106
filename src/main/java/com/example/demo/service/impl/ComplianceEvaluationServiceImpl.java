package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final SensorReadingRepository readingRepo;
    private final ComplianceThresholdRepository thresholdRepo;
    private final ComplianceLogRepository logRepo;

    public ComplianceEvaluationServiceImpl(
            SensorReadingRepository readingRepo,
            ComplianceThresholdRepository thresholdRepo,
            ComplianceLogRepository logRepo) {
        this.readingRepo = readingRepo;
        this.thresholdRepo = thresholdRepo;
        this.logRepo = logRepo;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        SensorReading r = readingRepo.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        List<ComplianceLog> existing = logRepo.findBySensorReading_Id(readingId);
        if (!existing.isEmpty()) {
            return existing.get(0);
        }

        ComplianceThreshold t = thresholdRepo.findBySensorType(r.getSensor().getSensorType())
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        String status = (r.getReadingValue() >= t.getMinValue() && r.getReadingValue() <= t.getMaxValue())
                ? "SAFE" : "UNSAFE";

        ComplianceLog log = new ComplianceLog();
        log.setSensorReading(r);
        log.setThresholdUsed(t);
        log.setStatusAssigned(status);

        return logRepo.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return logRepo.findBySensorReading_Id(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return logRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
