package sprintone;

public class Main {
    public static void main(String[] args) {
        // Create RewardManager and register users who wish to receive points
        RewardManager rewardManager = RewardManager.getInstance();

        // Facilities
        Facility kitchen = new Facility(1, "Kitchen", "Fully stocked with modern appliances");
        Facility washer = new Facility(2, "Washing Machine", "Washing with included dryer function");
        Facility wifi = new Facility(3, "Wi-Fi", "High-speed fibre optic internet access");
        Facility pool = new Facility(4, "Pool", "Heated outdoor pool with sun loungers");

        // Create accommodations
        Flat flat = new Flat(1, "City Centre Flat", "London", 100.0, 8, 3);
        flat.addFacility(kitchen);
        flat.addFacility(wifi);

        Hotel hotel = new Hotel(2, "Seaside Hotel", "Brighton", 150.0, 5, 50);
        hotel.addFacility(wifi);
        hotel.addFacility(washer);

        LuxuryVilla villa = new LuxuryVilla(3, "Sand Villa", "Ibiza", 500.0, 45.0, 4);
        villa.addFacility(kitchen);
        villa.addFacility(pool);

        // Create users
        User jack = new User("Jack Turner", "jack@outlook.com");
        User luke   = new User("Luke Pring", "luke@gmail.com");
        User cara = new User("Cara Cecan", "cara@icloud.com");

        // Register users with RewardManager (they opt-in to rewards)
        rewardManager.addObserver(jack);
        rewardManager.addObserver(luke);
        rewardManager.addObserver(cara);

        // Booking manager
        BookingManager bm = BookingManager.getInstance();

        // Jack books the flat
        System.out.println("\n--- Jack attempts to book the flat ---");
        bm.makeBooking(jack, flat);

        // Luke attempts to book the same flat -> should be informed it's unavailable
        System.out.println("\n--- Luke attempts to book the same flat ---");
        bm.makeBooking(luke, flat);

        // Cara books the hotel and villa
        System.out.println("\n--- Cara books the hotel ---");
        bm.makeBooking(cara, hotel);

        System.out.println("\n--- Cara books the villa ---");
        bm.makeBooking(cara, villa);

        // Print all bookings & accommodations status
        System.out.println("\n=== Current system state ===");
        bm.printAllBookings();

        System.out.println("=== Accommodation status ===");
        flat.printDetails();
        hotel.printDetails();
        villa.printDetails();

        // Show loyalty points awarded
        System.out.println("=== Loyalty Points ===");
        jack.printDetails();
        luke.printDetails();
        cara.printDetails();

        // Demonstrate releasing a booking so another user can book
        System.out.println("\n--- Releasing the flat so Bob can try again ---");
        bm.releaseBooking(flat);

        System.out.println("\n--- Bob attempts to book the flat again ---");
        bm.makeBooking(luke, flat);

        // Final state
        System.out.println("\n=== Final state ===");
        bm.printAllBookings();

        System.out.println("=== Final Loyalty Points ===");
        jack.printDetails();
        luke.printDetails();
        cara.printDetails();
    }
}
