package bankingSystem;

// Abstract Class with Static Block
public abstract class Person {

    private String name;
    private int age;

    static String organization;

    // Static Block
    static {
        organization = "SRITW Banking Organization";
        System.out.println("Static block in Person executed.");
    }

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Encapsulation - Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Encapsulation - Getter and Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Abstract Method
    public abstract String getDetails();
}
