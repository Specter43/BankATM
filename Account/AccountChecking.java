package Account;

/**
 * A class representing a checking account.
 */
public class AccountChecking extends Account {
    private static double transactionFee = 50;


    public AccountChecking(int id,String pID,double usd, double euro, double rmb) {
        super(id,pID);
        depositUSD(usd);
        depositEURO(euro);
        depositRMB(rmb);
    }






    public void withdrawal(String currencyType , double amount) throws  RuntimeException {
        getBalance().takeMoney(currencyType,amount);
    }
}
