package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String message;

    @ManyToOne
    @JoinColumn(name = "sensor_reading_id")
    private SensorReading sensorReading;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SensorReading getSensorReading() {
        return sensorReading;
    }

    public void setSensorReading(SensorReading sensorReading) {
        this.sensorReading = sensorReading;
    }
}
