package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
public class ComplianceThresholdController {

    @Autowired
    private ComplianceThresholdService service;

    @GetMapping
    public List<ComplianceThreshold> getAllThresholds() {
        return service.getAllThresholds();
    }

    @PostMapping
    public ComplianceThreshold createThreshold(@RequestBody ComplianceThreshold threshold) {
        return service.createThreshold(threshold);
    }

    @GetMapping("/{sensorType}")
    public ComplianceThreshold getBySensorType(@PathVariable String sensorType) {
        return service.getThresholdBySensorType(sensorType);
    }
}
