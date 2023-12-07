package com.example.kptest.model;




import java.sql.Time;



public class ExaminationCabinetReferralTime {

    private long id;


    private ExaminationCabinet examinationCabinet;

    private Time time;

    private String timeString;

    public ExaminationCabinetReferralTime() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ExaminationCabinet getExaminationCabinet() {
        return examinationCabinet;
    }

    public void setExaminationCabinet(ExaminationCabinet examinationCabinet) {
        this.examinationCabinet = examinationCabinet;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
