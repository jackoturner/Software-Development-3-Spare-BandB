package sprintone;

import java.util.ArrayList;
import java.util.List;

// base class for places to stay (hotels, flats, villas)
public abstract class Accommodation {
    protected int id;
    protected String name;
    protected String location;
    protected double pricePerNight;
    protected List<Facility> facilities;
    protected boolean available; // true = can be booked

    // constructor: set id, name, location and price, and start empty facilities
    public Accommodation(int id, String name, String location, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.facilities = new ArrayList<>();
        this.available = true;
    }

    // Properties
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; } 
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return available; }

    public List<Facility> getFacilities() {
        return facilities;  // Returns the actual list
    }

    // Methods
    public void addFacility(Facility f) {
        facilities.add(f);
    }

    public boolean book() {
        if (!available) return false;
        available = false;
        return true;
    }

    public boolean release() {
        if (available) return false;
        available = true;
        return true;
    }

    public double getTotalCost(int nights) {
        return pricePerNight * nights;
    }

    // Each concrete type prints their own details
    public abstract void printDetails();
}