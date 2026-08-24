package bankingSystem;

// CurrentAccount inherits from Account
public class CurrentAccount extends Account {

    private double overdraftLimit;

    // Constructor
    public CurrentAccount(
            String accountNumber,
            double balance,
            Customer customer,
            double overdraftLimit) {

        super(
            accountNumber,
            balance,
            customer
        );

        this.overdraftLimit = overdraftLimit;
    }

    // Getter
    public double getOverdraftLimit() {

        return overdraftLimit;
    }

    // Setter
    public void setOverdraftLimit(
            double overdraftLimit) {

        this.overdraftLimit = overdraftLimit;
    }

    // Method Overriding
    @Override
    public double calculateInterest() {

        return 0;
    }

    // Method Overriding
    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {

            System.out.println(
                "Withdrawal amount must be greater than zero."
            );

            return;
        }

        if (amount > getBalance() + overdraftLimit) {

            System.out.println(
                "Withdrawal exceeds overdraft limit."
            );

            return;
        }

        double newBalance =
            getBalance() - amount;

        AccountDAO accountDAO =
            new AccountDAO();

        accountDAO.updateBalance(
            getAccountNumber(),
            newBalance
        );

        setBalance(newBalance);

        TransactionDAO transactionDAO =
            new TransactionDAO();

        transactionDAO.insertTransaction(
            getAccountNumber(),
            "WITHDRAW",
            amount,
            "Current account withdrawal"
        );

        log(
            "₹" + amount +
            " withdrawn from Current Account " +
            getAccountNumber()
        );
    }

    // Method Overriding
    @Override
    public String getDetails() {

        return "Current Account: " +
               getAccountNumber() +
               ", Balance: ₹" +
               getBalance() +
               ", Overdraft Limit: ₹" +
               overdraftLimit;
    }
}