package sprintone;

// a simple user model, holds basic info
public class User {
    private String name;
    private String email;

    // constructor: sets name and email
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // print user info to console
    public void printDetails() {
        System.out.println("User: " + name + ", Email: " + email);
    }
}
