package bankingSystem;

//SavingsAccount inherits from Account
public class SavingsAccount extends Account {

 private double interestRate;

 // Constructor
 public SavingsAccount(String accountNumber,
                       double balance,
                       Customer customer,
                       double interestRate) {

     super(accountNumber, balance, customer);

     this.interestRate = interestRate;
 }

 // Copy Constructor
 public SavingsAccount(SavingsAccount account) {

     super(
         account.getAccountNumber() + "_COPY",
         account.getBalance(),
         account.getCustomer()
     );

     this.interestRate = account.getInterestRate();
 }

 // Getter
 public double getInterestRate() {
     return interestRate;
 }

 // Setter
 public void setInterestRate(double interestRate) {
     this.interestRate = interestRate;
 }

 // Method Overriding
 @Override
 public double calculateInterest() {

     return getBalance() * interestRate / 100;
 }

 // Method Overriding
 @Override
 public void withdraw(double amount) {

     if (amount > getBalance()) {

         System.out.println(
             "Savings Account cannot go below zero."
         );

         return;
     }

     super.withdraw(amount);
 }

 // Method Overriding
 @Override
 public String getDetails() {

     return "Savings Account: " +
            getAccountNumber() +
            ", Balance: ₹" +
            getBalance() +
            ", Interest Rate: " +
            interestRate + "%";
 }
}
