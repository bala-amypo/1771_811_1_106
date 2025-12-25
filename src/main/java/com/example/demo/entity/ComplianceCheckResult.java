package com.example.demo.entity;

public class ComplianceCheckResult {

    private boolean compliant;
    private String message;

    private double readingValue;
    private double minThreshold;
    private double maxThreshold;

    private String sensorType;

    public ComplianceCheckResult() {}

    public ComplianceCheckResult(boolean compliant, String message,
                                 double readingValue, double minThreshold,
                                 double maxThreshold, String sensorType) {
        this.compliant = compliant;
        this.message = message;
        this.readingValue = readingValue;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.sensorType = sensorType;
    }

    public boolean isCompliant() {
        return compliant;
    }

    public void setCompliant(boolean compliant) {
        this.compliant = compliant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(double readingValue) {
        this.readingValue = readingValue;
    }

    public double getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(double minThreshold) {
        this.minThreshold = minThreshold;
    }

    public double getMaxThreshold() {
        return maxThreshold;
    }

    public void setMaxThreshold(double maxThreshold) {
        this.maxThreshold = maxThreshold;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }
}
