package Currency;

/**
 * CurrencyEURO represents the EURO currency, and it extends from the Currency abstract class.
 */
public class CurrencyEURO extends Currency {

    /**
     * The empty Constructor
     * It sets the value of the CurrencyEURO to 0
     */
    public CurrencyEURO() {
        super.setAmount(0);
    }

    /**
     * Constructor that takes an amount in and generate a CurrencyEURO that is worth that amount of money
     * @param amount the worthy value of this CurrencyEURO
     */
    public CurrencyEURO(double amount){
        super.setAmount(amount);
    }
}
