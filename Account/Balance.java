package Account;

import Currency.CurrencyEURO;
import Currency.CurrencyRMB;
import Currency.CurrencyUSD;

/**
 * A class representing balance.
 */
public class Balance {
    private CurrencyUSD currencyUSD;
    private CurrencyEURO currencyEURO;
    private CurrencyRMB currencyRMB;

    public Balance(CurrencyUSD currencyUSD, CurrencyEURO currencyEURO, CurrencyRMB currencyRMB) {
        this.currencyUSD = currencyUSD;
        this.currencyEURO = currencyEURO;
        this.currencyRMB = currencyRMB;
    }

    public void takeMoney(String currencyType, double amount) throws RuntimeException{

        switch (currencyType) {
            case "USD":
                if(currencyUSD.getAmount() < amount) throw new RuntimeException("Not enough of USD to take");
                currencyUSD.setAmount(currencyUSD.getAmount() - amount);

                break;
            case "EURO":

                if(currencyEURO.getAmount() < amount) throw new RuntimeException("Not enough of EURO to take");
                currencyEURO.setAmount(currencyEURO.getAmount() - amount);
                break;
            case "RMB":
                if(currencyRMB.getAmount() < amount) throw new RuntimeException("Not enough of RMB to take");
                currencyRMB.setAmount(currencyRMB.getAmount() - amount);
                break;
        }
    }



    public double getUSDBalance(){
        return currencyUSD.getAmount();
    }
    public double getRMBBalance(){
        return currencyRMB.getAmount();
    }
    public double getEUROBalance(){
        return currencyEURO.getAmount();
    }

    public void addUSDBalance(double amount){
        currencyUSD.setAmount( currencyUSD.getAmount()  + amount);
    }
    public void addEUROBalance(double amount){
        currencyEURO.setAmount( currencyEURO.getAmount()  + amount);
    }
    public void addRMBBalance(double amount){
        currencyRMB.setAmount( currencyRMB.getAmount()  + amount);
    }

}
