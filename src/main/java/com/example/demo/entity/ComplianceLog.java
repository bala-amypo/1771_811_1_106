package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_logs")
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SensorReading sensorReading;

    @ManyToOne
    private ComplianceThreshold thresholdUsed;

    private String statusAssigned;
    private String remarks;
    private LocalDateTime loggedAt;

    public ComplianceLog() {
        this.loggedAt = LocalDateTime.now();
    }

    public ComplianceLog(SensorReading sr, ComplianceThreshold ct,
                         String status, String remarks, LocalDateTime time) {
        this.sensorReading = sr;
        this.thresholdUsed = ct;
        this.statusAssigned = status;
        this.remarks = remarks;
        this.loggedAt = time;
    }

    public void setThresholdUsed(ComplianceThreshold t) { this.thresholdUsed = t; }
    public void setStatusAssigned(String s) { this.statusAssigned = s; }
    public void setRemarks(String r) { this.remarks = r; }
}
