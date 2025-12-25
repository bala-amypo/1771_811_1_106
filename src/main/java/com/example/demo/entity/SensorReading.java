package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_readings")
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sensor sensor;

    private Double readingValue;
    private LocalDateTime readingTime;
    private String status;

    public SensorReading() {}

    public Long getId() { return id; }
    public Sensor getSensor() { return sensor; }
    public Double getReadingValue() { return readingValue; }
    public LocalDateTime getReadingTime() { return readingTime; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setSensor(Sensor sensor) { this.sensor = sensor; }
    public void setReadingValue(Double readingValue) { this.readingValue = readingValue; }
    public void setReadingTime(LocalDateTime readingTime) { this.readingTime = readingTime; }
    public void setStatus(String status) { this.status = status; }
}
