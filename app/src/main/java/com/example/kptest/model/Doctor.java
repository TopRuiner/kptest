package com.example.kptest.model;




import java.sql.Date;

public class Doctor {

    private long id;

    private String firstName;

    private String lastName;
    private String middleName;

    private Date birthDate;
    //public PolyclinicUser PolyclinicUser;

    private String speciality;
    private String category;
    private String degree;

    private PolyclinicUser user;

    private DoctorCabinet cabinet;

    public Doctor(String firstName, String lastName, String middleName, Date birthDate, String speciality, String category, String degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.speciality = speciality;
        this.category = category;
        this.degree = degree;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", speciality='" + speciality + '\'' +
                ", category='" + category + '\'' +
                ", degree='" + degree + '\'' +
                ", user=" + user +
                '}';
    }

    public String ReturnDateForDisplay()
    {
            return this.birthDate.toString();
    }
    public String ReturnFIO()
    {
            return lastName + " " + firstName + " " + middleName;
    }
    public String ReturnFIOAndSpeciality()
    {
            return lastName + " " + firstName + " " + middleName + " (" + speciality + ")";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public PolyclinicUser getUser() {
        return user;
    }

    public void setUser(PolyclinicUser user) {
        this.user = user;
    }

    public DoctorCabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(DoctorCabinet cabinet) {
        this.cabinet = cabinet;
    }
}
