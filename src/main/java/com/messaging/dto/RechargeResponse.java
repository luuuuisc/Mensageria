package com.messaging.dto;

public class RechargeResponse {
    private String phoneNumber;
    private boolean success;
    private String errorMessage;

    // Constructor
    public RechargeResponse(String phoneNumber, boolean success, String errorMessage) {
        this.phoneNumber = phoneNumber;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    // Getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

