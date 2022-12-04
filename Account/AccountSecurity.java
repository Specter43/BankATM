package Account;

import java.util.ArrayList;
import Service.*;

public class AccountSecurity extends Account {
    private final double minimumDeposit = 1000;
    private ArrayList<Stock> stocks;
    private double realizedProfit;
    private double unrealizedProfit;

    public void buy(Stock stock, int numBuy) {}
    public void sell(Stock stock, int numSell) {}

}
