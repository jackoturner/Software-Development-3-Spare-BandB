package sprintone;

// basic facility: name and short description
public class Facility {
    private int id;
    private String name;
    private String description;

    // constructor: set name and description
    public Facility(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Accessors
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    // Mutators
    public void setDescription(String description) { this.description = description; }


    // prints a short facility line
    public void printDetails() {
        System.out.println("- " + name + ": " + description);
    }
    
    public String toString() {
        return name + " (" + description + ")";
    }
}