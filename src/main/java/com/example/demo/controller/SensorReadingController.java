package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.SensorReadingService;
import com.example.demo.service.SensorService;
import com.example.demo.service.ComplianceThresholdService;
import com.example.demo.service.ComplianceEvaluationService;
import com.example.demo.service.ComplianceLogService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readings")
public class SensorReadingController {

    private final SensorReadingService readingService;
    private final ComplianceThresholdService thresholdService;
    private final ComplianceEvaluationService evaluationService;
    private final ComplianceLogService logService;

    public SensorReadingController(
            SensorReadingService readingService,
            ComplianceThresholdService thresholdService,
            ComplianceEvaluationService evaluationService,
            ComplianceLogService logService) {

        this.readingService = readingService;
        this.thresholdService = thresholdService;
        this.evaluationService = evaluationService;
        this.logService = logService;
    }

    @PostMapping("/sensor/{sensorId}")
    public SensorReading submitReading(@PathVariable Long sensorId,
                                       @RequestBody SensorReading reading) {

        SensorReading savedReading = readingService.submitReading(sensorId, reading);

        ComplianceThreshold threshold =
                thresholdService.getBySensorType(savedReading.getSensor().getSensorType());

        String status = evaluationService.evaluateReading(savedReading, threshold);

        logService.createLog(savedReading.getSensor(), savedReading.getReadingValue(), status);

        return savedReading;
    }

    @GetMapping("/{id}")
    public SensorReading getReading(@PathVariable Long id) {
        return readingService.getReading(id);
    }

    @GetMapping("/sensor/{sensorId}")
    public List<SensorReading> getBySensor(@PathVariable Long sensorId) {
        return readingService.getReadingsBySensor(sensorId);
    }

    @GetMapping
    public List<SensorReading> listAllReadings() {
        return readingService.listSensorReadings();
    }
}
