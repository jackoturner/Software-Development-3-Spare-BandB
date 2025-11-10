package sprintone;

public class Main {
    public static void main(String[] args) {
        // make some facilities (things guests use)
        Facility kitchen = new Facility("Kitchen", "Fully stocked with modern appliances");
        Facility washer = new Facility("Washing Machine", "Washing with included dryer function");
        Facility wifi = new Facility("Wi-Fi", "High-speed fibre optic internet access");
        Facility pool = new Facility("Pool", "Heated outdoor pool with sun loungers");

        // make places to stay and add facilities
        Flat flat = new Flat(1, "City Centre Flat", "London", 100.0, 8);
        flat.addFacility(kitchen);
        flat.addFacility(wifi);

        Hotel hotel = new Hotel(2, "Seaside Hotel", "Brighton", 150.0, 5);
        hotel.addFacility(wifi);
        hotel.addFacility(washer);

        LuxuryVilla villa = new LuxuryVilla(3, "Sand Villa", "Ibiza", 500.0, 45.0);
        villa.addFacility(kitchen);
        villa.addFacility(pool);

        // Create a user
        User jack = new User("Jack Turner", "jack@outlook.com");

        // create bookings and print them
        Booking b1 = new Booking(jack, flat, "2025-11-01", "2025-11-05");
        Booking b2 = new Booking(jack, hotel, "2025-12-10", "2025-12-15");
        Booking b3 = new Booking(jack, villa, "2026-01-02", "2026-01-09");

        // Print all bookings
        b1.printDetails();
        b2.printDetails();
        b3.printDetails();
    }
}