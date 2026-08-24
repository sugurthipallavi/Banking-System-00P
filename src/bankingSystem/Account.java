package bankingSystem;

//Abstract Account Class
public abstract class Account implements TransactionService, LoanProcessing {

 // Encapsulation - Private Fields
 private String accountNumber;
 private double balance;
 private Customer customer;

 // Static Counter
 private static int accountCount = 0;

 // Final Constant
 public static final String BANK_NAME = "SRITW BANK";

 // Inner Class
 class Transaction {

     private String type;
     private double amount;

     public Transaction(String type, double amount) {
         this.type = type;
         this.amount = amount;
     }

     public void displayTransaction() {
         System.out.println(
             "Transaction Type: " + type +
             ", Amount: ₹" + amount
         );
     }
 }

 // Constructor
 public Account(String accountNumber,
                double balance,
                Customer customer) {

     this.accountNumber = accountNumber;
     this.balance = balance;
     this.customer = customer;

     //accountCount++;
 }

 // Copy Constructor
 public Account(Account account) {

     this.accountNumber = account.accountNumber;
     this.balance = account.balance;
     this.customer = account.customer;

     //accountCount++;
 }

 public static void incrementAccountCount() {
	    accountCount++;
	}
 // Getters
 public String getAccountNumber() {
     return accountNumber;
 }

 public double getBalance() {
     return balance;
 }

 public Customer getCustomer() {
     return customer;
 }

 // Setters
 public void setAccountNumber(String accountNumber) {
     this.accountNumber = accountNumber;
 }

 public void setBalance(double balance) {
     this.balance = balance;
 }

 public void setCustomer(Customer customer) {
     this.customer = customer;
 }

 // Deposit
 @Override
 public void deposit(double amount) {

     if (amount <= 0) {

         System.out.println(
             "Deposit amount must be greater than zero."
         );

         return;
     }

     double newBalance = balance + amount;

     AccountDAO accountDAO = new AccountDAO();

     accountDAO.updateBalance(
         accountNumber,
         newBalance
     );

     balance = newBalance;

     TransactionDAO transactionDAO =
             new TransactionDAO();

     transactionDAO.insertTransaction(
         accountNumber,
         "DEPOSIT",
         amount,
         "Account deposit"
     );

     log(
         "₹" + amount +
         " deposited into account " +
         accountNumber
     );
 }

 // Method Overloading
 public void deposit(double amount, String description) {

     deposit(amount);

     System.out.println(
         "Description: " + description
     );
 }

 // Withdrawal
 @Override
 public void withdraw(double amount) {

     if (amount <= 0) {

         System.out.println(
             "Withdrawal amount must be greater than zero."
         );

         return;
     }

     if (amount > balance) {

         System.out.println(
             "Insufficient balance."
         );

         return;
     }

     double newBalance = balance - amount;

     AccountDAO accountDAO = new AccountDAO();

     accountDAO.updateBalance(
         accountNumber,
         newBalance
     );

     balance = newBalance;

     TransactionDAO transactionDAO =
             new TransactionDAO();

     transactionDAO.insertTransaction(
         accountNumber,
         "WITHDRAW",
         amount,
         "Account withdrawal"
     );

     log(
         "₹" + amount +
         " withdrawn from account " +
         accountNumber
     );
 }
 // Transfer
 @Override
 public void transfer(
         Account account,
         double amount) {

     if (account == null) {

         System.out.println(
             "Target account cannot be null."
         );

         return;
     }

     if (account == this) {

         System.out.println(
             "Cannot transfer to the same account."
         );

         return;
     }

     if (amount <= 0) {

         System.out.println(
             "Transfer amount must be greater than zero."
         );

         return;
     }

     if (amount > getBalance()) {

         System.out.println(
             "Insufficient balance."
         );

         return;
     }

     AccountDAO accountDAO =
         new AccountDAO();

     boolean success =
         accountDAO.transferMoney(
             getAccountNumber(),
             account.getAccountNumber(),
             amount
         );

     if (!success) {

         return;
     }

     // Synchronize Java objects with database
     setBalance(
         getBalance() - amount
     );

     account.setBalance(
         account.getBalance() + amount
     );

     log(
         "₹" + amount +
         " transferred from " +
         getAccountNumber() +
         " to " +
         account.getAccountNumber()
     );
 }
 // Loan Processing
 @Override
 public void applyLoan(double amount) {

     if (amount <= 0) {

         System.out.println(
             "Invalid loan amount."
         );

         return;
     }

     if (getBalance() < 10000) {

         System.out.println(
             "Loan application rejected."
         );

         System.out.println(
             "Minimum balance of ₹10000 is required."
         );

         return;
     }

     LoanDAO loanDAO = new LoanDAO();

     boolean success =
             loanDAO.insertLoan(
                 accountNumber,
                 amount,
                 "PENDING"
             );

     if (success) {

         System.out.println(
             "Loan application submitted for ₹"
             + amount
         );

         loanLog(
             "Loan application received for ₹"
             + amount
         );

     } else {

         System.out.println(
             "Loan application could not be submitted."
         );
     }
 }
 public void processLoan(int loanId, String status) {

	    if (status == null ||
	        (!status.equalsIgnoreCase("APPROVED")
	        && !status.equalsIgnoreCase("REJECTED"))) {

	        System.out.println(
	            "Invalid loan status."
	        );

	        return;
	    }

	    LoanDAO loanDAO = new LoanDAO();

	    boolean success =
	            loanDAO.updateLoanStatus(
	                loanId,
	                status.toUpperCase()
	            );

	    if (success) {

	        System.out.println(
	            "Loan " + loanId +
	            " has been " +
	            status.toUpperCase()
	        );

	        loanLog(
	            "Loan status changed to "
	            + status.toUpperCase()
	        );

	    } else {

	        System.out.println(
	            "Loan processing failed."
	        );
	    }
	}

 @Override
 public void calculateLoanEligibility() {

     System.out.println(
         "\nChecking loan eligibility..."
     );

     System.out.println(
         "Account Number: " + accountNumber
     );

     System.out.println(
         "Current Balance: ₹" + balance
     );

     if (balance >= 10000) {

         System.out.println(
             "Result: Customer is eligible for a basic loan."
         );

     } else {

         System.out.println(
             "Result: Customer is not eligible for a basic loan."
         );

     }
 }

 // Abstract Methods
 public abstract double calculateInterest();

 public abstract String getDetails();

 // Display Account
 public void displayAccount() {

     System.out.println(
         "\n-----------------------------"
     );

     System.out.println(
         "Bank: " + BANK_NAME
     );

     System.out.println(
         "Account Number: " +
         accountNumber
     );

     System.out.println(
         "Customer: " +
         customer
     );

     System.out.println(
         "Balance: ₹" +
         balance
     );

     System.out.println(
         "-----------------------------"
     );
 }

 // Static Method
 public static int getTotalAccounts() {
     return accountCount;
 }

 // equals() Method
 @Override
 public boolean equals(Object obj) {

     if (this == obj) {
         return true;
     }

     if (!(obj instanceof Account)) {
         return false;
     }

     Account other = (Account) obj;

     return accountNumber.equals(
         other.accountNumber
     );
 }

 // toString() Method
 @Override
 public String toString() {

     return "Account Number: " +
            accountNumber +
            ", Customer: " +
            customer.getName() +
            ", Balance: ₹" +
            balance;
 }
}