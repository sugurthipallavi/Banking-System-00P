package bankingSystem;

public class TransferTest {

    public static void main(String[] args) {

        AccountDAO accountDAO =
                new AccountDAO();

        Account source =
                accountDAO.fetchAccount("S001");

        Account target =
                accountDAO.fetchAccount("C001");

        if (source == null || target == null) {

            System.out.println(
                "Source or target account not found."
            );

            return;
        }

        System.out.println(
            "\n===== BEFORE INVALID TRANSFER ====="
        );

        System.out.println(
            "S001 Balance: ₹" +
            source.getBalance()
        );

        System.out.println(
            "C001 Balance: ₹" +
            target.getBalance()
        );

        double invalidAmount =
                source.getBalance() + 100000;

        System.out.println(
            "\n===== INVALID TRANSFER ====="
        );

        System.out.println(
            "Attempting to transfer ₹" +
            invalidAmount
        );

        source.transfer(
            target,
            invalidAmount
        );

        System.out.println(
            "\n===== AFTER INVALID TRANSFER ====="
        );

        System.out.println(
            "S001 Java Balance: ₹" +
            source.getBalance()
        );

        System.out.println(
            "C001 Java Balance: ₹" +
            target.getBalance()
        );

        System.out.println(
            "\n===== DATABASE BALANCES ====="
        );

        Account sourceFromDB =
                accountDAO.fetchAccount("S001");

        Account targetFromDB =
                accountDAO.fetchAccount("C001");

        System.out.println(
            "S001 DB Balance: ₹" +
            sourceFromDB.getBalance()
        );

        System.out.println(
            "C001 DB Balance: ₹" +
            targetFromDB.getBalance()
        );
    }
}