package Personnel;

import Account.Account;
import Factory.AccountFactory;
import asset.Asset;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Personnel {
    private HashMap<String, Account> accounts;
    private boolean collateral;
    private ArrayList<Asset> assets;


    public void createAccount(String accountType) {
        AccountFactory accountFactory = new AccountFactory();

    }
    public void requestLoan() {}
    public void viewTransactions() {}
    public void viewBalance() {}
    public void deposit() {}
    public void withdraw() {}
    public void transferFunds(Account from, Account to) {}

}
