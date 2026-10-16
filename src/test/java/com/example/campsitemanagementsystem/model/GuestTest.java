package com.example.campsitemanagementsystem.model;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuestTest {
    @Test
    public void testGuestName() {
        Guest guest = new Guest("G1","Anna", LocalDate.of(2006,9,16), "anna@gmail.com", "Vegetarian");

        assertEquals("Anna", guest.getName());

    }
    @Test
    public void testGuestDateOfBirth(){
        Guest guest = new Guest("G1","Anna", LocalDate.of(2006,9,16), "anna@gmail.com", "Vegetarian");

        assertEquals(LocalDate.of(2006,9,16), guest.getDateOfBirth());
    }
    @Test
    public void testGuestDetails(){
        Guest guest = new Guest("G2", "Maria", LocalDate.of(2003, 5, 20),
                "maria@gmail.com", "Need wheelchair");
        assertEquals("G2", guest.getId());
        assertEquals("maria@gmail.com", guest.getContactInfo());
        assertEquals("Need wheelchair", guest.getAdditionalInfo());
    }
}
