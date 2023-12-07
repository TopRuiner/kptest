package com.example.kptest.model.auth;

import com.example.kptest.model.Patient;

public class PatientRequest {
    Patient patient;
    //todo Email по токену можно
    String email;

    public PatientRequest() {
    }

    public PatientRequest(Patient patient, String email) {
        this.patient = patient;
        this.email = email;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
