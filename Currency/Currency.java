package Currency;

/**
 * An abstract class representing the currency.
 */
public abstract class Currency {
    private double amount;

    public Currency() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
