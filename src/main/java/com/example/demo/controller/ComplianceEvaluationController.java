package com.example.demo.service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final ReadingRepository readingRepository;

    @Autowired
    public ComplianceEvaluationServiceImpl(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        // Example logic - fetch reading and create a compliance log
        var reading = readingRepository.findById(readingId).orElseThrow(
            () -> new RuntimeException("Reading not found")
        );

        ComplianceLog log = new ComplianceLog();
        log.setReadingId(readingId);
        log.setStatus(reading.getValue() > 50 ? "FAIL" : "PASS"); // Example evaluation
        return log;
    }
}
