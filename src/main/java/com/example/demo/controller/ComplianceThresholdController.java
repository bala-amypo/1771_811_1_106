package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thresholds")
public class ComplianceThresholdController {

    private final ComplianceThresholdService thresholdService;

    public ComplianceThresholdController(ComplianceThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    @PostMapping
    public ResponseEntity<ComplianceThreshold> saveThreshold(@RequestBody ComplianceThreshold threshold) {
        return ResponseEntity.ok(thresholdService.saveThreshold(threshold));
    }

    @GetMapping("/type/{sensorType}")
    public ResponseEntity<ComplianceThreshold> getThresholdByType(@PathVariable String sensorType) {
        return ResponseEntity.ok(thresholdService.getBySensorType(sensorType));
    }
}
