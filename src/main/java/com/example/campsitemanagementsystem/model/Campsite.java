package com.example.campsitemanagementsystem.model;

import com.example.campsitemanagementsystem.structures.NodeList;

public class Campsite {
    private String name;
    private String address;
    private int stars;
    private String contactInfo;
    private NodeList<CampingArea> campingAreas;
    private String facilities;

    public Campsite(String name, String address, int stars, String contactInfo) {
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.contactInfo = contactInfo;
        this.campingAreas = new NodeList<CampingArea>();
    }
    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
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
    public CampingArea findAlternativeArea(Booking booking, String oldAreaId) {
        for (int i=0; i<campingAreas.size(); i++) {
            CampingArea area = campingAreas.getElement(i);

            if (!area.getId().equals(oldAreaId)) {
                Plot availablePlot = area.findAvailablePlot(booking);
                if (availablePlot != null) {
                    return area;
                }
            }
        }

        return null;
    }
    public boolean movePendingBooking(CampingArea oldArea, Booking booking) {
        CampingArea newArea = findAlternativeArea(booking, oldArea.getId());

        if (newArea == null) {
            return false;
        }

        Plot newPlot = newArea.findAvailablePlot(booking);

        boolean removed = oldArea.removePendingBooking(booking);

        if (removed == false) {
            return false;
        }

        booking.setCampingAreaId(newArea.getId());
        booking.assignPlot(newPlot.getId());

        newArea.addBooking(booking);

        return true;
    }
    public boolean removePlot(String areaId, String plotId) {
        for (int i = 0; i < campingAreas.size(); i++) {
            CampingArea area = campingAreas.getElement(i);
            if (area.getId().equals(areaId)) {
                boolean removed = area.removePlot(plotId);
                if (removed == false) {
                    return false;
                }
                int j = 0;
                while (j < area.getPendingBookingCount()) {
                    Booking booking = area.getPendingBooking(j);
                    boolean moved = movePendingBooking(area, booking);
                    if (moved == false) {
                        j++;
                    }
                }

                return true;
            }
        }

        return false;
    }
}
