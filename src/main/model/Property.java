package model;

// Represent a property having an address, monthly rental price (in dollars), capacity of tenants, status (available
// or rented) and if the rent has been paid or not
public class Property {
    private String address;
    private Double price;
    private Integer capacity;
    private Boolean status;
    private Boolean paid;

    public Property() {

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean getPaid() {
        return paid;
    }

}
