package Service;

/**
 * Service.HoldingStock is the stock that a customer gets when he buys a stock
 * from the stock market.
 * It has:
 * ServiceStock: the stock
 * shares: number of shares the customer bought on the stock
 * buyInPrice: the price at the time the customer bought this stock
 */

public class HoldingStock {
    private ServiceStock serviceStock;
    private int shares;
    private double buyInPrice;

    public ServiceStock getServiceStock() {
        return serviceStock;
    }

    public void setServiceStock(ServiceStock serviceStock) {
        this.serviceStock = serviceStock;
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
