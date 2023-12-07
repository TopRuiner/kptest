package com.example.kptest.model;



public class DoctorCabinet {
    private long id;

    private String name;
    private String number;
    private String schedule;

    public DoctorCabinet() {
    }

    public String ReturnNameAndNumber() {
        return name + " " + number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
