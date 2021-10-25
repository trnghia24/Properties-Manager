package persistence;

import model.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from this repo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkProperty(String address, Double price, Integer capacity,
                                 Boolean status, Boolean paid, Property p) {
        assertEquals(address, p.getAddress());
        assertEquals(price, p.getPrice());
        assertEquals(capacity, p.getCapacity());
        assertEquals(status, p.getStatus());
        assertEquals(paid, p.getPaid());
    }
}
