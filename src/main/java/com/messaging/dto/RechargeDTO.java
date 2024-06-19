package com.messaging.dto;

public class RechargeDTO {
    private String phoneNumber;
    private double amount;


    public RechargeDTO(String phoneNumber, double amount) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public double getAmount() {

        return amount;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}


