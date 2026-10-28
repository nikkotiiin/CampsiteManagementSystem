package com.example.campsitemanagementsystem.model;

import com.example.campsitemanagementsystem.structures.NodeList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;

public class AllCampsites {
    private NodeList<Campsite> campsites;
    private NodeList<Guest> guests;

    public AllCampsites() {
        campsites = new NodeList<Campsite>();
        guests = new NodeList<Guest>();
    }

    public boolean addCampsite(Campsite campsite) {
        for (int i = 0; i < campsites.size(); i++) {
            Campsite currentCamp = campsites.getElement(i);
            if (currentCamp.equals(campsite)) {
                return false;
            }
        }
        campsites.add(campsite);
        return true;
    }
    public boolean addGuest(Guest guest) {
        for (int i = 0; i < guests.size(); i++) {
            Guest currentGuest = guests.getElement(i);
            if (currentGuest.getId().equals(guest.getId())) {
                return false;
            }
        }
        guests.add(guest);
        return true;
    }

    public int getAllCampsiteSize() {
        return campsites.size();
    }

    public Campsite getCampsite(int index) {
        return campsites.getElement(index);
    }

    public NodeList<Booking> getAllPendingBookings() {
        NodeList<Booking> allPendingBookings = new NodeList<Booking>();

        for (int i = 0; i < campsites.size(); i++) {
            Campsite campsite = campsites.getElement(i);

            NodeList<Booking> pendingBookings = campsite.getPendingBookings();

            for (int j = 0; j < pendingBookings.size(); j++) {
                Booking booking = pendingBookings.getElement(j);
                allPendingBookings.add(booking);
            }
        }

        return allPendingBookings;
    }
    public NodeList<Booking> getAllGuestBookings(String guestId) {
        NodeList<Booking> allGuestBookings = new NodeList<Booking>();
        for (int i=0; i<campsites.size(); i++) {
            Campsite currentCampsite = campsites.getElement(i);
            NodeList<Booking> campsiteBookings = currentCampsite.getGuestBookings(guestId);
            for (int j=0; j<campsiteBookings.size(); j++) {
                Booking currentBooking = campsiteBookings.getElement(j);
                allGuestBookings.add(currentBooking);
            }
        }
        return allGuestBookings;
    }

    public int getGuestCount() {
        return guests.size();
    }

    public Guest getGuest(int index) {
        return guests.getElement(index);
    }

    public void reset() {
        campsites.clear();
        guests.clear();
    }
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out =
                xstream.createObjectOutputStream(new FileWriter("campsites.xml"));

        out.writeObject(campsites);
        out.writeObject(guests);

        out.close();
    }
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());

        XStream.setupDefaultSecurity(xstream);

        xstream.allowTypesByWildcard(new String[] {
                "com.example.campsitemanagementsystem.model.**",
                "com.example.campsitemanagementsystem.structures.**"
        });

        ObjectInputStream in =
                xstream.createObjectInputStream(new FileReader("campsites.xml"));

        campsites = (NodeList<Campsite>) in.readObject();
        guests = (NodeList<Guest>) in.readObject();

        in.close();
    }
}
