package com.example.campsitemanagementsystem.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}