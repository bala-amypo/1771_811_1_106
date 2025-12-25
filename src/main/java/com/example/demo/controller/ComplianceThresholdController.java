package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
public class ComplianceThresholdController {

    private final ComplianceThresholdService service;

    public ComplianceThresholdController(ComplianceThresholdService service) {
        this.service = service;
    }

    @PostMapping
    public ComplianceThreshold create(@RequestBody ComplianceThreshold t) {
        return service.createThreshold(t);
    }

    @GetMapping
    public List<ComplianceThreshold> all() {
        return service.getAllThresholds();
    }

    @GetMapping("/{id}")
    public ComplianceThreshold get(@PathVariable Long id) {
        return service.getThreshold(id);
    }

    @GetMapping("/type/{sensorType}")
    public ComplianceThreshold byType(@PathVariable String sensorType) {
        return service.getThresholdBySensorType(sensorType);
    }
}
