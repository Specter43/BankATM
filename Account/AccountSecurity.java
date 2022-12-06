package Account;

import java.sql.Array;
import java.util.*;
//import java.org.*;

import Service.*;

public class AccountSecurity extends Account {
    private final double minimumDeposit = 1000;
    private double deposit;
    //private ArrayList<Map.Entry <Stock,>> stocks;
    private LinkedHashMap<String, ArrayList<HoldingStock>> openPositions  = new LinkedHashMap<>();

    private double realizedProfit = 0;

    private HanhMap<String, Double>spending;

    public void buy(HoldingStock stock) {
        int cost = stock.getPrice() * numBuy ;
        if(deposit - cost < minimumDeposit ){
            throw new RuntimeException("Cannot decrease your security deposit less than the minimum amount.");
        }
        deposit -= cost;
        if(!openPositions.containsKey(stock.getServiceStock().getName())) {
            openPositions.put(stock.getServiceStock().getName(),new ArrayList<>());
            spending.put(stock.getServiceStock().getName(),0);
        }
        openPositions.get(stock.getServiceStock().getName()).add(stock);
        spending.put(stock.getServiceStock().getName(),spending.get(stock.getName()) + cost);
    }

    public double getUnrealizedProfit(){
        double unrealizedProfit = 0;
        for(Map.Entry<String,ArrayList<HoldingStock>> e : openPositions.entrySet()){
            double currentPrice = StockMarketService.getPriceOf(e.getKey());
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

        int  income = StockMarket.getCurrentPrice(stockName) * numSell;

        sellFIFO(stockName,numSell);


    }

}
