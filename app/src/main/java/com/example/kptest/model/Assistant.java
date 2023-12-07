package com.example.kptest.model;




import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Лаборант
 */

public class Assistant {

    private long id;
    private String firstName;

    private String lastName;
    private String middleName;
    private Date birthDate;

    private PolyclinicUser user;


    private AnalysisCabinet cabinet;

    //@OneToMany
    //private List<Analysis> analyses;
    public Assistant(String firstName, String lastName, String middleName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
    }

    public Assistant() {
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", user=" + user +
                '}';
    }

    public String ReturnDateForDisplay()
    {
            return this.birthDate.toString();
    }
    public String ReturnFIOAndBirthDate()
    {
//            return lastName + " " + firstName + " " + middleName + " " + this.birthDate.toString();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return lastName + " " + firstName + " " + middleName + " " + simpleDateFormat.format(this.birthDate);
    }
    public String ReturnFIO(){
        return lastName + " " + firstName + " " + middleName;
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

    public PolyclinicUser getUser() {
        return user;
    }

    public void setUser(PolyclinicUser user) {
        this.user = user;
    }

    public AnalysisCabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(AnalysisCabinet cabinet) {
        this.cabinet = cabinet;
    }
}
