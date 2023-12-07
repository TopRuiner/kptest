package com.example.kptest.model;




import java.time.LocalDateTime;


//todo Удалить


public class DoctorReferralAppointment {

    private long id;

    private LocalDateTime dateTime;
    private String status;

    private DoctorReferral doctorReferral;

    public DoctorReferralAppointment(LocalDateTime dateTime, String status, DoctorReferral doctorReferral) {
        //this.patient = patient;
        //this.cabinetId = cabinetId;
        this.dateTime = dateTime;
        this.status = status;
        // this.doctor = doctor;
        this.doctorReferral = doctorReferral;
    }

    public DoctorReferralAppointment() {
    }

    @Override
    public String toString() {
        return "DoctorReferralAppointment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                ", doctorReferral=" + doctorReferral +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DoctorReferral getDoctorReferral() {
        return doctorReferral;
    }

    public void setDoctorReferral(DoctorReferral doctorReferral) {
        this.doctorReferral = doctorReferral;
    }
}

