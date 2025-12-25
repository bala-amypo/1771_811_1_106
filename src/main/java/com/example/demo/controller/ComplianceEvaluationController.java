package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluate")
public class ComplianceEvaluationController {

    private final ComplianceEvaluationService evaluationService;

    public ComplianceEvaluationController(ComplianceEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ComplianceLog evaluate(@RequestBody SensorReading reading) {
        return evaluationService.evaluateReading(reading);
    }
}
