package Account;

public abstract class Account {
    private int accID;
    private Balance balance;
    private static double openFee = 100;
    private static double closeFee  = 100;

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
