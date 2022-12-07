package Factory;

import Input.FileOperator;

/**
 * Factory.StockFactory is responsible for all the operations that are related to stocks,
 * like generate stocks and put them into the memory, update a stock's price, get a stock's price,
 * etc.
 */
public class StockFactory {

    private FileOperator fileOperator;

    public StockFactory() {
    }

    /**
     * It uses the fileOperator to read the Stocks from the txt file and store all the stock
     * into the memory.
     */
    public void generateStocks(){

    }

    /**
     * It updates a new price to a stock that has the stockName
     * @param stockName the name of the stock that we want to update its price
     * @param newPrice the newest price of this stock
     */
    public void updateSingleStock(String stockName, double newPrice){

    }


    /**
     * Given the stock name, it returns the price of this stock
     * @param stockName
     * @return
     */
    public double getSingleStockPrice(String stockName){
        return 0.0;
    }
}
