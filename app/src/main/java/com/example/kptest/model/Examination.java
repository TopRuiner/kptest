package com.example.kptest.model;



import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;



public class Examination {

    private long id;

    private String type;

    private String report;


    private LocalDateTime date;

    private FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor;

    private Patient patient;


    private ExaminationReferral examinationReferral;

    public Examination(String type, String report, LocalDateTime date, FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor, Patient patient) {
        this.type = type;
        this.report = report;
        this.date = date;
        this.functionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
        this.patient = patient;
    }

    public Examination() {
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", report='" + report + '\'' +
                ", date=" + date +
                ", functionalDiagnosticsDoctor=" + functionalDiagnosticsDoctor +
                ", patient=" + patient +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public FunctionalDiagnosticsDoctor getFunctionalDiagnosticsDoctor() {
        return functionalDiagnosticsDoctor;
    }

    public void setFunctionalDiagnosticsDoctor(FunctionalDiagnosticsDoctor functionalDiagnosticsDoctor) {
        this.functionalDiagnosticsDoctor = functionalDiagnosticsDoctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ExaminationReferral getExaminationReferral() {
        return examinationReferral;
    }

    public void setExaminationReferral(ExaminationReferral examinationReferral) {
        this.examinationReferral = examinationReferral;
    }
}
