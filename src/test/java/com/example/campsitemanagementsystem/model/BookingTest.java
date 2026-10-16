package com.example.campsitemanagementsystem.model;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTest {
    @Test
    public void testValidBookingDates(){
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,16), LocalDate.of(2026,9,20),"Bungalow", 4, true,2, 40.0, 100.0);
        assertTrue(booking.hasValidDates());
    }
    @Test
    public void testInvalidBookingDates() {
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,16),"Bungalow", 4, true,2, 40.0, 100.0);
        assertFalse(booking.hasValidDates());

    }
    @Test
    public void testSameBookingDates(){
            Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,20),"Bungalow", 4, true,2, 40.0, 100.0);
            assertFalse(booking.hasValidDates());
    }
    @Test
    public void testBookingPreferences(){
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 40.0, 100.0);
        assertEquals("Bungalow", booking.getPreferredType());
        assertEquals(4, booking.getNumberOfGuests());
        assertTrue(booking.isRequiresPower());
    }
    @Test
    public void testBookingBudget(){
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 40.0, 100.0);
        assertEquals(100.00, booking.getBudget());

    }
    @Test
    void testIsPlotMatching() {
        Plot plot = new Plot("P1", "Bungalow", true, 2, 4, 50.0, 80.0, "photo.jpg", "WiFi");
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 50.0, 80.0);
        assertTrue(booking.isPlotMatching(plot));
    }
    @Test
    void testPlotNotMatching() {
        Plot plot = new Plot("P1", "Bungalow", true, 2, 2, 50.0, 80.0, "photo.jpg", "WiFi");
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 50.0, 80.0);
        assertFalse(booking.isPlotMatching(plot));

    }
    @Test
    void testPlotWithoutRequiredPower() {
        Plot plot = new Plot("P1", "Bungalow", false, 2, 4, 50.0, 80.0, "photo.jpg", "WiFi");
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 50.0, 80.0);
        assertFalse(booking.isPlotMatching(plot));
    }
    @Test
    void testPlotTooSmall() {
        Plot plot = new Plot("P1", "Bungalow", true, 2, 4, 40.0, 80.0, "photo.jpg", "WiFi");
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 50.0, 80.0);
        assertFalse(booking.isPlotMatching(plot));

    }
    @Test
    void testPlotNotEnoughBedrooms(){
        Plot plot = new Plot("P1", "Bungalow", true, 1, 4, 50.0, 80.0, "photo.jpg", "WiFi");
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 50.0, 80.0);
        assertFalse(booking.isPlotMatching(plot));
    }
    @Test
    void testPlotOverBudget() {
        Plot plot = new Plot("P1", "Bungalow", true, 2, 4, 50.0, 80.0, "photo.jpg", "WiFi");
        Booking booking = new Booking("B1","G1","CA1",LocalDate.of(2026,9,20), LocalDate.of(2026,9,25),"Bungalow", 4, true,2, 50.0, 60.0);
        assertFalse(booking.isPlotMatching(plot));

    }

}
