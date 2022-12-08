package Account;

public class AccountChecking extends Account {
    private static double transactionFee = 50;

    public void withdrawal(String currencyType , double amount) throws  RuntimeException {
        getBalance().takeMoney(currencyType,amount);
    }
}
