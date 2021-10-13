package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyTest {
    private Property p;

    @BeforeEach
    public void setup() {
        p = new Property();
        p.setAddress("2623 Wesbrook Mall");
        p.setPrice(1000.0);
        p.setStatus(true);
        p.setCapacity(2);
        p.setPaid(true);

    }


    @Test
    public void testGetPrice() {
        assertEquals(p.getPrice(),1000);
    }

    @Test
    public void testGetPaid() {
        assertTrue(p.getPaid());
    }


    @Test
    public void testGetCapacity() {
        assertEquals(p.getCapacity(),2);
    }



}
