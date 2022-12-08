package Currency;

/**
 * CurrencyUSD represents the USD currency, and it extends from the Currency abstract class.
 */
public class CurrencyUSD extends Currency {
    /**
     * The empty Constructor
     * It sets the value of the CurrencyUSD to 0
     */
    public CurrencyUSD() {
        super.setAmount(0);
    }

    /**
     * Constructor that takes an amount in and generate a CurrencyUSD that is worth that amount of money
     * @param amount the worthy value of this CurrencyUSD
     */
    public CurrencyUSD(double amount){
        super.setAmount(amount);
    }
}
