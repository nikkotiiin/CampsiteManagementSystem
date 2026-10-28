package com.example.campsitemanagementsystem.model;

import com.example.campsitemanagementsystem.structures.NodeList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AllCampsitesTest {

    @Test
    public void testSaveAndLoad() throws Exception {
        AllCampsites allCampsites = new AllCampsites();

        Campsite campsite = new Campsite(
                "Sunny Camp",
                "Dublin",
                4,
                "123456789"
        );

        allCampsites.addCampsite(campsite);

        // Save
        allCampsites.save();

        // Reset
        allCampsites.reset();
        assertEquals(0, allCampsites.getAllCampsiteSize());

        // Load
        allCampsites.load();

        assertEquals(1, allCampsites.getAllCampsiteSize());
        assertEquals("Sunny Camp", allCampsites.getCampsite(0).getName());
    }
    @Test
    public void testSaveAndLoadGuests() throws Exception {
        AllCampsites allCampsites = new AllCampsites();

        Guest guest = new Guest(
                "G001",
                "Anna Smith",
                LocalDate.of(2000, 5, 15),
                "anna@email.com",
                "No additional information"
        );

        allCampsites.addGuest(guest);

        // Save
        allCampsites.save();

        // Reset
        allCampsites.reset();
        assertEquals(0, allCampsites.getGuestCount());

        // Load
        allCampsites.load();

        assertEquals(1, allCampsites.getGuestCount());
        assertEquals("G001", allCampsites.getGuest(0).getId());
        assertEquals("Anna Smith", allCampsites.getGuest(0).getName());
    }
    @Test
    public void testSaveAndLoadNestedObjects() throws Exception {
        AllCampsites allCampsites = new AllCampsites();

        Campsite campsite = new Campsite(
                "Sunny Camp",
                "Dublin",
                4,
                "123456789"
        );

        CampingArea area = new CampingArea("A001", "Lake Area");

        Plot plot = new Plot(
                "P001",
                "Tent",
                true,
                0,
                2,
                30.0,
                25.0,
                "photo.jpg",
                "Electricity"
        );

        area.addPlot(plot);
        campsite.addCampingArea(area);
        allCampsites.addCampsite(campsite);

        // Save
        allCampsites.save();

        // Reset
        allCampsites.reset();
        assertEquals(0, allCampsites.getAllCampsiteSize());

        // Load
        allCampsites.load();

        // Check campsite
        assertEquals(1, allCampsites.getAllCampsiteSize());

        Campsite loadedCampsite = allCampsites.getCampsite(0);
        assertEquals("Sunny Camp", loadedCampsite.getName());

        // Check camping area
        assertEquals(1, loadedCampsite.getCampingAreaCount());

        CampingArea loadedArea = loadedCampsite.getCampingArea(0);
        assertEquals("A001", loadedArea.getId());

        // Check plot
        assertEquals(1, loadedArea.getPlotCount());

        Plot loadedPlot = loadedArea.getPlot(0);
        assertEquals("P001", loadedPlot.getId());
    }
    @Test
    public void testSaveAndLoadBooking() throws Exception {
        AllCampsites allCampsites = new AllCampsites();

        Campsite campsite = new Campsite(
                "Sunny Camp", "Dublin", 4, "123456789"
        );

        CampingArea area = new CampingArea("A001", "Lake Area");

        Plot plot = new Plot(
                "P001", "Tent", true,
                0, 2, 30.0, 25.0,
                "photo.jpg", "Electricity"
        );

        area.addPlot(plot);

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

        booking.assignPlot("P001");
        area.addBooking(booking);

        campsite.addCampingArea(area);
        allCampsites.addCampsite(campsite);

        allCampsites.save();
        allCampsites.reset();
        allCampsites.load();

        CampingArea loadedArea = allCampsites
                .getCampsite(0)
                .getCampingArea(0);

        NodeList<Booking> loadedBookings =
                loadedArea.getGuestBookings("G001");

        assertEquals(1, loadedBookings.size());

        Booking loadedBooking = loadedBookings.getElement(0);

        assertEquals("B001", loadedBooking.getId());
        assertEquals("P001", loadedBooking.getAssignedPlotId());
        assertEquals(LocalDate.of(2026, 7, 10), loadedBooking.getStartDate());
        assertEquals(LocalDate.of(2026, 7, 15), loadedBooking.getEndDate());
    }
    @Test
    public void testSaveAndLoadPendingBooking() throws Exception {
        AllCampsites allCampsites = new AllCampsites();

        Campsite campsite = new Campsite(
                "Sunny Camp", "Dublin", 4, "123456789"
        );

        CampingArea area = new CampingArea("A001", "Lake Area");

        Booking booking = new Booking(
                "B002",
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

        // No plots available, so booking should be pending
        area.bookPlot(booking);

        assertEquals(1, area.getPendingBookingCount());

        campsite.addCampingArea(area);
        allCampsites.addCampsite(campsite);

        // Save
        allCampsites.save();

        // Reset
        allCampsites.reset();

        // Load
        allCampsites.load();

        CampingArea loadedArea = allCampsites
                .getCampsite(0)
                .getCampingArea(0);

        // Check pending booking
        assertEquals(1, loadedArea.getPendingBookingCount());

        Booking loadedBooking = loadedArea.getPendingBooking(0);

        assertEquals("B002", loadedBooking.getId());
        assertEquals("G001", loadedBooking.getGuestId());
        assertNull(loadedBooking.getAssignedPlotId());
        assertEquals(LocalDate.of(2026, 7, 10), loadedBooking.getStartDate());
        assertEquals(LocalDate.of(2026, 7, 15), loadedBooking.getEndDate());
    }
    @Test
    public void testGetAllPendingBookings() {

        AllCampsites allCampsites = new AllCampsites();

        Campsite campsite1 = new Campsite(
                "Sunny Camp", "Dublin", 4, "123456789"
        );

        Campsite campsite2 = new Campsite(
                "Green Camp", "Cork", 3, "987654321"
        );

        CampingArea area1 = new CampingArea("A001", "Tent Area");
        CampingArea area2 = new CampingArea("A002", "Caravan Area");

        Booking booking1 = new Booking(
                "B001", "G001", "A001",
                LocalDate.of(2026, 7, 10),
                LocalDate.of(2026, 7, 15),
                "Tent", 2, true, 0, 20.0, 200.0
        );

        Booking booking2 = new Booking(
                "B002", "G002", "A002",
                LocalDate.of(2026, 8, 10),
                LocalDate.of(2026, 8, 15),
                "Caravan", 2, false, 0, 20.0, 200.0
        );

        // No plots available, so both bookings become pending
        assertFalse(area1.bookPlot(booking1));
        assertFalse(area2.bookPlot(booking2));

        campsite1.addCampingArea(area1);
        campsite2.addCampingArea(area2);

        allCampsites.addCampsite(campsite1);
        allCampsites.addCampsite(campsite2);

        NodeList<Booking> pendingBookings =
                allCampsites.getAllPendingBookings();

        assertEquals(2, pendingBookings.size());

        boolean foundBooking1 = false;
        boolean foundBooking2 = false;

        for (int i = 0; i < pendingBookings.size(); i++) {
            Booking booking = pendingBookings.getElement(i);

            if (booking.getId().equals("B001")) {
                foundBooking1 = true;
            }

            if (booking.getId().equals("B002")) {
                foundBooking2 = true;
            }
        }

        assertTrue(foundBooking1);
        assertTrue(foundBooking2);

        // Original pending bookings must remain unchanged
        assertEquals(1, area1.getPendingBookingCount());
        assertEquals(1, area2.getPendingBookingCount());
    }
}