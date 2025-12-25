package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.entity.ComplianceCheckResult;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compliance")
public class ComplianceEvaluationController {

    private final ComplianceEvaluationService evaluationService;

    public ComplianceEvaluationController(ComplianceEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<ComplianceCheckResult> evaluate(@RequestBody SensorReading reading) {
        return ResponseEntity.ok(evaluationService.evaluateReading(reading));
    }
}
