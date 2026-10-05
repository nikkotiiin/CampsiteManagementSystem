package com.example.campsitemanagementsystem.structures;

import com.example.campsitemanagementsystem.model.Campsite;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeListTest {

    @Test
    public void testEmptyListSize() {
        NodeList<String> list = new NodeList<String>();
        assertEquals(0, list.size());
    }

    @Test
    public void testAdd() {
        NodeList<String> list = new NodeList<String>();
        list.add("Campsite");
        list.add("CampingArea");
        assertEquals(2, list.size());
    }
    @Test
    public void testGetElement() {
        NodeList<String> list = new NodeList<String>();
        list.add("Campsite");
        list.add("CampingArea");

        assertEquals("CampingArea", list.getElement(0));
        assertEquals("Campsite", list.getElement(1));

    }

}