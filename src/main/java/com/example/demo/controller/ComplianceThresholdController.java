package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complianceThresholds")
public class ComplianceThresholdController {

    private final ComplianceThresholdService service;

    public ComplianceThresholdController(ComplianceThresholdService service) {
        this.service = service;
    }

    @PostMapping
    public ComplianceThreshold createThreshold(@RequestBody ComplianceThreshold threshold) {
        return service.createThreshold(threshold);
    }

    @GetMapping("/{id}")
    public ComplianceThreshold getThreshold(@PathVariable Long id) {
        return service.getThreshold(id);
    }

    @GetMapping
    public List<ComplianceThreshold> getAllThresholds() {
        return service.getAllThresholds();
    }
}
