package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_thresholds")
public class ComplianceThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String sensorType;

    private Double minValue;
    private Double maxValue;
    private String severityLevel;
    private LocalDateTime createdAt;

    public ComplianceThreshold() {
        this.createdAt = LocalDateTime.now();
    }

    public String getSensorType() { return sensorType; }
    public Double getMinValue() { return minValue; }
    public Double getMaxValue() { return maxValue; }

    public Object getSeverityLevel() {
        throw new UnsupportedOperationException("Unimplemented method 'getSeverityLevel'");
    }
}
