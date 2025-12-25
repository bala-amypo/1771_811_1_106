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

    public ComplianceLog() {}

    public ComplianceLog(SensorReading sensorReading,
                         ComplianceThreshold thresholdUsed,
                         String statusAssigned,
                         String remarks,
                         LocalDateTime loggedAt) {
        this.sensorReading = sensorReading;
        this.thresholdUsed = thresholdUsed;
        this.statusAssigned = statusAssigned;
        this.remarks = remarks;
        this.loggedAt = loggedAt;
    }

    public Long getId() { return id; }
    public SensorReading getSensorReading() { return sensorReading; }
    public ComplianceThreshold getThresholdUsed() { return thresholdUsed; }
    public String getStatusAssigned() { return statusAssigned; }
    public String getRemarks() { return remarks; }
    public LocalDateTime getLoggedAt() { return loggedAt; }

    public void setId(Long id) { this.id = id; }
    public void setSensorReading(SensorReading sensorReading) { this.sensorReading = sensorReading; }
    public void setThresholdUsed(ComplianceThreshold thresholdUsed) { this.thresholdUsed = thresholdUsed; }
    public void setStatusAssigned(String statusAssigned) { this.statusAssigned = statusAssigned; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
