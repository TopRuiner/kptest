package com.example.kptest.model;



public class Diagnosis {
    private String id;
    private String description;

    public Diagnosis(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Diagnosis() {
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String ReturnIdAndDescription()
    {
            return id + " " + description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
