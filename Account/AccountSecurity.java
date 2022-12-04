package Account;

import java.util.ArrayList;
import Service.*;

public class AccountSecurity extends Account {
    private final double minimumDeposit = 1000;
    private ArrayList<ServiceStock> stocks;
    private double realizedProfit;
    private double unrealizedProfit;

    public void buy(ServiceStock stock, int numBuy) {}
    public void sell(ServiceStock stock, int numSell) {}

}
