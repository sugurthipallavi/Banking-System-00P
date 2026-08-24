/*package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        Customer customer =
                new Customer("C102", "Rahul", 22);

        CustomerDAO customerDAO = new CustomerDAO();

        customerDAO.insertCustomer(customer);
    }

	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();

	    Customer customer = customerDAO.fetchCustomer("C101");

	    if (customer != null) {
	    	System.out.println("Customer fetched successfully!");
	        System.out.println(customer);
	    }
	}
}*/
/*package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        Customer customer = new Customer("C102", "Rahul", 22);

        SavingsAccount savingsAccount =
                new SavingsAccount(
                        "S002",
                        15000,
                        customer,
                        4.5
                );

        AccountDAO accountDAO = new AccountDAO();

        accountDAO.insertAccount(savingsAccount);
    }
	public static void main(String[] args) {

        Customer customer =
                new Customer("C102", "Rahul", 22);

        CurrentAccount currentAccount =
                new CurrentAccount(
                        "C002",
                        20000,
                        customer,
                        5000
                );

        AccountDAO accountDAO = new AccountDAO();

        accountDAO.insertAccount(currentAccount);
    }
	public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        //Account account =
                accountDAO.fetchAccount("S001");
        Account account =
                accountDAO.fetchAccount("C001");

        if (account != null) {

            System.out.println("Account fetched successfully!");

            System.out.println(account);
        }
    }
	public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        //accountDAO.updateBalance("S002", 17000);
        accountDAO.updateBalance("C002", 25000);
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        TransactionDAO transactionDAO =
                new TransactionDAO();

        transactionDAO.insertTransaction(
                "S002",
                "DEPOSIT",
                2000,
                "Test deposit"
        );
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account account =
                accountDAO.fetchAccount("S002");

        if (account != null) {

            System.out.println(
                "Balance before deposit: ₹"
                + account.getBalance()
            );

            account.deposit(2000);

            System.out.println(
                "Balance after deposit: ₹"
                + account.getBalance()
            );

        } else {

            System.out.println(
                "Account S002 was not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account account =
                accountDAO.fetchAccount("S002");

        if (account != null) {

            System.out.println(
                "Balance before withdrawal: ₹"
                + account.getBalance()
            );

            account.withdraw(999999);

            System.out.println(
                "Balance after withdrawal: ₹"
                + account.getBalance()
            );

        } else {

            System.out.println(
                "Account S002 was not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account sourceAccount =
                accountDAO.fetchAccount("S002");

        Account targetAccount =
                accountDAO.fetchAccount("C001");

        if (sourceAccount != null && targetAccount != null) {

            System.out.println(
                "Source balance before transfer: ₹"
                + sourceAccount.getBalance()
            );

            System.out.println(
                "Target balance before transfer: ₹"
                + targetAccount.getBalance()
            );

            sourceAccount.transfer(
                targetAccount,
                2000
            );

            System.out.println(
                "Source balance after transfer: ₹"
                + sourceAccount.getBalance()
            );

            System.out.println(
                "Target balance after transfer: ₹"
                + targetAccount.getBalance()
            );

        } else {

            System.out.println(
                "Source or target account was not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account sourceAccount =
                accountDAO.fetchAccount("S002");

        Account targetAccount =
                accountDAO.fetchAccount("C001");

        if (sourceAccount != null && targetAccount != null) {

            System.out.println(
                "Source balance before transfer: ₹"
                + sourceAccount.getBalance()
            );

            System.out.println(
                "Target balance before transfer: ₹"
                + targetAccount.getBalance()
            );

            sourceAccount.transfer(
                targetAccount,
                2000
            );

            System.out.println(
                "Source balance after transfer: ₹"
                + sourceAccount.getBalance()
            );

            System.out.println(
                "Target balance after transfer: ₹"
                + targetAccount.getBalance()
            );

        } else {

            System.out.println(
                "Source or target account was not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        boolean success = accountDAO.transferMoney(
                "S002",
                "INVALID",
                1000
        );

        if (success) {
            System.out.println(
                "Database transfer test: SUCCESS"
            );
        } else {
            System.out.println(
                "Database transfer test: FAILED"
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account sourceAccount =
                accountDAO.fetchAccount("S002");

        Account targetAccount =
                accountDAO.fetchAccount("C001");

        if (sourceAccount != null &&
            targetAccount != null) {

            System.out.println(
                "Source balance before transfer: ₹"
                + sourceAccount.getBalance()
            );

            System.out.println(
                "Target balance before transfer: ₹"
                + targetAccount.getBalance()
            );

            sourceAccount.transfer(
                targetAccount,
                999999
            );

            System.out.println(
                "Source balance after transfer: ₹"
                + sourceAccount.getBalance()
            );

            System.out.println(
                "Target balance after transfer: ₹"
                + targetAccount.getBalance()
            );

        } else {

            System.out.println(
                "Source or target account was not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account account =
                accountDAO.fetchAccount("S002");

        if (account != null) {

            System.out.println(
                "Account Balance: ₹"
                + account.getBalance()
            );

            account.applyLoan(50000);

        } else {

            System.out.println(
                "Account not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        LoanDAO loanDAO = new LoanDAO();

        boolean success =
                loanDAO.updateLoanStatus(
                    3,
                    "APPROVED"
                );

        if (success) {

            System.out.println(
                "Loan approval test successful."
            );

        } else {

            System.out.println(
                "Loan approval test failed."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO =
                new AccountDAO();

        Account account =
                accountDAO.fetchAccount("S001");

        if (account != null) {

            System.out.println(
                "\n===== LOAN ELIGIBILITY ====="
            );

            account.calculateLoanEligibility();

            System.out.println(
                "\n===== LOAN APPLICATION ====="
            );

            account.applyLoan(30000);

        } else {

            System.out.println(
                "Account not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO =
                new AccountDAO();

        Account account =
                accountDAO.fetchAccount("S002");

        if (account != null) {

            System.out.println(
                "\n===== INVALID LOAN TEST ====="
            );

            account.applyLoan(-5000);

        } else {

            System.out.println(
                "Account not found."
            );
        }
    }
}
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO =
                new AccountDAO();

        Account account =
                accountDAO.fetchAccount("S001");

        if (account != null) {

            System.out.println(
                "\n===== INVALID LOAN STATUS TEST ====="
            );

            account.processLoan(
                1,
                "INVALID"
            );

        } else {

            System.out.println(
                "Account not found."
            );
        }
    }
}*/
package bankingSystem;

public class DatabaseTest {

    public static void main(String[] args) {

        AccountDAO accountDAO =
                new AccountDAO();

        Account account =
                accountDAO.fetchAccount("S002");

        if (account != null) {

            System.out.println(
                "\n===== APPROVE LOAN TEST ====="
            );

            account.processLoan(
            	4,
            	"REJECTED"
            );

        } else {

            System.out.println(
                "Account not found."
            );
        }
    }
}