package sprintone;

public class Flat extends Accommodation {
    private int floorNumber;

    public Flat(int id, String name, String location, double pricePerNight, int floorNumber) {
        super(id, name, location, pricePerNight);
        this.floorNumber = floorNumber;
    }

    @Override
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