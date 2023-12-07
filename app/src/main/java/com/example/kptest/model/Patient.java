package com.example.kptest.model;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Пациент
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {

    private long id;

    private String firstName;

    private String lastName;
    private String middleName;
    private Date birthDate;
    private String polisId;

    private String poilsCompany;
    private Date polisEndDate;

    private String  snilsNumber;
    private String homeAddress;
    private String workPlace;
    private String workPosition;

    private PolyclinicUser user;
//    @OneToMany
//    private List<Analysis> Analyses;
//    @OneToMany
//    private List<Examination> Examinations;
//    @OneToMany
//    private List<Inspection> Inspections;

    private String polisEndDateString;


    public Patient(String firstName, String lastName, String middleName, Date birthDate, String polisId, String poilsCompany, String snilsNumber, String homeAddress, String workPlace, String workPosition, Date polisEndDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.polisId = polisId;
        this.poilsCompany = poilsCompany;
        this.polisEndDate = polisEndDate;
        this.snilsNumber = snilsNumber;
        this.homeAddress = homeAddress;
        this.workPlace = workPlace;
        this.workPosition = workPosition;
    }
    public Patient(String firstName, String lastName, String middleName, Date birthDate, String polisId, String poilsCompany, String snilsNumber, String homeAddress, String workPlace, String workPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.polisId = polisId;
        this.poilsCompany = poilsCompany;
        this.snilsNumber = snilsNumber;
        this.homeAddress = homeAddress;
        this.workPlace = workPlace;
        this.workPosition = workPosition;
    }

    public Patient() {
    }

    public String ReturnBirthDate()
    {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.birthDate);
    }
    public String getFIOForRequest(){
        return lastName+"+"+firstName+"+"+middleName+"+";
    }
    public String getBirthDateForRequest(){
        String pattern = "yyyy+MM+dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(birthDate);
        return date;
    }
    public String ReturnPolisEndDate()
    {
            return this.polisEndDate.toString();
    }
    public String ReturnFIO()
    {
            return lastName + " " + firstName + " " + middleName;
    }
    public String ReturnFIOAndBirthDate()
    {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return lastName + " " + firstName + " " + middleName + " " + simpleDateFormat.format(this.birthDate);
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

    public String getPolisId() {
        return polisId;
    }

    public void setPolisId(String polisId) {
        this.polisId = polisId;
    }

    public String getPoilsCompany() {
        return poilsCompany;
    }

    public void setPoilsCompany(String poilsCompany) {
        this.poilsCompany = poilsCompany;
    }

    public Date getPolisEndDate() {
        return polisEndDate;
    }

    public String getStringPolisEndDate(){
        if (polisEndDate==null){
            return "";
        }
        else {
            return String.valueOf(polisEndDate);
        }
    }
    public void setPolisEndDate(Date polisEndDate) {
        this.polisEndDate = polisEndDate;
    }

    public String getSnilsNumber() {
        return snilsNumber;
    }

    public void setSnilsNumber(String snilsNumber) {
        this.snilsNumber = snilsNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public PolyclinicUser getUser() {
        return user;
    }

    public void setUser(PolyclinicUser user) {
        this.user = user;
    }

    public String getPolisEndDateString() {
        return polisEndDateString;
    }

    public void setPolisEndDateString(String polisEndDateString) {
        this.polisEndDateString = polisEndDateString;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", polisId='" + polisId + '\'' +
                ", poilsCompany='" + poilsCompany + '\'' +
                ", polisEndDate=" + polisEndDate +
                ", snilsNumber='" + snilsNumber + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", workPosition='" + workPosition + '\'' +
                ", user=" + user +
                ", polisEndDateString='" + polisEndDateString + '\'' +
                '}';
    }
}
