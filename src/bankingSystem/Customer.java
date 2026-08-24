package bankingSystem;

// Customer inherits from Person
public class Customer extends Person {

    private String customerId;

    // Constructor
    public Customer(String customerId, String name, int age) {
        super(name, age);
        this.customerId = customerId;
    }

    // Getter
    public String getCustomerId() {
        return customerId;
    }

    // Setter
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Method Overriding
    @Override
    public String getDetails() {
        return "Customer ID: " + customerId +
               ", Name: " + getName() +
               ", Age: " + getAge();
    }

    // toString() method
    @Override
    public String toString() {
        return getDetails();
    }
}