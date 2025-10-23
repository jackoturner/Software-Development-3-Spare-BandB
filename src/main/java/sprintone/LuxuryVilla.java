package sprintone;

public class LuxuryVilla extends Accommodation {
    private double poolSize;

    public LuxuryVilla(int id, String name, String location, double pricePerNight, double poolSize) {
        super(id, name, location, pricePerNight);
        this.poolSize = poolSize;
    }

    @Override
    public void printDetails() {
        System.out.println("Luxury Villa: " + name);
        System.out.println("Location: " + location);
        System.out.println("Price per night: £" + pricePerNight);
        System.out.println("Pool size: " + poolSize + "m²");
        System.out.println("Facilities:");
        for (Facility f : facilities) f.printDetails();
        System.out.println();
    }
}