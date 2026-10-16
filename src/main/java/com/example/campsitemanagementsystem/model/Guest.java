package com.example.campsitemanagementsystem.model;
import java.time.LocalDate;

public class Guest {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private String contactInfo;
    private String additionalInfo;

    public Guest(String id, String name, LocalDate dateOfBirth, String contactInfo, String additionalInfo){
        this.id=id;
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.contactInfo=contactInfo;
        this.additionalInfo=additionalInfo;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public String getContactInfo(){
        return contactInfo;
    }
    public String getAdditionalInfo(){
        return additionalInfo;
    }


}
