package Service;

import Personnel.PersonnelCustomer;

import java.util.Date;

public class Loan extends Service {
    private double amount;
    private double interestRate;
    private Date dueDate;
    private String duePeriod;
    private PersonnelCustomer debtor;
    private double duePeriodAmount;

    public Loan(double amount, double interestRate, Date dueDate, String duePeriod, PersonnelCustomer debtor) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.dueDate = dueDate;
        this.duePeriod = duePeriod;
        this.debtor = debtor;
    }

}
