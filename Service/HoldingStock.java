package Service;

import Service.Stock;

/**
 * HoldingStock is the stock that a customer gets when he buys a stock
 * from the stock market.
 * It has:
 * ServiceStock: the stock
 * shares: number of shares the customer bought on the stock
 * buyInPrice: the price at the time the customer bought this stock
 */

public class HoldingStock {
    private Stock stock;
    private int shares;
    private double buyInPrice;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public double getBuyInPrice() {
        return buyInPrice;
    }

    public void setBuyInPrice(double buyInPrice) {
        this.buyInPrice = buyInPrice;
    }
}
