package bankingSystem;

// Interface for Loan Processing
public interface LoanProcessing {

    void applyLoan(double amount);

    void calculateLoanEligibility();

    void processLoan(int loanId, String status);

    // Default Interface Method
    default void loanLog(String message) {
        System.out.println("LOAN LOG: " + message);
    }
}