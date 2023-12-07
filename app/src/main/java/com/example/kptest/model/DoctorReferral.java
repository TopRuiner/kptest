package com.example.kptest.model;



import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DoctorReferral {

    private long id;

    private Diagnosis diagnosis;

    private Doctor doctorInitial;

    private Patient patient;

    private Doctor doctorTarget;
    private String cabinetNum;
    private String status;

    private LocalDateTime dateOfTaking;

    private String whatToResearch;
    private String schedule;

    public DoctorReferral(Diagnosis diagnosis, Doctor doctorInitial, Patient patient, Doctor doctorTarget, String cabinetNum, String status, LocalDateTime dateOfTaking, String whatToResearch, String schedule) {
        this.diagnosis = diagnosis;
        this.doctorInitial = doctorInitial;
        this.patient = patient;
        this.doctorTarget = doctorTarget;
        this.cabinetNum = cabinetNum;
        this.status = status;
        this.dateOfTaking = dateOfTaking;
        this.whatToResearch = whatToResearch;
        this.schedule = schedule;
    }

    public DoctorReferral() {
    }

    @Override
    public String toString() {
        return "DoctorReferral{" +
                "id=" + id +
                ", diagnosis=" + diagnosis +
                ", doctorInitial=" + doctorInitial +
                ", patient=" + patient +
                ", doctorTarget=" + doctorTarget +
                ", cabinetNum='" + cabinetNum + '\'' +
                ", status='" + status + '\'' +
                ", dateOfTaking=" + dateOfTaking +
                ", whatToResearch='" + whatToResearch + '\'' +
                ", schedule='" + schedule + '\'' +
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

    public Doctor getDoctorInitial() {
        return doctorInitial;
    }

    public void setDoctorInitial(Doctor doctorInitial) {
        this.doctorInitial = doctorInitial;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctorTarget() {
        return doctorTarget;
    }

    public void setDoctorTarget(Doctor doctorTarget) {
        this.doctorTarget = doctorTarget;
    }

    public String getCabinetNum() {
        return cabinetNum;
    }

    public void setCabinetNum(String cabinetNum) {
        this.cabinetNum = cabinetNum;
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

    public String getWhatToResearch() {
        return whatToResearch;
    }

    public void setWhatToResearch(String whatToResearch) {
        this.whatToResearch = whatToResearch;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
