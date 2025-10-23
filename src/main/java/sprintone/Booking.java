package sprintone;

public class Booking {
    private User user;
    private Accommodation accommodation;
    private String startDate;
    private String endDate;

    public Booking(User user, Accommodation accommodation, String startDate, String endDate) {
        this.user = user;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void printDetails() {
        System.out.println("Booking Details:");
        user.printDetails();
        accommodation.printDetails();
        System.out.println("From: " + startDate + " To: " + endDate);
        System.out.println("----------------------------------------\n");

    }
}