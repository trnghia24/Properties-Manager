package model;

import java.util.ArrayList;
import java.util.List;

public class ManagementList {
    List<Property> myList;

    public ManagementList() {
        myList = new ArrayList<>();
    }

    public void addProperty(Property p) {
        myList.add(p);
    }

    public void deleteProperty(Property p) {
        myList.remove(p);
    }

    public List<Property> getProperties() {
        return myList;
    }

    public List<Property> getRentedProperties() {
        List<Property> rented = new ArrayList<>();
        for (Property p : myList) {
            if (p.getStatus() == true) {
                rented.add(p);
            }
        }
        return rented;
    }

    public List<Property> getUnrentedProperties() {
        List<Property> unrented = new ArrayList<>();
        for (Property p : myList) {
            if (p.getStatus() == false) {
                unrented.add(p);
            }
        }
        return unrented;
    }

    public List<Property> getPaidProperties() {
        List<Property> paid = new ArrayList<>();
        for (Property p : myList) {
            if (p.getPaid() == true) {
                paid.add(p);
            }
        }
        return paid;
    }

    public List<Property> getUnpaidProperties() {
        List<Property> unpaid = new ArrayList<>();
        for (Property p : myList) {
            if (p.getPaid() == false) {
                unpaid.add(p);
            }
        }
        return unpaid;
    }

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

