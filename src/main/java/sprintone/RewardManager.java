package sprintone;

import java.util.ArrayList;
import java.util.List;

/* Simple observer-style manager: users can be registered to receive loyalty rewards when a booking is made. */
public class RewardManager {
    private static RewardManager instance = null;

    private List<User> observers;
    private int pointsPerBooking;

    private RewardManager() {
        observers = new ArrayList<>();
        pointsPerBooking = 10; // default reward
    }

    public static RewardManager getInstance() {
        if (instance == null) instance = new RewardManager();
        return instance;
    }

    public void addObserver(User u) {
        if (!observers.contains(u)) observers.add(u);
    }

    public void removeObserver(User u) {
        observers.remove(u);
    }

    // Notify a specific user, only reward the user who made the booking
    public void notifyBookingMade(User u) {
        if (observers.contains(u)) {
            u.addPoints(pointsPerBooking);
            System.out.println("RewardManager: awarded " + pointsPerBooking + " points to " + u.getName());
        }
    }
}