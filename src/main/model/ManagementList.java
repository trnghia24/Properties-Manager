package model;

// Represents a list of properties to be managed
import java.util.ArrayList;
import java.util.List;

public class ManagementList {
    List<Property> myList;

    public ManagementList() {
        myList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a property to the end of the management list
    public void addProperty(Property p) {
        myList.add(p);
    }

    // MODIFIES: this
    // EFFECTS: remove a property from the management list
    public void deleteProperty(Property p) {
        myList.remove(p);
    }


    // EFFECTS: return the list of properties
    public List<Property> getProperties() {
        return myList;
    }

    // EFFECTS: return the list of properties whose status is rented
    public List<Property> getRentedProperties() {
        List<Property> rented = new ArrayList<>();
        for (Property p : myList) {
            if (p.getStatus() == true) {
                rented.add(p);
            }
        }
        return rented;
    }

    // EFFECTS: return the list of properties whose status is available for rent
    public List<Property> getNotRentedProperties() {
        List<Property> available = new ArrayList<>();
        for (Property p : myList) {
            if (p.getStatus() == false) {
                available.add(p);
            }
        }
        return available;
    }

    // EFFECTS: return the list of properties whose tenants already paid the rent
    public List<Property> getPaidProperties() {
        List<Property> paid = new ArrayList<>();
        for (Property p : myList) {
            if (p.getPaid() == true) {
                paid.add(p);
            }
        }
        return paid;
    }

    // EFFECTS: return the list of properties whose tenants did not pay the rent
    public List<Property> getUnpaidProperties() {
        List<Property> unpaid = new ArrayList<>();
        for (Property p : myList) {
            if (p.getPaid() == false) {
                unpaid.add(p);
            }
        }
        return unpaid;
    }

    // EFFECTS: return a property by searching its address in the management list
    public Property getPropertyByAddress(String a) {
        if (myList.isEmpty()) {
            return null;
        }
        for (Property p : myList) {
            if (p.getAddress().equals(a)) {
                return p;
            }
        }
        return null;
    }
}
