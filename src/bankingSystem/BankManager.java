package bankingSystem;

import java.util.ArrayList;
import java.util.List;

// Business / Management Class
public class BankManager {

    private List<Account> accounts;

    // Constructor
    public BankManager() {
        accounts = new ArrayList<>();
    }

    // Add Account
    public void addAccount(Account account)
            throws InvalidAccountException {

        if (account == null) {
            throw new InvalidAccountException(
                "Account cannot be null."
            );
        }

        // Avoid duplicate accounts
        if (findAccountWithoutException(
                account.getAccountNumber()) != null) {

            System.out.println(
                "Account already exists: "
                + account.getAccountNumber()
            );

            return;
        }

        accounts.add(account);
        
        Account.incrementAccountCount();
        
        System.out.println(
            "Account added successfully: "
            + account.getAccountNumber()
        );
    }

    // Find Account
    public Account findAccount(String accountNumber)
            throws InvalidAccountException {

        if (accountNumber == null ||
            accountNumber.trim().isEmpty()) {

            throw new InvalidAccountException(
                "Account number cannot be empty."
            );
        }

        for (Account account : accounts) {

            if (account.getAccountNumber()
                    .equals(accountNumber)) {

                return account;
            }
        }

        throw new InvalidAccountException(
            "Account not found: " + accountNumber
        );
    }

    // Internal search without exception
    private Account findAccountWithoutException(
            String accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber()
                    .equals(accountNumber)) {

                return account;
            }
        }

        return null;
    }

    // Deposit Money
    public void deposit(
            String accountNumber,
            double amount)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        account.deposit(amount);
    }

    // Deposit Money with Description
    public void deposit(
            String accountNumber,
            double amount,
            String description)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        account.deposit(amount, description);
    }

    // Withdraw Money
    public void withdraw(
            String accountNumber,
            double amount)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        account.withdraw(amount);
    }

    // Transfer Money
    public void transfer(
            String fromAccountNumber,
            String toAccountNumber,
            double amount)
            throws InvalidAccountException {

        Account fromAccount =
            findAccount(fromAccountNumber);

        Account toAccount =
            findAccount(toAccountNumber);

        fromAccount.transfer(
            toAccount,
            amount
        );
    }

    // Display All Accounts
    public void displayAllAccounts() {

        System.out.println(
            "\n===== ALL BANK ACCOUNTS ====="
        );

        if (accounts.isEmpty()) {

            System.out.println(
                "No accounts available."
            );

            return;
        }

        for (Account account : accounts) {

            System.out.println(
                account.getDetails()
            );
        }
    }

    // Display Account Details
    public void displayAccount(
            String accountNumber)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        account.displayAccount();
    }

    // Calculate Interest
    public void showInterest(
            String accountNumber)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        double interest =
            account.calculateInterest();

        System.out.println(
            "Interest for account " +
            accountNumber +
            ": ₹" +
            interest
        );
    }

    // Check Loan Eligibility
    public void checkLoanEligibility(
            String accountNumber)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        account.calculateLoanEligibility();
    }

    // Apply Loan
    public void applyLoan(
            String accountNumber,
            double amount)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        account.applyLoan(amount);
    }

    // View Loans
    public void viewLoans(
            String accountNumber)
            throws InvalidAccountException {

        // Make sure the account exists
        findAccount(accountNumber);

        LoanDAO loanDAO =
            new LoanDAO();

        loanDAO.fetchLoan(accountNumber);
    }

    // Process Loan
    public void processLoan(
            String accountNumber,
            int loanId,
            String status)
            throws InvalidAccountException {

        Account account =
            findAccount(accountNumber);

        account.processLoan(
            loanId,
            status
        );
    }

    // Display Total Accounts
    public void displayTotalAccounts() {

        System.out.println(
            "Total Accounts: " +
            Account.getTotalAccounts()
        );
    }
}