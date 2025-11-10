package sprintone;

// basic facility: name and short description
public class Facility {
    private String name;
    private String description;

    // constructor: set name and description
    public Facility(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    // prints a short facility line
    public void printDetails() {
        System.out.println("- " + name + ": " + description);
    }
}