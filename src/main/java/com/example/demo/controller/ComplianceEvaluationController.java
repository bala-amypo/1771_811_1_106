package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluate")
public class ComplianceEvaluationController {

    private final ComplianceEvaluationService evaluationService;

    public ComplianceEvaluationController(ComplianceEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ComplianceLog evaluateCompliance(@RequestBody SensorReading reading) {
        return evaluationService.evaluateCompliance(reading);
    }
}
