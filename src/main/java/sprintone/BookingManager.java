package sprintone;

import java.util.ArrayList;
import java.util.List;

/* Singleton manager that creates and tracks bookings. Ensures an item that is already booked cannot be booked by another user. Works with RewardManager class to award loyalty points. */
public class BookingManager {
    private static BookingManager instance = null;

    private List<Booking> bookings;

    private BookingManager() {
        bookings = new ArrayList<>();
    }

    public static BookingManager getInstance() {
        if (instance == null) instance = new BookingManager();
        return instance;
    }

    public List<Booking> getBookings() { return bookings; }

    // Make a booking if the accommodation is available
    public Booking makeBooking(User user, Accommodation acc) {
        if (!acc.isAvailable()) {
            System.out.println("Sorry " + user.getName() + ", the item '" + acc.getName() + "' is currently unavailable.");
            return null;
        }

        boolean success = acc.book();
        if (!success) {
            System.out.println("Booking failed: '" + acc.getName() + "' could not be marked as booked.");
            return null;
        }

        Booking b = new Booking(user, acc);
        b.confirmBooking();
        bookings.add(b);

        // Notify the reward manager
        RewardManager.getInstance().notifyBookingMade(user);

        System.out.println("BookingManager: booking confirmed for " + user.getName() + " -> " + acc.getName());
        return b;
    }

    // Release an accommodation/cancel the booking
    public boolean releaseBooking(Accommodation acc) {
        Booking found = null;
        for (Booking b : bookings) {
            if (b.getAccommodation().getId() == acc.getId() && b.isActive()) {
                found = b;
                break;
            }
        }
        if (found == null) {
            System.out.println("No active booking found for accommodation ID " + acc.getId());
            return false;
        }

        found.cancelBooking();
        boolean released = acc.release();
        if (released) {
            System.out.println("BookingManager: released booking for " + acc.getName());
            return true;
        } else {
            System.out.println("BookingManager: could not release accommodation " + acc.getName());
            return false;
        }
    }

    public void printAllBookings() {
        System.out.println("=== All Bookings ===");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                b.printDetails();
            }
        }
    }
}