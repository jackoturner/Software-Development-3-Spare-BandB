package sprintone;

// fancy villa class with a pool
public class LuxuryVilla extends Accommodation {
    private double poolSize;
    private int bedrooms;

    public LuxuryVilla(int id, String name, String location, double pricePerNight, double poolSize, int bedrooms) {
        super(id, name, location, pricePerNight);
        // constuctor: pool size, bedrooms
        this.poolSize = poolSize;
        this.bedrooms = bedrooms;
    }

    public double getPoolSize() { return poolSize; }
    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int b) { this.bedrooms = b; }

    @Override
    // prints villa data
    public void printDetails() {
        System.out.println("Luxury Villa: " + name + " (ID: " + id + ")");
        System.out.println("Location: " + location);
        System.out.println("Price per night: £" + pricePerNight);
        System.out.println("Pool size: " + poolSize + "m² | Bedrooms: " + bedrooms);
        System.out.println("Available: " + (available ? "Yes" : "No"));
        System.out.println("Facilities:");
        for (Facility f : facilities) f.printDetails();
        System.out.println();
    }
}