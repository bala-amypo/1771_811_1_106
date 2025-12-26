package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reading_id", nullable = false)
    private SensorReading reading;

    private String statusAssigned;

    // Constructors
    public ComplianceLog() {}

    public ComplianceLog(SensorReading reading, String statusAssigned) {
        this.reading = reading;
        this.statusAssigned = statusAssigned;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensorReading getReading() {
        return reading;
    }

    public void setReading(SensorReading reading) {
        this.reading = reading;
    }

    public String getStatusAssigned() {
        return statusAssigned;
    }

    public void setStatusAssigned(String statusAssigned) {
        this.statusAssigned = statusAssigned;
    }
}
