package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thresholds")
public class ComplianceThresholdController {

    private final ComplianceThresholdService thresholdService;

    public ComplianceThresholdController(ComplianceThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    @PostMapping
    public ComplianceThreshold create(@RequestBody ComplianceThreshold threshold) {
        return thresholdService.createThreshold(threshold);
    }

    @GetMapping("/{sensorType}")
    public ComplianceThreshold getBySensorType(@PathVariable String sensorType) {
        return thresholdService.getThresholdBySensorType(sensorType);
    }
}
