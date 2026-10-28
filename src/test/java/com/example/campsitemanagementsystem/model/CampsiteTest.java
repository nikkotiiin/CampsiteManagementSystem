package com.example.campsitemanagementsystem.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CampsiteTest {

    @Test
    public void testEmptyCampingAreas() {
        Campsite campsite = new Campsite("Green Park", "Dublin", 4, "123456");

        assertEquals(0, campsite.getCampingAreaCount());
    }
    @Test
    public void testAddCampingArea() {
        Campsite campsite = new Campsite("Green Park", "Dublin", 4, "123456");
        CampingArea area = new CampingArea("A1","Near lake");

        campsite.addCampingArea(area);

        assertEquals(1, campsite.getCampingAreaCount());
        campsite.addCampingArea(new CampingArea("A2","Near lake"));
        assertEquals(2, campsite.getCampingAreaCount());
    }
    @Test
    public void testGetCampingArea() {
        Campsite campsite = new Campsite("Green Park", "Dublin", 4, "123456");

        campsite.addCampingArea(new CampingArea("A1","Near lake"));
        campsite.addCampingArea(new CampingArea("A2","Near lake"));

        assertEquals("A2", campsite.getCampingArea(0).getId());
        assertEquals("A1", campsite.getCampingArea(1).getId());
        assertEquals(null, campsite.getCampingArea(2));
    }
    @Test
    public void testDuplicateCampingAreaId() {
        Campsite campsite = new Campsite("Green Park", "Dublin", 4, "123456");

        campsite.addCampingArea(new CampingArea("A1","Near lake"));
        campsite.addCampingArea(new CampingArea("A1","Near lake"));

        assertEquals(1, campsite.getCampingAreaCount());
        campsite.addCampingArea(new CampingArea("A2","Near lake"));
        assertEquals(2, campsite.getCampingAreaCount());
    }
    @Test
    public void testSameIdInDifferentCampsites() {
        Campsite first = new Campsite("Green Park", "Dublin", 4, "123456");
        Campsite second = new Campsite("Blue Lake", "Galway", 3, "654321");

        first.addCampingArea(new CampingArea("A1", "Near lake"));
        second.addCampingArea(new CampingArea("A1","Near lake"));

        assertEquals(1, first.getCampingAreaCount());
        assertEquals(1, second.getCampingAreaCount());
    }
    @Test
    public void testRemovePlotMovesBookingToAnotherArea() {

        Campsite campsite = new Campsite(
                "Sunny Camp", "Dublin", 4, "123456789"
        );

        CampingArea area1 = new CampingArea("A001", "First Area");
        CampingArea area2 = new CampingArea("A002", "Second Area");

        Plot plot1 = new Plot(
                "P001", "Tent", true,
                0, 2, 30.0, 25.0,
                "photo1.jpg", "Electricity"
        );

        Plot plot2 = new Plot(
                "P002", "Tent", true,
                0, 2, 30.0, 25.0,
                "photo2.jpg", "Electricity"
        );

        area1.addPlot(plot1);
        area2.addPlot(plot2);

        Booking booking = new Booking(
                "B001",
                "G001",
                "A001",
                LocalDate.of(2026, 7, 10),
                LocalDate.of(2026, 7, 15),
                "Tent",
                2,
                true,
                0,
                20.0,
                200.0
        );

        // Book the plot in the first area
        assertTrue(area1.bookPlot(booking));
        assertEquals("P001", booking.getAssignedPlotId());

        campsite.addCampingArea(area1);
        campsite.addCampingArea(area2);

        // Remove the booked plot
        boolean removed = campsite.removePlot("A001", "P001");

        assertTrue(removed);

        // Booking should move to the second area
        assertEquals("A002", booking.getCampingAreaId());
        assertEquals("P002", booking.getAssignedPlotId());

        // Check that booking is no longer pending in the first area
        assertEquals(0, area1.getPendingBookingCount());

        // Check that booking is stored in the second area
        assertEquals(1, area2.getGuestBookings("G001").size());
    }
    @Test
    public void testRemovePlotKeepsBookingPending() {

        Campsite campsite = new Campsite(
                "Sunny Camp", "Dublin", 4, "123456789"
        );

        CampingArea area1 = new CampingArea("A001", "First Area");
        CampingArea area2 = new CampingArea("A002", "Second Area");

        Plot plot1 = new Plot(
                "P001", "Tent", true,
                0, 2, 30.0, 25.0,
                "photo1.jpg", "Electricity"
        );

        Plot plot2 = new Plot(
                "P002", "Caravan", false,
                0, 2, 30.0, 25.0,
                "photo2.jpg", "Water"
        );

        area1.addPlot(plot1);
        area2.addPlot(plot2);

        Booking booking = new Booking(
                "B001",
                "G001",
                "A001",
                LocalDate.of(2026, 7, 10),
                LocalDate.of(2026, 7, 15),
                "Tent",
                2,
                true,
                0,
                20.0,
                200.0
        );

        assertTrue(area1.bookPlot(booking));

        campsite.addCampingArea(area1);
        campsite.addCampingArea(area2);

        boolean removed = campsite.removePlot("A001", "P001");

        assertTrue(removed);

        // Booking stays in the original area
        assertEquals("A001", booking.getCampingAreaId());

        // No plot assigned
        assertNull(booking.getAssignedPlotId());

        // Booking is waiting
        assertEquals(1, area1.getPendingBookingCount());

        // No booking moved to the second area
        assertEquals(0, area2.getGuestBookings("G001").size());

        // The original plot was removed
        assertEquals(0, area1.getPlotCount());
    }
}