package com.example.campsitemanagementsystem.model;

import com.example.campsitemanagementsystem.structures.NodeList;

public class Campsite {
    private String name;
    private String address;
    private int stars;
    private String contactInfo;
    private NodeList<CampingArea> campingAreas;

    public Campsite(String name, String address, int stars, String contactInfo) {
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.contactInfo = contactInfo;
        this.campingAreas = new NodeList<CampingArea>();
    }
    public void addCampingArea(CampingArea area) {
        for (int i=0; i<getCampingAreaCount(); i++) {
            CampingArea currentArea = getCampingArea(i);
            if (currentArea.getId().equals(area.getId())) {
                return;
            }
        }
        campingAreas.add(area);
    }
    public int getCampingAreaCount() {
        return campingAreas.size();
    }
    public CampingArea getCampingArea(int index) {
        return campingAreas.getElement(index);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getStars() {
        return stars;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}
