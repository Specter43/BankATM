package Account;

public abstract class Account {
    private Balance balance;
    private double openFee;
    private double closeFee;

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
