package com.example.campsitemanagementsystem.model;

import com.example.campsitemanagementsystem.structures.NodeList;

import java.time.LocalDate;

public class Booking {
    private String id;
    private String guestId;
    private String campingAreaId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String preferredType;
    private int numberOfGuests;
    private String assignedPlotId;

    public double getMinSize() {
        return minSize;
    }

    public int getMinBedrooms() {
        return minBedrooms;
    }

    public double getBudget() {
        return budget;
    }

    private boolean requiresPower;
    private double minSize;
    private int minBedrooms;
    private double budget;

    public Booking(String id, String guestId, String campingAreaId, LocalDate startDate, LocalDate endDate, String preferredType, int numberOfGuests, boolean requiresPower, int minBedrooms, double minSize, double budget){
        this.id=id;
        this.guestId=guestId;
        this.campingAreaId=campingAreaId;
        this.startDate=startDate;
        this.endDate=endDate;
        this.preferredType=preferredType;
        this.numberOfGuests=numberOfGuests;
        this.requiresPower=requiresPower;
        this.minBedrooms=minBedrooms;
        this.minSize=minSize;
        this.budget=budget;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getCampingAreaId() {
        return campingAreaId;
    }
    public void setCampingAreaId(String campingAreaId) {
        this.campingAreaId = campingAreaId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getId() {
        return id;
    }
    public boolean hasValidDates() {
        return startDate.isBefore(endDate);
    }

    public String getPreferredType() {
        return preferredType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isRequiresPower() {
        return requiresPower;
    }
    public boolean isPlotMatching(Plot plot){
        if(!plot.getType().equals(getPreferredType())){
            return false;
        }
        if(plot.getBeds()<(getNumberOfGuests())){
            return false;
        }
        if(!plot.isPowered()&&(isRequiresPower())){
            return false;
        }
        if(plot.getSize()<(getMinSize())){
            return false;
        }
        if(plot.getBedrooms()<(getMinBedrooms())){
            return false;
        }
        if(plot.getCostPerNight()>(getBudget())){
            return false;
        }
        return true;
    }
    public boolean hasDateConflict(Booking otherBooking) {
        if(startDate.isBefore(otherBooking.getEndDate()) && otherBooking.getStartDate().isBefore(getEndDate())){
            return true;
        }
        return false;
    }
    public void assignPlot(String plotId) {
        this.assignedPlotId=plotId;
    }
    public String getAssignedPlotId(){
        return assignedPlotId;
    }
}
