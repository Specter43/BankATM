package Service;

/**
 * A class representing a stock.
 */
public class Stock {
    private String name;
    private double price;
    private boolean tradable;

    public Stock(String name, double price, boolean tradable) {
        this.name = name;
        this.price = price;
        this.tradable = tradable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
