package Account;

public class AccountSaving extends Account {
    private double interestRate;
    private Balance balance;
    private final double minimumBalanceToKeepSecurity = 2500.0;
    private final double highBalanceCap= 5000.0;

    public boolean canUseSecurity(){
        return balance.getUSDBalance() > minimumBalanceToKeepSecurity;
    }



    public boolean isHighBalance() {
        return balance.getUSDBalance() >= highBalanceCap;
    }
}
