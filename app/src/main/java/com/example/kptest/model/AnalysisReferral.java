package com.example.kptest.model;





import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Направление на анализ
 */

public class AnalysisReferral {

    private long id;

    private Diagnosis diagnosis;

    private Doctor doctor;

    private Patient patient;

    private AnalysisCabinet cabinet;

    private String status;
    private LocalDateTime dateOfTaking;

    private LocalDateTime dateOfIssue;
    private String whatToResearch;


    public AnalysisReferral(Diagnosis diagnosis, Doctor doctor, Patient patient, String status, LocalDateTime dateOfTaking, String whatToResearch) {
        this.diagnosis = diagnosis;
        this.doctor = doctor;
        this.patient = patient;
//        this.laboratory = laboratory;
//        this.assistant = assistant;
//        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
//        this.schedule = schedule;
    }

    public AnalysisReferral() {
    }

    @Override
    public String toString() {
        return "AnalysisReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctor=" + doctor +
                ", patient=" + patient +
//                ", laboratory='" + laboratory + '\'' +
//                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
//                ", schedule='" + schedule + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AnalysisCabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(AnalysisCabinet cabinet) {
        this.cabinet = cabinet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateOfTaking() {
        return dateOfTaking;
    }

    public void setDateOfTaking(LocalDateTime dateOfTaking) {
        this.dateOfTaking = dateOfTaking;
    }

    public LocalDateTime getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDateTime dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getWhatToResearch() {
        return whatToResearch;
    }

    public void setWhatToResearch(String whatToResearch) {
        this.whatToResearch = whatToResearch;
    }
}
