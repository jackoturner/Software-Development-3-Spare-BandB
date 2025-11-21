package sprintone;

// small flat class for apartments
public class Flat extends Accommodation {
    private int floorNumber;
    private int maxOccupancy;

    // constructor: set floor number
    public Flat(int id, String name, String location, double pricePerNight, int floorNumber, int maxOccupancy) {
        super(id, name, location, pricePerNight);
        this.floorNumber = floorNumber;
        this.maxOccupancy = maxOccupancy;
    }

    public int getFloorNumber() { return floorNumber; }
    public int getMaxOccupancy() { return maxOccupancy; }
    public void setMaxOccupancy(int occ) { this.maxOccupancy = occ; }

    @Override
    // prints flat info
    public void printDetails() {
        System.out.println("Flat: " + name + " (ID: " + id + ")");
        System.out.println("Location: " + location);
        System.out.println("Price per night: £" + pricePerNight);
        System.out.println("Floor: " + floorNumber + " | Max occupancy: " + maxOccupancy);
        System.out.println("Available: " + (available ? "Yes" : "No"));
        System.out.println("Facilities:");
        for (Facility f : facilities) f.printDetails();
        System.out.println();
    }
}