package Account;

import Service.HoldingStock;
import Service.Stock;
import Service.StockMarket;

import java.util.*;

/**
 * A class representing a security account.
 */
public class AccountSecurity extends Account {
    private final double minimumDeposit = 1000;
    private double deposit;
    //private ArrayList<Map.Entry <Stock,>> stocks;
    private LinkedHashMap<String, ArrayList<HoldingStock>> openPositions  = new LinkedHashMap<>();

    private double realizedProfit = 0;

    private HashMap<String, Double>spending;

    public AccountSecurity(int ID,String personnelID,double amountUSD, List<Stock> stocks) {
        super(ID,personnelID);
    }

    @Override
    public double getEUROBalance() {
        return 0.0;
    }

    @Override
    public double getRMBBalance() {
        return 0.0;
    }

    @Override
    public void depositEURO(double amount) {
        return;
    }

    @Override
    public void depositRMB(double amount) {
       return;
    }

    public void buy(HoldingStock stock) {
        double cost = stock.getBuyInPrice() * stock.getShares();
        if(deposit - cost < minimumDeposit ){
            throw new RuntimeException("Cannot decrease your security deposit less than the minimum amount.");
        }
        deposit -= cost;
        if(!openPositions.containsKey(stock.getStock().getName())) {
            openPositions.put(stock.getStock().getName(),new ArrayList<>());
            spending.put(stock.getStock().getName(),0.0);
        }
        openPositions.get(stock.getStock().getName()).add(stock);
        spending.put(stock.getStock().getName(),spending.get(stock.getStock().getName()) + cost);
    }

    public double getUnrealizedProfit(){
        double unrealizedProfit = 0;
        for(Map.Entry<String,ArrayList<HoldingStock>> e : openPositions.entrySet()){
            double currentPrice = StockMarket.getMarket().getPriceOf(e.getKey());
            for(HoldingStock hs : e.getValue()){
              unrealizedProfit+=   (currentPrice - hs.getBuyInPrice()) * hs.getShares() ;
            }
        }
        return unrealizedProfit;
    }
    public double getRrealizedProfit(){
        return realizedProfit;
    }

    private int getTotalShares(ArrayList<HoldingStock> shares){
        int sum = 0;
        for(HoldingStock hs : shares){
            sum += hs.getShares() ;
        }
        return sum;
    }
    private double getAveragePrice(ArrayList<HoldingStock> shares){

        double shareValueSum = 0;
        for(HoldingStock hs : shares){
            shareValueSum += hs.getShares() * hs.getBuyInPrice();
        }
        return shareValueSum / getTotalShares(shares);
    }

    private void sellFIFO(String stockName, int numSell){
        ArrayList<HoldingStock> pile = openPositions.get(stockName);
        while(true){
            if(pile.get(0).getShares() < numSell){
                numSell -= pile.get(0).getShares();
                pile.remove(0);
            }else{
                pile.get(0).setShares(pile.get(0).getShares() - numSell);
                break;
            }
        }

    }



    public void sell(String stockName, int numSell) throws RuntimeException{
        if(numSell < 0){
            throw new RuntimeException("Sell Number is negative");
        }
        if(numSell == 0){
            return;
        }
        //stock is current stock
        if(!openPositions.containsKey(stockName)){
            throw new RuntimeException("Cannot sell stocks that you don't have.");
        }

        if(getTotalShares(openPositions.get(stockName)) < numSell){
            throw new RuntimeException("Cannot sell more than you have.");
        }

        double income = StockMarket.getMarket().getPriceOf(stockName) * numSell;

        sellFIFO(stockName,numSell);
        realizedProfit += income;
    }

}
