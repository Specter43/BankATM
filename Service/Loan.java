package Service;

import Personnel.Customer;

import java.util.Date;

public class Loan extends Service {
    private double amount;
    private double interestRate;
    private Date dueDate;
    private String duePeriod;
    private Customer debtor;
    private double duePeriodAmount;

    public Loan(double amount, double interestRate, Date dueDate, String duePeriod, Customer debtor) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.dueDate = dueDate;
        this.duePeriod = duePeriod;
        this.debtor = debtor;
    }

}
