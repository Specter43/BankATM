package Account;

import Currency.CurrencyEURO;
import Currency.CurrencyRMB;
import Currency.CurrencyUSD;

public class AccountChecking extends Account {
    private static double transactionFee = 50;
    private CurrencyUSD usdBalance;
    private CurrencyEURO euroBalance;
    private CurrencyRMB rmbBalance;

    public AccountChecking() {
        usdBalance = new CurrencyUSD(0);
        euroBalance = new CurrencyEURO(0);
        rmbBalance = new CurrencyRMB(0);
    }


    public void withdrawal(String currencyType , double amount) throws  RuntimeException {
        getBalance().takeMoney(currencyType,amount);
    }
}
