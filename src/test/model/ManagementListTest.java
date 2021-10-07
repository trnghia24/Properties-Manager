package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagementListTest {
    private ManagementList testList;
    private Property p1;
    private Property p2;
    private Property p3;
    private List propertyList;

    @BeforeEach
    public void setup() {
        testList = new ManagementList();
        p1 = new Property();
        p1.setAddress("2623 Wesbrook Mall");
        p1.setPrice(1000.0);
        p1.setStatus(true);
        p1.setCapacity(2);
        p1.setPaid(true);

        p2 = new Property();
        p2.setAddress("2623 Fraser Street");
        p2.setPrice(1500.0);
        p2.setStatus(false);
        p2.setCapacity(10);
        p2.setPaid(false);

        p3 = new Property();
        p3.setAddress("2623 Camosun Street");
        p3.setPrice(1500.0);
        p3.setStatus(true);
        p3.setCapacity(10);
        p3.setPaid(false);

        propertyList = testList.getProperties();
    }

    @Test
    public void testAddProperty() {
        testList.addProperty(p1);
        assertTrue(propertyList.contains(p1));
        assertEquals(1, propertyList.size());

        testList.addProperty(p2);
        assertTrue(propertyList.contains(p2));
        assertEquals(2, propertyList.size());

    }

    @Test
    public void testDeleteProperty() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        testList.deleteProperty(p1);
        assertFalse(propertyList.contains(p1));
        assertTrue(propertyList.contains(p2));
        assertEquals(1, propertyList.size());
    }

    @Test
    public void testDeletePropertyFail() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        testList.deleteProperty(p3);
        assertTrue(propertyList.contains(p1));
        assertTrue(propertyList.contains(p2));
        assertEquals(2, propertyList.size());
    }

    @Test
    public void testGetPropertiesNull() {
        assertTrue(propertyList.isEmpty());
        assertEquals(0, propertyList.size());
    }

    @Test
    public void testGetPropertiesNotNull() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        assertTrue(propertyList.contains(p1));
        assertTrue(propertyList.contains(p2));
        assertEquals(2, propertyList.size());
    }

    @Test
    public void testRentedNull() {
        testList.addProperty(p2);
        List<Property> rentedList = testList.getRentedProperties();
        assertTrue(rentedList.isEmpty());
        assertEquals(0, rentedList.size());

    }

    @Test
    public void testRentedNotNull() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        testList.addProperty(p3);
        List<Property> rentedList = testList.getRentedProperties();
        assertFalse(rentedList.isEmpty());
        assertTrue(rentedList.contains(p1));
        assertTrue(rentedList.contains(p3));
        assertEquals(2, rentedList.size());

    }

    @Test
    public void testUnrentedNull() {
        testList.addProperty(p1);
        testList.addProperty(p3);
        List<Property> unrentedList = testList.getUnrentedProperties();
        assertTrue(unrentedList.isEmpty());
        assertEquals(0, unrentedList.size());

    }

    @Test
    public void testUnrentedNotNull() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        testList.addProperty(p3);
        List<Property> unrentedList = testList.getUnrentedProperties();
        assertFalse(unrentedList.isEmpty());
        assertFalse(unrentedList.contains(p1));
        assertFalse(unrentedList.contains(p3));
        assertTrue(unrentedList.contains(p2));
        assertEquals(1, unrentedList.size());

    }

    @Test
    public void testPaidNull() {
        testList.addProperty(p2);
        testList.addProperty(p3);
        List<Property> paidList = testList.getPaidProperties();
        assertTrue(paidList.isEmpty());
        assertEquals(0, paidList.size());

    }

    @Test
    public void testPaidNotNull() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        testList.addProperty(p3);
        List<Property> paidList = testList.getPaidProperties();
        assertFalse(paidList.isEmpty());
        assertTrue(paidList.contains(p1));
        assertFalse(paidList.contains(p2));
        assertFalse(paidList.contains(p3));
        assertEquals(1, paidList.size());


    }

    @Test
    public void testUnpaidNull() {
        testList.addProperty(p1);
        List<Property> unpaidList = testList.getUnpaidProperties();
        assertTrue(unpaidList.isEmpty());
        assertEquals(0, unpaidList.size());

    }

    @Test
    public void testUnpaidNotNull() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        testList.addProperty(p3);
        List<Property> unpaidList = testList.getUnpaidProperties();
        assertFalse(unpaidList.isEmpty());
        assertFalse(unpaidList.contains(p1));
        assertTrue(unpaidList.contains(p2));
        assertTrue(unpaidList.contains(p3));
        assertEquals(2, unpaidList.size());
    }

    @Test
    public void testGetPropertyByAddress() {
        testList.addProperty(p1);
        testList.addProperty(p2);
        testList.addProperty(p3);
        assertEquals(testList.getPropertyByAddress("2623 Camosun Street"), p3);
    }
}