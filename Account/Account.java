package Account;

import Currency.CurrencyEURO;
import Currency.CurrencyRMB;
import Currency.CurrencyUSD;

/**
 * An abstract class representing an account.
 */
public abstract class Account {
    private Integer accID;
    private String personnelID;
    private Balance balance;
    private static double openFee = 100;
    private static double closeFee  = 100;

    public Account(Integer ID,String personnelID){
        accID = ID;
        this.personnelID = personnelID;
        balance = new Balance(new CurrencyUSD(0.0),new CurrencyEURO(0.0),new CurrencyRMB(0.0));
    }

    public Balance getBalance() {

        return balance;
    }


    public void depositUSD(double amount){
        balance.addUSDBalance(amount);
    }
    public void depositEURO(double amount){
        balance.addEUROBalance(amount);
    }
    public void depositRMB (double amount){
        balance.addRMBBalance(amount);
    }
    public double getUSDBalance(){return balance.getUSDBalance();}
    public double getEUROBalance(){return balance.getEUROBalance();}
    public double getRMBBalance(){return balance.getRMBBalance();}

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
    public String getAccID(){return Integer.toString( accID);}

}
