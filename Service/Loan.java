package Service;

import Personnel.Customer;

public class Loan extends Service {
    private double amount;
    private double interestRate;
    private Customer debtor;
    private double duePeriodAmount;

    public Loan(double amount, double interestRate, Customer debtor) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.debtor = debtor;
    }

    public void chargePayment() {

    }
}
