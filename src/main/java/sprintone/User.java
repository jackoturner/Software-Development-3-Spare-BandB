package sprintone;

// a simple user model, holds basic info
public class User {
    private String name;
    private String email;
    private int loyaltyPoints;

    // constructor: sets name and email
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.loyaltyPoints = 0;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getPoints() { return loyaltyPoints; }

    public void addPoints(int p) {
        loyaltyPoints += p;
    }
    
    // print user info to console
    public void printDetails() {
        System.out.println("User: " + name + " (" + email + ") - Loyalty: " + loyaltyPoints + " pts");
    }
}
