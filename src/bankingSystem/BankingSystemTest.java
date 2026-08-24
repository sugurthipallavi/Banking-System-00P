package bankingSystem;

public class BankingSystemTest {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("     SRITW BANKING SYSTEM TESTS");
        System.out.println("======================================");

        AccountDAO accountDAO = new AccountDAO();

        int passed = 0;
        int failed = 0;

        // =====================================================
        // TEST 1 - FETCH SAVINGS ACCOUNT
        // =====================================================

        System.out.println("\n===== TEST 1: FETCH SAVINGS ACCOUNT =====");

        Account savings =
                accountDAO.fetchAccount("S001");

        if (savings != null) {

            System.out.println("PASS: S001 fetched successfully.");
            System.out.println(
                "Balance: ₹" + savings.getBalance()
            );

            passed++;

        } else {

            System.out.println("FAIL: S001 could not be fetched.");
            failed++;
        }


        // =====================================================
        // TEST 2 - FETCH CURRENT ACCOUNT
        // =====================================================

        System.out.println("\n===== TEST 2: FETCH CURRENT ACCOUNT =====");

        Account current =
                accountDAO.fetchAccount("C001");

        if (current != null) {

            System.out.println("PASS: C001 fetched successfully.");
            System.out.println(
                "Balance: ₹" + current.getBalance()
            );

            passed++;

        } else {

            System.out.println("FAIL: C001 could not be fetched.");
            failed++;
        }


        // =====================================================
        // TEST 3 - INVALID ACCOUNT
        // =====================================================

        System.out.println("\n===== TEST 3: INVALID ACCOUNT =====");

        Account invalid =
                accountDAO.fetchAccount("INVALID");

        if (invalid == null) {

            System.out.println(
                "PASS: Invalid account correctly rejected."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Invalid account was unexpectedly found."
            );

            failed++;
        }


        // =====================================================
        // TEST 4 - INVALID DEPOSIT
        // =====================================================

        System.out.println("\n===== TEST 4: INVALID DEPOSIT =====");

        double beforeDeposit =
                savings.getBalance();

        savings.deposit(0);

        double afterDeposit =
                savings.getBalance();

        if (beforeDeposit == afterDeposit) {

            System.out.println(
                "PASS: Invalid deposit did not change balance."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Invalid deposit changed balance."
            );

            failed++;
        }


        // =====================================================
        // TEST 5 - INVALID WITHDRAWAL
        // =====================================================

        System.out.println("\n===== TEST 5: INVALID WITHDRAWAL =====");

        double beforeWithdrawal =
                savings.getBalance();

        savings.withdraw(-500);

        double afterWithdrawal =
                savings.getBalance();

        if (beforeWithdrawal == afterWithdrawal) {

            System.out.println(
                "PASS: Invalid withdrawal did not change balance."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Invalid withdrawal changed balance."
            );

            failed++;
        }


        // =====================================================
        // TEST 6 - SAVINGS OVERDRAW PROTECTION
        // =====================================================

        System.out.println(
            "\n===== TEST 6: SAVINGS OVERDRAW PROTECTION ====="
        );

        double beforeOverdraw =
                savings.getBalance();

        savings.withdraw(
                beforeOverdraw + 10000
        );

        double afterOverdraw =
                savings.getBalance();

        if (beforeOverdraw == afterOverdraw) {

            System.out.println(
                "PASS: Savings account prevented overdrawing."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Savings account balance changed incorrectly."
            );

            failed++;
        }


        // =====================================================
        // TEST 7 - CURRENT ACCOUNT OVERDRAFT
        // =====================================================

        System.out.println(
            "\n===== TEST 7: CURRENT ACCOUNT OVERDRAFT ====="
        );

        if (current instanceof CurrentAccount) {

            CurrentAccount currentAccount =
                    (CurrentAccount) current;

            double overdraftLimit =
                    currentAccount.getOverdraftLimit();

            System.out.println(
                "Current Account Balance: ₹" +
                currentAccount.getBalance()
            );

            System.out.println(
                "Overdraft Limit: ₹" +
                overdraftLimit
            );

            System.out.println(
                "PASS: Current account overdraft information available."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: C001 is not a CurrentAccount."
            );

            failed++;
        }


        // =====================================================
        // TEST 8 - INVALID TRANSFER AMOUNT
        // =====================================================

        System.out.println(
            "\n===== TEST 8: INVALID TRANSFER AMOUNT ====="
        );

        double sourceBefore =
                savings.getBalance();

        double targetBefore =
                current.getBalance();

        savings.transfer(
                current,
                0
        );

        double sourceAfter =
                savings.getBalance();

        double targetAfter =
                current.getBalance();

        if (sourceBefore == sourceAfter &&
            targetBefore == targetAfter) {

            System.out.println(
                "PASS: Invalid transfer amount rejected."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Invalid transfer changed balance."
            );

            failed++;
        }


        // =====================================================
        // TEST 9 - SAME ACCOUNT TRANSFER
        // =====================================================

        System.out.println(
            "\n===== TEST 9: SAME ACCOUNT TRANSFER ====="
        );

        double sameBefore =
                savings.getBalance();

        savings.transfer(
                savings,
                100
        );

        double sameAfter =
                savings.getBalance();

        if (sameBefore == sameAfter) {

            System.out.println(
                "PASS: Same-account transfer rejected."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Same-account transfer changed balance."
            );

            failed++;
        }


        // =====================================================
        // TEST 10 - INVALID DESTINATION ACCOUNT
        // =====================================================

        System.out.println(
            "\n===== TEST 10: INVALID DESTINATION ACCOUNT ====="
        );

        Account invalidTarget =
                accountDAO.fetchAccount("INVALID");

        if (invalidTarget == null) {

            System.out.println(
                "PASS: Invalid destination account correctly detected."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Invalid destination account exists."
            );

            failed++;
        }


        // =====================================================
        // TEST 11 - INSUFFICIENT BALANCE TRANSFER
        // =====================================================

        System.out.println(
            "\n===== TEST 11: INSUFFICIENT BALANCE TRANSFER ====="
        );

        double sourceBeforeInvalidTransfer =
                savings.getBalance();

        double targetBeforeInvalidTransfer =
                current.getBalance();

        double invalidTransferAmount =
                sourceBeforeInvalidTransfer + 100000;

        savings.transfer(
                current,
                invalidTransferAmount
        );

        double sourceAfterInvalidTransfer =
                savings.getBalance();

        double targetAfterInvalidTransfer =
                current.getBalance();

        if (sourceBeforeInvalidTransfer ==
                sourceAfterInvalidTransfer
            &&
            targetBeforeInvalidTransfer ==
                targetAfterInvalidTransfer) {

            System.out.println(
                "PASS: Insufficient-balance transfer rejected."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Invalid transfer changed account balances."
            );

            failed++;
        }


        // =====================================================
        // TEST 12 - LOAN ELIGIBILITY
        // =====================================================

        System.out.println(
            "\n===== TEST 12: LOAN ELIGIBILITY ====="
        );

        savings.calculateLoanEligibility();
        current.calculateLoanEligibility();

        System.out.println(
            "PASS: Loan eligibility methods executed."
        );

        passed++;


        // =====================================================
        // TEST 13 - INTEREST CALCULATION
        // =====================================================

        System.out.println(
            "\n===== TEST 13: INTEREST CALCULATION ====="
        );

        double interest =
                savings.calculateInterest();

        System.out.println(
            "S001 Interest: ₹" + interest
        );

        if (interest >= 0) {

            System.out.println(
                "PASS: Interest calculation executed."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Invalid interest result."
            );

            failed++;
        }


        // =====================================================
        // TEST 14 - DATABASE BALANCE CONSISTENCY
        // =====================================================

        System.out.println(
            "\n===== TEST 14: DATABASE BALANCE CONSISTENCY ====="
        );

        Account savingsFromDB =
                accountDAO.fetchAccount("S001");

        Account currentFromDB =
                accountDAO.fetchAccount("C001");

        boolean savingsConsistent =
                Math.abs(
                    savings.getBalance()
                    -
                    savingsFromDB.getBalance()
                ) < 0.001;

        boolean currentConsistent =
                Math.abs(
                    current.getBalance()
                    -
                    currentFromDB.getBalance()
                ) < 0.001;

        System.out.println(
            "S001 Java Balance: ₹" +
            savings.getBalance()
        );

        System.out.println(
            "S001 DB Balance: ₹" +
            savingsFromDB.getBalance()
        );

        System.out.println(
            "C001 Java Balance: ₹" +
            current.getBalance()
        );

        System.out.println(
            "C001 DB Balance: ₹" +
            currentFromDB.getBalance()
        );

        if (savingsConsistent &&
            currentConsistent) {

            System.out.println(
                "PASS: Java and database balances are consistent."
            );

            passed++;

        } else {

            System.out.println(
                "FAIL: Java and database balances are inconsistent."
            );

            failed++;
        }


        // =====================================================
        // FINAL RESULT
        // =====================================================

        System.out.println(
            "\n======================================"
        );

        System.out.println(
            "           TEST SUMMARY"
        );

        System.out.println(
            "======================================"
        );

        System.out.println(
            "Tests Passed: " + passed
        );

        System.out.println(
            "Tests Failed: " + failed
        );

        System.out.println(
            "Total Tests: " +
            (passed + failed)
        );

        if (failed == 0) {

            System.out.println(
                "\nALL TESTS PASSED SUCCESSFULLY!"
            );

        } else {

            System.out.println(
                "\nSOME TESTS FAILED."
            );
        }

        System.out.println(
            "\n===== BANKING SYSTEM TEST COMPLETED ====="
        );
    }
}