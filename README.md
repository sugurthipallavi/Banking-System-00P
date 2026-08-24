# SRITW Banking System

A simple Java-based Banking System developed using Object-Oriented Programming concepts and integrated with MySQL for persistent data storage.

## Project Overview

The SRITW Banking System demonstrates core Java OOP concepts together with JDBC-based MySQL database integration.

The system supports:

- Customer management
- Savings accounts
- Current accounts
- Deposits
- Withdrawals
- Money transfers
- Transaction recording
- Loan applications
- Loan eligibility checking
- Loan status processing
- Interest calculation
- Account management
- Database persistence

The project also demonstrates several Java OOP features such as inheritance, abstraction, encapsulation, polymorphism, interfaces, method overloading, method overriding, constructors, copy constructors, static members, final constants, inner classes, `equals()`, and `toString()`.

---

## Technologies Used

- Java
- MySQL
- JDBC
- Eclipse / VS Code
- Git
- GitHub

---

## Project Structure

```text
BankingSystem/
│
├── src/
│   └── bankingSystem/
│       ├── Account.java
│       ├── AccountDAO.java
│       ├── BankManager.java
│       ├── BankingSystemTest.java
│       ├── CurrentAccount.java
│       ├── Customer.java
│       ├── CustomerDAO.java
│       ├── DatabaseConnection.java
│       ├── LoanDAO.java
│       ├── LoanProcessing.java
│       ├── MainBankingApp.java
│       ├── SavingsAccount.java
│       ├── TransactionDAO.java
│       ├── TransactionService.java
│       └── other supporting classes
│
└── README.md
OOP Concepts Demonstrated
1. Encapsulation

Account data is stored using private fields and accessed through getters and setters.

Example fields include:

private String accountNumber;
private double balance;
private Customer customer;
2. Abstraction

Account is an abstract class.

It defines abstract methods such as:

public abstract double calculateInterest();

public abstract String getDetails();

Subclasses provide their own implementations.

3. Inheritance

The banking system uses inheritance.

For example:

Account
   |
   +---- SavingsAccount
   |
   +---- CurrentAccount

Both account types inherit common functionality from Account.

4. Polymorphism

An Account reference can refer to different account implementations.

Example:

Account account1 = savings;
Account account2 = current;

The appropriate overridden methods are executed at runtime.

5. Interfaces

The project uses interfaces for banking operations.

TransactionService

Provides:

void deposit(double amount);

void withdraw(double amount);

void transfer(Account account, double amount);
LoanProcessing

Provides:

void applyLoan(double amount);

void calculateLoanEligibility();

Both interfaces also demonstrate default interface methods for logging.

6. Method Overloading

The project demonstrates method overloading through deposit operations.

Example:

deposit(double amount);

deposit(double amount, String description);
7. Method Overriding

SavingsAccount and CurrentAccount override methods inherited from Account.

Examples include:

calculateInterest();

withdraw(double amount);

getDetails();
8. Constructors

The project uses constructors to initialize objects.

Example:

public SavingsAccount(
        String accountNumber,
        double balance,
        Customer customer,
        double interestRate) {
    ...
}
9. Copy Constructor

SavingsAccount demonstrates a copy constructor:

public SavingsAccount(SavingsAccount account) {
    ...
}
10. Static Members

The Account class maintains an account counter using a static variable.

private static int accountCount = 0;

The project also provides a static method to retrieve the account count.

11. Final Constant

The bank name is represented using a final constant:

public static final String BANK_NAME = "SRITW BANK";
12. Inner Class

Account contains an inner Transaction class used to demonstrate Java inner classes.

13. equals() and toString()

The Account class overrides:

equals()

and

toString()

for object comparison and readable object representation.

Database

The application uses MySQL for persistent storage.

Database:

banking_system

The main tables are:

customers
accounts
transactions
loans
Customers

Stores customer information such as:

Customer ID
Name
Age
Accounts

Stores account information such as:

Account number
Customer ID
Balance
Account type
Interest rate
Overdraft limit

Supported account types:

SAVINGS
CURRENT
Transactions

Stores:

Transaction ID
Account number
Transaction type
Amount
Transaction date
Description

Supported transaction types include:

DEPOSIT
WITHDRAW
TRANSFER
Loans

Stores:

Loan ID
Account number
Loan amount
Loan status
Loan date

Loan statuses include:

PENDING
APPROVED
REJECTED
Database Integration

The project uses JDBC to connect Java with MySQL.

The DAO classes are responsible for database operations.

Examples include:

AccountDAO
CustomerDAO
LoanDAO
TransactionDAO

The application supports database operations such as:

Fetching accounts
Inserting accounts
Updating account balances
Recording transactions
Processing transfers
Fetching loan information
Updating loan status
Banking Operations
Deposit

A valid deposit:

Validates the amount.
Updates the account balance.
Updates the database.
Records the transaction.
Generates a transaction log.
Withdrawal

The system validates the withdrawal amount and available balance.

Savings accounts cannot go below zero.

Current accounts support an overdraft limit.

Transfer

The transfer functionality:

Validates the source account.
Validates the destination account.
Validates the transfer amount.
Checks sufficient balance.
Updates the source account.
Updates the destination account.
Records the transfer.
Uses database transaction handling.

Database rollback is used when a transfer operation fails.

Loan Processing

The system supports:

Loan eligibility checking
Loan applications
Loan status processing
Approved loans
Rejected loans
Loan logging

Basic loan eligibility is based on the account balance.

The application also demonstrates processing a loan using statuses such as:

APPROVED
REJECTED
Testing

A separate test class is included:

BankingSystemTest.java

The test suite checks important banking and database scenarios.

The completed test run produced:

Tests Passed: 14
Tests Failed: 0
Total Tests: 14

ALL TESTS PASSED SUCCESSFULLY!
Tested Scenarios
Fetch Savings Account
Fetch Current Account
Invalid Account
Invalid Deposit
Invalid Withdrawal
Savings Overdraw Protection
Current Account Overdraft
Invalid Transfer Amount
Same Account Transfer
Invalid Destination Account
Insufficient Balance Transfer
Loan Eligibility
Interest Calculation
Java and MySQL Balance Consistency
Example Output
===== SRITW BANKING SYSTEM =====

===== DATABASE ACCOUNTS =====

Savings Account: S001
Current Account: C001

===== DEPOSIT =====

Account balance updated successfully!
Transaction recorded successfully!

===== SAVINGS WITHDRAWAL =====

Account balance updated successfully!
Transaction recorded successfully!

===== TRANSFER =====

Transfer completed successfully.

===== INTEREST CALCULATION =====

Interest for account S001: ₹220.5

===== LOAN ELIGIBILITY =====

Customer is not eligible for a basic loan.
Customer is eligible for a basic loan.

===== ACCOUNT COUNT =====

Total Accounts: 2

===== BANKING SYSTEM COMPLETED =====
How to Run the Project
1. Clone the repository
git clone <YOUR_GITHUB_REPOSITORY_URL>
2. Open the project

Open the project in Eclipse or VS Code.

3. Configure MySQL

Create the required MySQL database:

banking_system

Create the required tables:

customers
accounts
transactions
 database connection
loans
4. Configure
Update the database connection settings in:

DatabaseConnection.java

Do not commit passwords or other sensitive credentials to GitHub.

5. Add the MySQL JDBC driver

Make sure the MySQL Connector/J JDBC driver is available to the project.

6. Run the main application

Run:

MainBankingApp.java
7. Run the tests

Run:

BankingSystemTest.java

The expected result is:

Tests Passed: 14
Tests Failed: 0
Important Security Note

Do not store real database passwords, API keys, or other credentials in the GitHub repository.

For a local development environment, database configuration should be kept outside the public repository or replaced with safe placeholder values.

Future Improvements

Possible future improvements include:

User authentication
Password/PIN management
GUI or web interface
Better exception handling
More detailed transaction history
Account deletion
Customer update functionality
Automated unit testing using JUnit
Connection pooling
Configuration using environment variables
Improved database constraints and validation
Author

SRITW Banking System

Developed as a Java OOP and MySQL database integration project.


