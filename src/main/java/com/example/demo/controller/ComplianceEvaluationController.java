package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class ComplianceLogController {

    private final ComplianceLogService logService;

    public ComplianceLogController(ComplianceLogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<ComplianceLog> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ComplianceLog getLogById(@PathVariable Long id) {
        return logService.getLogById(id);
    }

    @GetMapping("/sensor/{sensorId}")
    public List<ComplianceLog> getLogsBySensor(@PathVariable Long sensorId) {
        return logService.getLogsBySensor(sensorId);
    }
}
