package Account;

public class AccountSaving extends Account {
    private double interestRate;
    private Balance balance;
    private final double minimumBalanceToKeepSecurity = 2500.0;

    public boolean isHighBalance() {
        return false;
    }
}
