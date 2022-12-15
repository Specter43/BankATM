package Personnel;

import Bank.Bank;
import Service.StockMarket;

import java.util.ArrayList;

/**
 * A class representing a manager.
 */
public class Manager extends Personnel {
    private Bank bank;
    private StockMarket stockMarket;
    private ArrayList<Customer> allCustomers;

    public Manager(Bank bank, StockMarket stockMarket, ArrayList<Customer> allCustomers) {
        this.bank = bank;
        this.stockMarket = stockMarket;
        this.allCustomers = allCustomers;
    }

    public void checkSpecificCustomer(String name) {

    }
    public void checkAllCustomers() {}

    public void checkPoorCustomer() {}

    public void getDailyReport() {}

    public void updateStockPrice() {}

    public void changeStockStatus() {

    }

}
