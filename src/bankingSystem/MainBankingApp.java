package bankingSystem;

import java.util.Scanner;

// Driver Class
public class MainBankingApp {

    public static void main(String[] args) {

        System.out.println(
            "===== SRITW BANKING SYSTEM ====="
        );

        Scanner scanner = new Scanner(System.in);

        BankManager manager =
            new BankManager();

        AccountDAO accountDAO =
            new AccountDAO();

        try {

            // =====================================================
            // LOAD ACCOUNTS FROM DATABASE
            // =====================================================

            System.out.println(
                "\n===== LOADING DATABASE ACCOUNTS ====="
            );

            Account savings =
                accountDAO.fetchAccount("S001");

            Account current =
                accountDAO.fetchAccount("C001");

            if (savings != null) {

                manager.addAccount(savings);

            } else {

                System.out.println(
                    "S001 account not found in database."
                );
            }

            if (current != null) {

                manager.addAccount(current);

            } else {

                System.out.println(
                    "C001 account not found in database."
                );
            }

            // =====================================================
            // DISPLAY ACCOUNTS
            // =====================================================

            System.out.println(
                "\n===== DATABASE ACCOUNTS ====="
            );

            manager.displayAllAccounts();

            // =====================================================
            // DEPOSIT
            // =====================================================

            System.out.println(
                "\n===== DEPOSIT ====="
            );

            manager.deposit(
                "S001",
                5000,
                "Main application deposit"
            );

            // =====================================================
            // WITHDRAWAL
            // =====================================================

            System.out.println(
                "\n===== SAVINGS WITHDRAWAL ====="
            );

            manager.withdraw(
                "S001",
                3000
            );

            // =====================================================
            // CURRENT ACCOUNT WITHDRAWAL
            // =====================================================

            System.out.println(
                "\n===== CURRENT ACCOUNT WITHDRAWAL ====="
            );

            manager.withdraw(
                "C001",
                2000
            );

            // =====================================================
            // TRANSFER
            // =====================================================

            System.out.println(
                "\n===== TRANSFER ====="
            );

            manager.transfer(
                "S001",
                "C001",
                2000
            );

            // =====================================================
            // UPDATED ACCOUNTS
            // =====================================================

            System.out.println(
                "\n===== UPDATED ACCOUNTS ====="
            );

            manager.displayAllAccounts();

            // =====================================================
            // INTEREST CALCULATION
            // =====================================================

            System.out.println(
                "\n===== INTEREST CALCULATION ====="
            );

            manager.showInterest("S001");

            manager.showInterest("C001");

            // =====================================================
            // LOAN ELIGIBILITY
            // =====================================================

            System.out.println(
                "\n===== LOAN ELIGIBILITY ====="
            );

            manager.checkLoanEligibility("S001");

            manager.checkLoanEligibility("C001");

            // =====================================================
            // APPLY LOAN
            // =====================================================

            System.out.println(
                "\n===== LOAN APPLICATION ====="
            );

            manager.applyLoan(
                "S001",
                50000
            );

            // =====================================================
            // VIEW LOANS
            // =====================================================

            System.out.println(
                "\n===== LOAN DETAILS ====="
            );

            manager.viewLoans("S001");

            // =====================================================
            // ACCOUNT DETAILS
            // =====================================================

            System.out.println(
                "\n===== ACCOUNT DETAILS ====="
            );

            manager.displayAccount("S001");

            // =====================================================
            // INNER CLASS DEMONSTRATION
            // =====================================================

            System.out.println(
                "\n===== INNER CLASS ====="
            );

            if (savings != null) {

                Account.Transaction transaction =
                    savings.new Transaction(
                        "Deposit",
                        5000
                    );

                transaction.displayTransaction();
            }

            // =====================================================
            // COPY CONSTRUCTOR
            // =====================================================

            System.out.println(
                "\n===== COPY CONSTRUCTOR ====="
            );

            if (savings instanceof SavingsAccount) {

                SavingsAccount savingsAccount =
                    (SavingsAccount) savings;

                SavingsAccount savingsCopy =
                    new SavingsAccount(
                        savingsAccount
                    );

                System.out.println(
                    savingsCopy.getDetails()
                );
            }

            // =====================================================
            // POLYMORPHISM
            // =====================================================

            System.out.println(
                "\n===== POLYMORPHISM ====="
            );

            Account account1 = savings;
            Account account2 = current;

            if (account1 != null) {

                System.out.println(
                    "Account 1 Interest: ₹" +
                    account1.calculateInterest()
                );
            }

            if (account2 != null) {

                System.out.println(
                    "Account 2 Interest: ₹" +
                    account2.calculateInterest()
                );
            }

            // =====================================================
            // ACCOUNT COUNT
            // =====================================================

            System.out.println(
                "\n===== ACCOUNT COUNT ====="
            );

            manager.displayTotalAccounts();

            // =====================================================
            // OPTIONAL LOAN PROCESSING
            // =====================================================

            System.out.println(
                "\n===== LOAN PROCESSING ====="
            );

            System.out.println(
                "Enter a loan ID to process."
            );

            System.out.println(
                "Enter 0 to skip."
            );

            System.out.print(
                "Loan ID: "
            );

            int loanId =
                scanner.nextInt();

            if (loanId != 0) {

                System.out.print(
                    "Enter status (APPROVED/REJECTED): "
                );

                String status =
                    scanner.next();

                manager.processLoan(
                    "S001",
                    loanId,
                    status
                );
            }

        } catch (InvalidAccountException e) {

            System.out.println(
                "Banking Error: " +
                e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                "Unexpected Error: " +
                e.getMessage()
            );

            e.printStackTrace();

        } finally {

            scanner.close();
        }

        System.out.println(
            "\n===== BANKING SYSTEM COMPLETED ====="
        );
    }
}