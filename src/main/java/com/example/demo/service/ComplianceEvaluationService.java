package com.example.demo.service;

import com.example.demo.entity.ComplianceLog;

import java.util.List;

public interface ComplianceEvaluationService {

    ComplianceLog evaluateReading(Long readingId);

    ComplianceLog getComplianceLog(Long id);

    List<ComplianceLog> getLogsByReading(Long readingId);
}
