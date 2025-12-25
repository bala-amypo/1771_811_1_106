package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.entity.ComplianceCheckResult;
import com.example.demo.service.SensorReadingService;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readings")
public class SensorReadingController {

    private final SensorReadingService readingService;
    private final ComplianceEvaluationService evaluationService;

    public SensorReadingController(SensorReadingService readingService,
                                   ComplianceEvaluationService evaluationService) {
        this.readingService = readingService;
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{sensorId}")
    public ResponseEntity<SensorReading> addReading(
            @PathVariable Long sensorId,
            @RequestBody SensorReading reading
    ) {
        return ResponseEntity.ok(readingService.addReading(sensorId, reading));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorReading> getReading(@PathVariable Long id) {
        return ResponseEntity.ok(readingService.getReadingById(id));
    }

    @GetMapping
    public ResponseEntity<List<SensorReading>> getAllReadings() {
        return ResponseEntity.ok(readingService.getAllReadings());
    }

    @PostMapping("/evaluate")
    public ResponseEntity<ComplianceCheckResult> evaluateReading(
            @RequestBody SensorReading reading
    ) {
        return ResponseEntity.ok(evaluationService.evaluateReading(reading));
    }
}
