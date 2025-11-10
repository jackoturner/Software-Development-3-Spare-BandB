package sprintone;

// simple hotel class with star rating
public class Hotel extends Accommodation {
    private int stars;

    // constructor: set basic info and star rating
    public Hotel(int id, String name, String location, double pricePerNight, int stars) {
        super(id, name, location, pricePerNight);
        this.stars = stars;
    }

    @Override
    // prints hotel details to console
    public void printDetails() {
        System.out.println("Hotel: " + name + " (" + stars + "★)");
        System.out.println("Location: " + location);
        System.out.println("Price per night: £" + pricePerNight);
        System.out.println("Facilities:");
        for (Facility f : facilities) f.printDetails();
        System.out.println();
    }
}