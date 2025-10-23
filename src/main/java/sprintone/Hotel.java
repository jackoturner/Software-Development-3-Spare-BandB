package sprintone;

public class Hotel extends Accommodation {
    private int stars;

    public Hotel(int id, String name, String location, double pricePerNight, int stars) {
        super(id, name, location, pricePerNight);
        this.stars = stars;
    }

    @Override
    public void printDetails() {
        System.out.println("Hotel: " + name + " (" + stars + "★)");
        System.out.println("Location: " + location);
        System.out.println("Price per night: £" + pricePerNight);
        System.out.println("Facilities:");
        for (Facility f : facilities) f.printDetails();
        System.out.println();
    }
}