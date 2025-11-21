package sprintone;

// booking links a user, a place to stay and dates
public class Booking {
    private User user;
    private Accommodation accommodation;
    private boolean isActive;

    public Booking(User user, Accommodation accommodation) {
        this.user = user;
        this.accommodation = accommodation;
        this.isActive = false;
    }

    public User getUser() { return user; }
    public Accommodation getAccommodation() { return accommodation; }
    public boolean isActive() { return isActive; }

    public void confirmBooking() {
        isActive = true;
    }

    public void cancelBooking() {
        isActive = false;
    }

    public void printDetails() {
        System.out.println("Booking Details:");
        user.printDetails();
        accommodation.printDetails();
        System.out.println("Status: " + (isActive ? "CONFIRMED" : "CANCELLED/INACTIVE"));
        System.out.println("----------------------------------------\n");
    }
}