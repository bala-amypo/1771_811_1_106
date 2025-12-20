package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
public class ComplianceThresholdController {

    private final ComplianceThresholdService thresholdService;

    public ComplianceThresholdController(ComplianceThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    @PostMapping
    public ComplianceThreshold createThreshold(@RequestBody ComplianceThreshold threshold) {
        return thresholdService.createThreshold(threshold);
    }

    @GetMapping("/{id}")
    public ComplianceThreshold getThreshold(@PathVariable Long id) {
        return thresholdService.getThreshold(id);
    }

    @GetMapping
    public List<ComplianceThreshold> getAllThresholds() {
        return thresholdService.getAllThresholds();
    }
}
