package Personnel;

import Account.Account;
import Factory.AccountFactory;
import Input.FileOperator;
import Service.Loan;
import asset.Asset;
import Account.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Customer extends Personnel {
    private HashMap<String, Account> accounts;
    private boolean collateral;
    private ArrayList<Asset> assets;


    public void createAccount(String accountType, int ID ) {

        AccountFactory accountFactory = new AccountFactory();
        if(accountType.equals("Checking")){
            FileOperator fileOperator = new FileOperator();
            HashMap<String, List<String>> checkingAccounts = fileOperator.readFile("Account/CheckingAccounts.txt");
            if(checkingAccounts.get("personnelID").contains(String.valueOf(ID))){

            }
        }else if(accountType.equals("Saving")){
            AccountSaving savingAccount = accountFactory.generateSavingAccount();
            accounts.put("Saving", savingAccount);
        }else if(accountType.equals("Security")){
            AccountSecurity securityAccount = accountFactory.generateSecurityAccount();
            accounts.put("Security", securityAccount);
        }else{
            System.out.println("Invalid accountType");
        }
    }
    public void requestLoan(double amount) {
        double interestRate = 0.0; //This needs to be changed!!!!!!!!
        if(assets.size()!= 0){
            collateral = true;
            try{
                FileWriter fw = new FileWriter("Service/Loans.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                int id = super.getID();
                Date dueDate = new Date(); //This needs to be changed!!!!!!!!
                double duePeriodAmount = 0.0; //This needs to be changed!!!!!!!!
                bw.write("\n"+id+" "+amount+" "+dueDate+" "+ duePeriodAmount+" "+interestRate);
                bw.close();
            }catch (Throwable e){
                e.printStackTrace();
            }
        }else{
            System.out.println("The customer is not collateral and can't request a loan");
        }
    }
    public void viewTransactions() {}
    public void viewBalance() {}
    public void deposit(double amount) {
//        if(!accounts.containsKey("Checking")){
//            System.out.println("The customer doesn't have checking account, can't deposit");
//        }else{
//            AccountChecking checkingAccount =
//        }
    }
    public void withdraw() {}
    public void transferFunds(Account from, Account to) {}

}
