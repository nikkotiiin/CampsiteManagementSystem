package com.example.campsitemanagementsystem.model;

import com.example.campsitemanagementsystem.model.Booking;
import com.example.campsitemanagementsystem.model.CampingArea;
import com.example.campsitemanagementsystem.model.Plot;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CampingAreaTest {
    @Test
    public void testDuplicatePlotId() {
        CampingArea area = new CampingArea("A1", "Near the lake");

        Plot plot1 = new Plot("P1", "Bungalow", true,
                2, 4, 45.5, 80.0, "photo1.jpg", "WiFi");

        Plot plot2 = new Plot("P1", "Apartment", true,
                1, 2, 30.0, 60.0, "photo2.jpg", "Air conditioning");

        area.addPlot(plot1);
        area.addPlot(plot2);

        assertEquals(1, area.getPlotCount());
    }
    @Test
    public void testGetDescription() {
        CampingArea area = new CampingArea("A1","Quiet area" );
        assertEquals("Quiet area",area.getDescription());

    }
    @Test
    public void testEmptyCampingArea() {
        CampingArea area = new CampingArea("A2","Quiet area" );
        assertEquals(0, area.getPlotCount());

    }
    @Test
    public void testAddPlot() {
        CampingArea area = new CampingArea("A2","Quiet area" );
        Plot plot1 = new Plot("P1", "Bungalow", true,
                2, 4, 45.5, 80.0, "photo1.jpg", "WiFi");
        area.addPlot(plot1);
        assertEquals(1,area.getPlotCount());

    }
    @Test
    public void testGetPlot(){
        CampingArea area = new CampingArea("A2","Quiet area" );
        Plot plot1 = new Plot("P1", "Bungalow", true,
                2, 4, 45.5, 80.0, "photo1.jpg", "WiFi");
        Plot plot2 = new Plot("P2", "Apartment", true,
                1, 2, 30.0, 60.0, "photo2.jpg", "Air conditioning");

        area.addPlot(plot1);
        area.addPlot(plot2);
        assertEquals("P2", area.getPlot(0).getId());
        assertNull(area.getPlot(2));
    }
    @Test
    void testFindMatchingPlot() {
        CampingArea area = new CampingArea("CA1", "Quiet area");

        Plot plot1 = new Plot(
                "P1", "Apartment", true,
                2, 4, 50.0, 80.0,
                "apartment.jpg", "WiFi"
        );

        Plot plot2 = new Plot(
                "P2", "Bungalow", true,
                2, 4, 50.0, 80.0,
                "bungalow.jpg", "WiFi"
        );

        area.addPlot(plot1);
        area.addPlot(plot2);

        Booking booking = new Booking(
                "B1", "G1", "CA1",
                LocalDate.of(2026, 9, 20),
                LocalDate.of(2026, 9, 25),
                "Bungalow", 4, true,
                2, 50.0, 100.0
        );


        Plot result = area.findMatchingPlot(booking);


        assertNotNull(result);
        assertEquals("P2", result.getId());
    }
    @Test
    void testFindMatchingPlotNoMatch() {
        CampingArea area = new CampingArea("CA1", "Quiet area");

        Plot plot = new Plot(
                "P1", "Apartment", true,
                2, 4, 50.0, 80.0,
                "apartment.jpg", "WiFi"
        );

        area.addPlot(plot);

        Booking booking = new Booking(
                "B1", "G1", "CA1",
                LocalDate.of(2026, 9, 20),
                LocalDate.of(2026, 9, 25),
                "Bungalow", 4, true,
                2, 50.0, 100.0
        );

        assertNull(area.findMatchingPlot(booking));
    }
    @Test
    void testFindMatchingPlotEmptyArea() {
        CampingArea area = new CampingArea("CA1", "Quiet area");

        Booking booking = new Booking(
                "B1", "G1", "CA1",
                LocalDate.of(2026, 9, 20),
                LocalDate.of(2026, 9, 25),
                "Bungalow", 4, true,
                2, 50.0, 100.0
        );

        assertNull(area.findMatchingPlot(booking));
    }
}
