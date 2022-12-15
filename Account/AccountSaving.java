package Account;

public class AccountSaving extends Account {
    private Balance balance;
    private final double minimumBalanceToKeepSecurity = 2500.0;
    private final double highBalanceCap= 5000.0;

    public AccountSaving(int ID) {
        super(ID);
    }



    public boolean canUseSecurity(){
        return balance.getUSDBalance() > minimumBalanceToKeepSecurity;
    }



    public boolean isHighBalance() {
        return balance.getUSDBalance() >= highBalanceCap;
    }
}
