package Account;

public abstract class Account {
    private String accID;
    private Balance balance;
    private static double openFee = 100;
    private static double closeFee  = 100;

    public Account(String ID){
        accID = ID;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
    public String getAccID(){return accID;}

}
