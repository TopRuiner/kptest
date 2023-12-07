package com.example.kptest.model.appointment;

import java.sql.Date;
import java.sql.Time;

public class MakeAppointmentRequest {
    private String patientEmail;
    private long doctorId;
    private Date appointmentDate;
    private String appointmentTime;

    public MakeAppointmentRequest() {
    }

    public MakeAppointmentRequest(String patientEmail, long doctorId, Date appointmentDate, String appointmentTime) {
        this.patientEmail = patientEmail;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "MakeAppointmentRequest{" +
                "patientEmail='" + patientEmail + '\'' +
                ", doctorId=" + doctorId +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
