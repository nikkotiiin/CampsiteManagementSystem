package com.example.campsitemanagementsystem.model;

public class Plot {
    private String id;
    private String type;
    private boolean powered;
    private int bedrooms;
    private int beds;
    private double size;
    private double costPerNight;
    private String photoUrl;
    private String facilities;

    public Plot(String id, String type, boolean powered, int bedrooms, int beds, double size, double costPerNight, String facilities, String photoUrl) {
        this.id = id;
        this.type= type;
        this.powered = powered;
        this.bedrooms = bedrooms;
        this.beds = beds;
        this.size = size;
        this.costPerNight = costPerNight;
        this.photoUrl = photoUrl;
        this.facilities=facilities;
    }
    public String getId() {
        return id;
    }
    public String getType(){
        return type;
    }
    public boolean isPowered() {
        return powered;
    }
    public int getBedrooms(){
        return bedrooms;
    }
    public int getBeds(){
        return beds;
    }
    public double getSize(){
        return size;
    }
    public double getCostPerNight(){
        return costPerNight;
    }
    public String getPhotoUrl(){
        return photoUrl;
    }
    public String getFacilities(){
        return facilities;
    }
}
