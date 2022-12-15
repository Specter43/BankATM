package Service;

import Personnel.Customer;

/**
 * A class representing a loan.
 */
public class Loan extends Service {
    private double amount;
    private double interestRate;
    private Customer debtorID;

    public Loan(double amount, double interestRate, Customer debtorID) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.debtorID = debtorID;
    }

    public void payLoan() {

    }
}
