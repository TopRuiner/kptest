package com.example.kptest.model;





import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Анализ
 */

public class Analysis {
    private long id;
    //private int patientId;
    private Patient patient;
    private String type;
    private String report;
    private Assistant assistant;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;
    private AnalysisReferral analysisReferral;

    public Analysis(Patient patient, String type, String report, Assistant assistant, LocalDateTime date) {
        this.patient = patient;
        this.type = type;
        this.report = report;
        this.assistant = assistant;
        this.date = date;
    }

    public Analysis() {
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", patient=" + patient +
                ", type='" + type + '\'' +
                ", report='" + report + '\'' +
                ", assistant=" + assistant +
                ", date=" + date +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AnalysisReferral getAnalysisReferral() {
        return analysisReferral;
    }

    public void setAnalysisReferral(AnalysisReferral analysisReferral) {
        this.analysisReferral = analysisReferral;
    }
}
