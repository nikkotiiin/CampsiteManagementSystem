package com.example.campsitemanagementsystem.model;
import com.example.campsitemanagementsystem.structures.Node;
import com.example.campsitemanagementsystem.structures.NodeList;
import com.example.campsitemanagementsystem.model.Booking;
import com.example.campsitemanagementsystem.model.Plot;

public class CampingArea {
    private String id;
    private String description;
    private NodeList<Plot> plots;
    private NodeList<Booking> bookings;
    private NodeList<Booking> pendingBookings;


    public CampingArea(String id, String description){
        this.id=id;
        this.description=description;
        this.plots=new NodeList<Plot>();
        this.bookings=new NodeList<Booking>();
        this.pendingBookings= new NodeList<Booking>();
    }
    public String getId() {
        return id;
    }
    public String getDescription(){
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void addPlot(Plot plot){
        for(int i=0; i<getPlotCount(); i++){
            Plot currentPlot = getPlot(i);
            if(currentPlot.getId().equals(plot.getId())){
                return;
            }
        }
        plots.add(plot);
    }
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    public int getPlotCount(){
        return plots.size();
    }
    public Plot getPlot(int index){
        return plots.getElement(index);
    }
    public Plot findMatchingPlot(Booking booking) {
        for (int i = 0; i < getPlotCount(); i++){
            Plot currentPlot = getPlot(i);
            if(booking.isPlotMatching(currentPlot)){
                return currentPlot;
            }
        }
        return null;
    }
    public boolean isPlotBooked(Plot plot, Booking newBooking) {
        for(int i=0; i< bookings.size(); i++){
            Booking currentBooking = bookings.getElement(i);
            if(plot.getId().equals(currentBooking.getAssignedPlotId()) && currentBooking.hasDateConflict(newBooking)){
                return true;
            }
        }
        return false;
    }
    public Plot findAvailablePlot(Booking booking){
        for(int i =0; i<plots.size(); i++){
            Plot currentPlot = plots.getElement(i);
            if(booking.isPlotMatching(currentPlot) && !isPlotBooked(currentPlot, booking)){
                return currentPlot;
            }
        }
        return null;
    }
    public boolean bookPlot(Booking booking){
        if (!booking.hasValidDates()) {
            return false;
        }
        Plot availablePlot = findAvailablePlot(booking);
        if(availablePlot==null){
            pendingBookings.add(booking);
            return false;
        }
        booking.assignPlot(availablePlot.getId());
        addBooking(booking);

        return true;
    }
    public int getPendingBookingCount() {
        return pendingBookings.size();
    }
    public Booking getPendingBooking(int index) {
        return pendingBookings.getElement(index);
    }
    public boolean cancelBooking(Booking booking){
        if(bookings.remove(booking)){
            processPendingBookings();

            return true;
        }
        if(pendingBookings.remove(booking)){
            return true;
        }
        return false;
    }
    public boolean cancelBookingById(String bookingId) {
        for(int i=0; i<bookings.size(); i++){
            Booking currentBooking = bookings.getElement(i);
            if(currentBooking.getId().equals(bookingId) ){
                return cancelBooking(currentBooking);
            }
        }
        for (int i=0; i<pendingBookings.size(); i++){
            Booking currentBooking = pendingBookings.getElement(i);
            if(currentBooking.getId().equals(bookingId)){
               return cancelBooking(currentBooking);
            }
        }
        return false;
    }

    public void processPendingBookings() {
        int i = 0;
        while (i < pendingBookings.size()){
            Booking currentBooking = pendingBookings.getElement(i);
            Plot availablePlot = findAvailablePlot(currentBooking);
            if(availablePlot!=null){
                currentBooking.assignPlot(availablePlot.getId());
                bookings.add(currentBooking);
                pendingBookings.remove(currentBooking);
            } else{
                i++;
            }
        }

    }
    public NodeList<Booking> getGuestBookings(String guestId) {
        NodeList<Booking> guestBookings = new NodeList<Booking>();
        for (int i=0; i< bookings.size(); i++){
            Booking currentBooking = bookings.getElement(i);
            if(currentBooking.getGuestId().equals(guestId)){
                guestBookings.add(currentBooking);
            }
        }
        for (int i=0; i< pendingBookings.size(); i++){
            Booking currentBooking = pendingBookings.getElement(i);
            if(currentBooking.getGuestId().equals(guestId)){
                guestBookings.add(currentBooking);
            }
        }
        return guestBookings;
    }
    public boolean removePlot(String plotId) {
        for(int i=0; i<plots.size(); i++){
            Plot currentPlot = getPlot(i);
            if(currentPlot.getId().equals(plotId)){
                for (int j = 0; j < bookings.size(); j++) {
                    Booking currentBooking = bookings.getElement(j);
                    if(plotId.equals(currentBooking.getAssignedPlotId())){
                        currentBooking.assignPlot(null);
                        bookings.remove(currentBooking);
                        pendingBookings.add(currentBooking);
                        j--;
                    }
                }
                boolean removedPlot = plots.remove(currentPlot);
                processPendingBookings();
                return removedPlot;
            }
        }
        return false;
    }


}
