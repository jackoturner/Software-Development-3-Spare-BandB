package sprintone;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void printDetails() {
        System.out.println("User: " + name + ", Email: " + email);
    }
}
