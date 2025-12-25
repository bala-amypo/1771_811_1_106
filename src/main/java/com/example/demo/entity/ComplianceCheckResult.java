package com.example.demo.entity;

public class ComplianceCheckResult {

    private boolean compliant;
    private String message;

    public ComplianceCheckResult() {}

    public ComplianceCheckResult(boolean compliant, String message) {
        this.compliant = compliant;
        this.message = message;
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
}
