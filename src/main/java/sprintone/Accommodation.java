package sprintone;

import java.util.ArrayList;
import java.util.List;

public abstract class Accommodation {
    protected int id;
    protected String name;
    protected String location;
    protected double pricePerNight;
    protected List<Facility> facilities;

    public Accommodation(int id, String name, String location, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.facilities = new ArrayList<>();
    }

    public void addFacility(Facility f) {
        facilities.add(f);
    }

    public double getTotalCost(int nights) {
        return pricePerNight * nights;
    }

    public abstract void printDetails();
}