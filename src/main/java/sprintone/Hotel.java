package sprintone;

// simple hotel class with star rating
public class Hotel extends Accommodation {
    private int stars;
    private int numberOfRooms;

    // constructor: set basic info and star rating
    public Hotel(int id, String name, String location, double pricePerNight, int stars, int numberOfRooms) {
        super(id, name, location, pricePerNight);
        this.stars = stars;
        this.numberOfRooms = numberOfRooms;
    }

    public int getStars() { return stars; }
    public int getNumberOfRooms() { return numberOfRooms; }
    public void setNumberOfRooms(int n) { this.numberOfRooms = n; }

    @Override
    // prints hotel details to console
    public void printDetails() {
        System.out.println("Hotel: " + name + " (ID: " + id + ", " + stars + "★)");
        System.out.println("Location: " + location);
        System.out.println("Rooms: " + numberOfRooms + " | Price per night: £" + pricePerNight);
        System.out.println("Available: " + (available ? "Yes" : "No"));
        System.out.println("Facilities:");
        for (Facility f : facilities) f.printDetails();
        System.out.println();
    }
}