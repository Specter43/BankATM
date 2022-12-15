import Account.AccountChecking;
import Personnel.Customer;
import Views.CheckingWindow;
import Views.FrameATM;
import sun.util.resources.cldr.so.CurrencyNames_so;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//        Customer customer = new Customer(3, "sam");
//        //BalanceUSD/BalanceEURO/BalanceRMB
//        customer.transferFunds(12,1000);
        Customer.createAccount("checking", 7);
//        Customer.withdrawFromCheckingOrSaving(1,60,"checking","BalanceUSD");
//
//        Customer.deposit(1,1000,"saving", "BalanceUSD");
//        Customer.withdrawFromCheckingOrSaving(1,60,"checking","BalanceUSD");
//        Customer.transferFunds(1,1000);
//        Customer.viewTransactions(1);
        Customer.closeCheckingAccount(1);
    }
}