package Service;

import Input.FileOperator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockMarket extends Service {

    static private StockMarket market;
    private HashMap<LocalDate, List<Stock>> stocks;

    private StockMarket() {
        initializeStockMarket();
    }

    public void initializeStockMarket() {
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> stockData = fileOperator.readFile("Service/Stocks.txt");
        HashMap<LocalDate, List<Stock>> stocks = new HashMap<LocalDate, List<Stock>>();
        for (int i = 0; i < stockData.get("date").size(); i++) {
            LocalDate date = LocalDate.parse(stockData.get("date").get(i));
            if (!stocks.containsKey(date)) stocks.put(date, new ArrayList<>());
            String name = stockData.get("name").get(i);
            double price = Double.parseDouble(stockData.get("price").get(i));
            boolean tradable = Boolean.parseBoolean(stockData.get("tradable").get(i));
            Stock newStock = new Stock(name, price, tradable);
            stocks.get(date).add(newStock);
        }
        this.stocks = stocks;
    }

    public HashMap<LocalDate, List<Stock>> getStocks() {
        return stocks;
    }

    static public StockMarket getMarket(){
        if(market == null){
            market = new StockMarket();
        }
        return market;
    }


    public double getPriceOf(String name) {
        LocalDate currentDate = java.time.LocalDate.now();
        List<Stock> todayStocks = stocks.get(currentDate);
        for (Stock stock : todayStocks) {
            if (stock.getName().equals(name)) {
                return stock.getPrice();
            }
        }
        return 0.0;
    }
}
