package com.consumer.main.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientData {

    private String id;
    private double temperature;
    private double bloodPressure;
//
//    @JsonCreator
//    public PatientData(@JsonProperty("id") String id,
//                       @JsonProperty("temperature") double temperature,
//                       @JsonProperty("bloodPressure") double bloodPressure) {
//        this.id = id;
//        this.temperature = temperature;
//        this.bloodPressure = bloodPressure;
//    }

    public double getTemperature() {
        return temperature;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public PatientData() {
    }

    public String getId() {
        return id;
    }

    public PatientData(double temperature, double bloodPressure, String id) {
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.id = id;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setId(String id) {
        this.id = id;
    }
}
