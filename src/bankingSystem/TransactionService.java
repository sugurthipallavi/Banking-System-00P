package bankingSystem;

// Interface for Banking Transactions
public interface TransactionService {

    void deposit(double amount);

    void withdraw(double amount);

    void transfer(Account account, double amount);

    // Default Interface Method
    default void log(String message) {
        System.out.println("TRANSACTION LOG: " + message);
    }
}
