package Account;

/**
 * A class representing a saving account.
 */
public class AccountSaving extends Account {
    private Balance balance;
    private final double minimumBalanceToKeepSecurity = 2500.0;
    private final double highBalanceCap= 5000.0;

    public AccountSaving(int id,String pID,double usd, double euro, double rmb) {
        super(id,pID);
        depositUSD(usd);
        depositEURO(euro);
        depositRMB(rmb);
    }



    public boolean canUseSecurity(){
        return balance.getUSDBalance() > minimumBalanceToKeepSecurity;
    }



    public boolean isHighBalance() {
        return balance.getUSDBalance() >= highBalanceCap;
    }
}
