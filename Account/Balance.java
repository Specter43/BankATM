package Account;

import Currency.CurrencyEURO;
import Currency.CurrencyRMB;
import Currency.CurrencyUSD;

public class Balance {
    private CurrencyUSD currencyUSD;
    private CurrencyEURO currencyEURO;
    private CurrencyRMB currencyRMB;

    public Balance(CurrencyUSD currencyUSD, CurrencyEURO currencyEURO, CurrencyRMB currencyRMB) {
        this.currencyUSD = currencyUSD;
        this.currencyEURO = currencyEURO;
        this.currencyRMB = currencyRMB;
    }

    private void takeMoney(String currencyType, double amount) {
        switch (currencyType) {
            case "USD":
                currencyUSD.setAmount(currencyUSD.getAmount() - amount);
                break;
            case "EURO":
                currencyEURO.setAmount(currencyEURO.getAmount() - amount);
                break;
            case "RMB":
                currencyRMB.setAmount(currencyRMB.getAmount() - amount);
                break;
        }
    }
}
