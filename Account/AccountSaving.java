package Account;

public class AccountSaving extends Account {
    private Balance balance;
    private final double minimumBalanceToKeepSecurity = 2500.0;
    private final double highBalanceCap= 5000.0;

    public AccountSaving(String ID) {
        super(ID);
    }

    public void deposit(double amount){
        balance.addUSDBalance(amount);
    }

    public double getUSDBalance(){
         return balance.getUSDBalance();
    }


    public boolean canUseSecurity(){
        return balance.getUSDBalance() > minimumBalanceToKeepSecurity;
    }



    public boolean isHighBalance() {
        return balance.getUSDBalance() >= highBalanceCap;
    }
}
