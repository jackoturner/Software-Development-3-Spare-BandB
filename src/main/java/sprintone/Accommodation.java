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

    // constructor: set id, name, location and price, and start empty facilities
    public Accommodation(int id, String name, String location, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.facilities = new ArrayList<>();
    }

    // add a facility to this accommodation
    public void addFacility(Facility f) {
        facilities.add(f);
    }

    public double getTotalCost(int nights) {
        return pricePerNight * nights;
    }

    public abstract void printDetails();
}