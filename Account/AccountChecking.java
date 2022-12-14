package Account;

import Currency.CurrencyEURO;
import Currency.CurrencyRMB;
import Currency.CurrencyUSD;

public class AccountChecking extends Account {
    private static double transactionFee = 50;
    private CurrencyUSD usdBalance;
    private CurrencyEURO euroBalance;
    private CurrencyRMB rmbBalance;


    public AccountChecking(String id,double usd, double euro, double rmb) {
        super(id);
        usdBalance = new CurrencyUSD(usd);
        euroBalance = new CurrencyEURO(euro);
        rmbBalance = new CurrencyRMB(rmb);
    }

    public double getUSDBalance(){return usdBalance.getAmount();}
    public double getEUROBalance(){return euroBalance.getAmount();}
    public double getRMBBalance(){return rmbBalance.getAmount();}






    public void withdrawal(String currencyType , double amount) throws  RuntimeException {
        getBalance().takeMoney(currencyType,amount);
    }
}
