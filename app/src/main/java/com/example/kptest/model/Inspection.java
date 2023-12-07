package com.example.kptest.model;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Осмотр
 */
public class Inspection {
    private long id;
    private Patient patient;

    private String complaint;
    private String recipe;

    private Diagnosis diagnosis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    private String type;

    private Doctor doctor;

    public Inspection(Patient patient, String complaint, String recipe, Diagnosis diagnosis, LocalDateTime date, String type, Doctor doctor) {
        this.patient = patient;
        this.complaint = complaint;
        this.recipe = recipe;
        this.diagnosis = diagnosis;
        this.date = date;
        this.type = type;
        this.doctor = doctor;
    }

    public Inspection() {
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", patient=" + patient +
                ", complaint='" + complaint + '\'' +
                ", recipe='" + recipe + '\'' +
                ", diagnosis=" + diagnosis +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", doctor=" + doctor +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
