package sprintone;

// small flat class for apartments
public class Flat extends Accommodation {
    private int floorNumber;

    // constructor: set floor number
    public Flat(int id, String name, String location, double pricePerNight, int floorNumber) {
        super(id, name, location, pricePerNight);
        this.floorNumber = floorNumber;
    }

    @Override
    // prints flat info
    public void printDetails() {
        System.out.println("Flat: " + name);
        System.out.println("Location: " + location);
        System.out.println("Price per night: £" + pricePerNight);
        System.out.println("Floor: " + floorNumber);
        System.out.println("Facilities:");
        for (Facility f : facilities) f.printDetails();
        System.out.println();
    }
}