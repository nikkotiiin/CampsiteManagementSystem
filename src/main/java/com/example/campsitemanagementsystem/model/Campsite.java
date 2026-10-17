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

    public NodeList<Booking> getGuestBookings(String guestId) {
        NodeList<Booking> guestBookings = new NodeList<Booking>();
        for(int i=0; i < getCampingAreaCount(); i++){
            CampingArea currentArea = getCampingArea(i);
            NodeList<Booking> areaBookings = currentArea.getGuestBookings(guestId);
            for (int j = 0; j < areaBookings.size(); j++) {
                Booking currentBooking = areaBookings.getElement(j);
                guestBookings.add(currentBooking);
            }

        }
        return guestBookings;
    }
    public NodeList<Booking> getPendingBookings() {
        NodeList<Booking> pendingBookings = new NodeList<Booking>();
        for(int i=0; i<getCampingAreaCount(); i++){
            CampingArea currentArea = getCampingArea(i);
            for(int j=0; j<currentArea.getPendingBookingCount(); j++){
                Booking currentBooking = currentArea.getPendingBooking(j);
                pendingBookings.add(currentBooking);
            }
        }
        return pendingBookings;

    }
}
