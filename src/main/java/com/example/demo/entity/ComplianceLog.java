package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SensorReading sensorReading;

    @ManyToOne
    private ComplianceThreshold thresholdUsed;

    private String statusAssigned;

    public Long getId() {
        return id;
    }

    public SensorReading getSensorReading() {
        return sensorReading;
    }

    public void setSensorReading(SensorReading sensorReading) {
        this.sensorReading = sensorReading;
    }

    public ComplianceThreshold getThresholdUsed() {
        return thresholdUsed;
    }

    public void setThresholdUsed(ComplianceThreshold thresholdUsed) {
        this.thresholdUsed = thresholdUsed;
    }

    public String getStatusAssigned() {
        return statusAssigned;
    }

    public void setStatusAssigned(String statusAssigned) {
        this.statusAssigned = statusAssigned;
    }
}
