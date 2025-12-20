package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceEvaluationController {

    private final ComplianceEvaluationService service;

    public ComplianceEvaluationController(ComplianceEvaluationService service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{readingId}")
    public ComplianceLog evaluate(@PathVariable Long readingId) {
        return service.evaluateReading(readingId);
    }

    @GetMapping("/{id}")
    public ComplianceLog get(@PathVariable Long id) {
        return service.getComplianceLog(id);
    }

    @GetMapping("/reading/{readingId}")
    public List<ComplianceLog> getLogs(@PathVariable Long readingId) {
        return service.getLogsByReading(readingId);
    }
}
