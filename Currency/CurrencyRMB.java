package Currency;


/**
 * CurrencyRMN represents the RMB currency, and it extends from the Currency abstract class.
 */
public class CurrencyRMB extends Currency {

    /**
     * The empty Constructor
     * It sets the value of the CurrencyRMB to 0
     */
    public CurrencyRMB() {
        super.setCurrencyName("RMB");
        super.setAmount(0);
    }


    /**
     * Constructor that takes an amount in and generate a CurrencyRMB that is worth that amount of money
     * @param amount the worthy value of this CurrencyRMB
     */
    public CurrencyRMB(double amount){
        super.setCurrencyName("RMB");
        super.setAmount(amount);
    }
}
