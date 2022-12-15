package Personnel;

import Account.Account;
import Factory.AccountFactory;
import Input.FileOperator;
import Service.Loan;
import asset.Asset;
import Account.*;

import java.io.*;
import java.util.*;

public class Customer extends Personnel {
    private HashMap<String, Account> accounts;
    private boolean collateral;
    private ArrayList<Asset> assets;

    public Customer() {
    }

    public Customer(int ID, String name) {
        super(ID, name);
        collateral = true;
    }

    public static boolean createAccount(String accountType, int ID) {
        if(accountType.equals("checking")){
            return putNewAccountIntoCheckingOrSavingTxt("Checking", ID);
        }else if(accountType.equals("saving")){
            return putNewAccountIntoCheckingOrSavingTxt("Saving", ID);
        }else if(accountType.equals("security")){
            if(!checkAccountExist("Account/SecurityAccounts.txt", ID)){
            return putNewAccountIntoSecurityTxt("Account/SecurityAccounts.txt", ID);
            }
        }else{
            System.out.println("Invalid accountType");
        }
        return false;
    }


    public boolean requestLoan(double amount) {
        double interestRate = 0.0;
        if(amount < 500.0){
            interestRate = 0.05;
        }else if(amount >= 500 && amount < 2000){
            interestRate = 0.07;
        }else{
            interestRate = 0.09;
        }
        if(isCollateral()){
            try{
                FileWriter fw = new FileWriter("Service/Loans.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                int id = super.getID();
                bw.write("\n"+id+" "+amount+" "+interestRate);
                bw.close();
                setCustomerCollateralFalse();
                return true;
            }catch (Throwable e){
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("The customer is not collateral and can't request a loan");
            return false;
        }
    }

    /**
     * Read the file, and get all the transactions of this customer
     */
    public static void viewTransactions(int ID) {
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> outputs = fileOperator.readFile("Bank/transactions.txt");
        String name = getNameByID(ID);
        for(int i = 0; i < outputs.get("name").size(); i++){
            if(outputs.get("name").get(i).equals(name)){
                System.out.println(outputs.get("date").get(i)+" "+outputs.get("name").get(i)+" "+outputs.get("id").get(i)+" "+outputs.get("transaction_type").get(i)+" "+outputs.get("amount").get(i));
            }
        }
    }

    public static boolean closeCheckingAccount(int ID){
        double amountUSD = 0;
        double amountEURO = 0;
        double amountRMB = 0;
        if(hasAccount(ID,"Account/CheckingAccounts.txt")){
            //if checking account has less than 50, can't close, because we need to charge closing account 50$.
            FileOperator fileOperator = new FileOperator();
            HashMap<String, List<String>> outputs = fileOperator.readFile("Account/CheckingAccounts.txt");
            for(int i =0; i<outputs.get("personnelID").size(); i++){
                if(outputs.get("personnelID").get(i).equals(Integer.toString(ID))){
                    if(Double.parseDouble(outputs.get("BalanceUSD").get(i)) < 50){
                        System.out.println("Your checking account's USD balance is less than 50 and can't close your account, because we need to charge you 50$ to close your account");
                        return false;
                    }else{
                        amountUSD = Double.parseDouble(outputs.get("BalanceUSD").get(i));
                        amountEURO = Double.parseDouble(outputs.get("BalanceEURO").get(i));
                        amountRMB = Double.parseDouble(outputs.get("BalanceRMB").get(i));
                    }
                }
            }
            deleteTheAccountFromFile(ID,"Account/CheckingAccounts.txt");
            addOneTransaction(ID,"withdrawUSD-for-closing",amountUSD-50);
            addOneTransaction(ID,"withdrawEURO-for-closing",amountEURO);
            addOneTransaction(ID,"withdrawRMB-for-closing",amountRMB);
            addOneTransaction(ID,"delete-checking-account",0);
            return true;
        }else{
            System.out.println("This customer doesn't have checking account");
            return false;
        }
    }

    public static void addOneTransaction(int ID, String transactionType, double amount){
        try{
            FileWriter fw = new FileWriter("Bank/transactions.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            // date/name/id/transaction_type/amount
            String name = getNameByID(ID);
            String date = "this-day";
            bw.write("\n"+date+" "+name+" "+ID+" "+transactionType+" "+amount);
            bw.close();
            System.out.println("Finished writing");
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    private static String getNameByID(int ID){
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> outputs = fileOperator.readFile("Personnel/Personnels.txt");
        for(int i = 0; i<outputs.get("ID").size(); i++){
            if(outputs.get("ID").get(i).equals(Integer.toString(ID))){
                return outputs.get("name").get(i);
            }
        }
        return "";
    }

    public HashMap<String, Double> viewBalance(String accountType) {
        String fileName;
        HashMap<String, Double> results = new HashMap<>();
        if(accountType.equals("checking")){
            fileName = "Account/CheckingAccounts.txt";
            results = getCheckingOrSavingBalance(super.getID(), fileName);
        }else if(accountType.equals("saving")){
            fileName = "Account/SavingsAccounts.txt";
            results = getCheckingOrSavingBalance(super.getID(), fileName);
        }else if(accountType.equals("security")){
            fileName = "Account/SecurityAccounts.txt";
            results = getSecurityBalance(super.getID(), fileName);
        }else{
            fileName = "";
            System.out.println("No such account type");
        }
        for(String key: results.keySet()){
            System.out.println(key+" "+results.get(key));
        }
        return results;
    }


    /**
     *
     * @param personnelID
     * @param amount
     * @param accountType: checking or saving
     * @param currencyType: BalanceUSD / BalanceEURO / BalanceRMB
     * @return
     */
    public static boolean deposit(int personnelID, double amount, String accountType, String currencyType) {
        if(!accountType.equals("checking") && !accountType.equals("saving")){
            System.out.println("No such account type in deposit");
            return false;
        }
        if(!currencyType.equals("BalanceUSD") && !currencyType.equals("BalanceEURO") && !currencyType.equals("BalanceRMB")){
            System.out.println("No such currency type in deposit");
            return false;
        }
        if(amount <= 50.0){
            System.out.println("For checking account deposit, you need to deposit more than 50 USD because 50 USD will be charged.");
            return false;
        }
        String fileName = "";
        FileOperator fileOperator = new FileOperator();
        if(accountType.equals("checking")){
            fileName = "Account/CheckingAccounts.txt";
            HashMap<String, List<String>> outputs = fileOperator.readFile(fileName);
            for(int i = 0; i<outputs.get("personnelID").size(); i++){
                if(outputs.get("personnelID").get(i).equals(Integer.toString(personnelID))){ //found this customer's checking account
                    System.out.println("found this customer's checking account");
                    System.out.println("Actual deposit money is: "+(amount-50));
                    updateCurrency(currencyType,amount-50,fileName,personnelID);
                    addOneTransaction(personnelID,currencyType+"-"+"deposit"+"-"+accountType,amount-50);
                    return true;
                }
            }
            System.out.println("This customer doesn't have checking account");
            return false; //can't find this customer's checking account
        }else if(accountType.equals("saving")){
            fileName = "Account/SavingsAccounts.txt";
            HashMap<String, List<String>> outputs = fileOperator.readFile(fileName);
            for(int i = 0; i<outputs.get("personnelID").size(); i++){
                if(outputs.get("personnelID").get(i).equals(Integer.toString(personnelID))){ //found this customer's checking account
                    System.out.println("found this customer's saving account");
                    updateCurrency(currencyType,amount,fileName,personnelID);
                    addOneTransaction(personnelID,currencyType+"-"+"deposit"+"-"+accountType,amount);
                    return true;
                }
            }
            System.out.println("This customer doesn't have saving account");
            return false; //can't find this customer's checking account
        }else{
            System.out.println("No such an account in Customer.deposit");
            return false;
        }
    }

    /**
     *
     * @param personnelID
     * @param amount
     * @param accountType
     * @param currencyType BalanceUSD/BalanceEURO/BalanceRMB
     * @return
     */
    public static boolean withdrawFromCheckingOrSaving(int personnelID, double amount, String accountType, String currencyType) {
        if(!accountType.equals("checking") && !accountType.equals("saving")){
            System.out.println("No such account type in deposit");
            return false;
        }
        if(!currencyType.equals("BalanceUSD") && !currencyType.equals("BalanceEURO") && !currencyType.equals("BalanceRMB")){
            System.out.println("No such currency type in deposit");
            return false;
        }
        if(amount <= 50.0){
            System.out.println("The withdraw amount is less or equal to 50. You need to withdraw more than this, and what you get is your withdraw amount-50.0 USD");
            return false;
        }
        String fileName = "";
        FileOperator fileOperator = new FileOperator();
        if(accountType.equals("checking")){
            fileName = "Account/CheckingAccounts.txt";
            HashMap<String, List<String>> outputs = fileOperator.readFile(fileName);
            for(int i = 0; i<outputs.get("personnelID").size(); i++){
                if(outputs.get("personnelID").get(i).equals(Integer.toString(personnelID))){ //found this customer's checking account
                    if(Double.parseDouble(outputs.get(currencyType).get(i)) < amount){
                        System.out.println("Not enough money to withdraw");
                        return false;
                    }else{
                        updateWithdraw(currencyType,amount,fileName,personnelID);
                        addOneTransaction(personnelID,currencyType+"-"+"withdraw"+"-"+accountType,amount-50);
                        return true;
                    }
                }
            }
            System.out.println("This customer doesn't have checking account");
            return false; //can't find this customer's checking account
        }else if(accountType.equals("saving")){
            fileName = "Account/SavingsAccounts.txt";
            HashMap<String, List<String>> outputs = fileOperator.readFile(fileName);
            for(int i = 0; i<outputs.get("personnelID").size(); i++){
                if(outputs.get("personnelID").get(i).equals(Integer.toString(personnelID))){ //found this customer's checking account
                    if(Double.parseDouble(outputs.get(currencyType).get(i)) < amount){
                        System.out.println("not enough money to withdraw");
                        return false;
                    }else{
                        updateWithdraw(currencyType,amount,fileName,personnelID);
                        addOneTransaction(personnelID,currencyType+"-"+"withdraw"+"-"+accountType,amount-50);
                        return true;
                    }
                }
            }
            System.out.println("This customer doesn't have saving account");
            return false; //can't find this customer's checking account
        }else{
            System.out.println("No such account type");
            return false;
        }
    }


    public static boolean transferFunds(int personnelID, double amount) {
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> outputs = fileOperator.readFile("Account/SavingsAccounts.txt");
        for(int i = 0; i<outputs.get("personnelID").size();i++){
            if(outputs.get("personnelID").get(i).equals(Integer.toString(personnelID))){
                if(Double.parseDouble(outputs.get("BalanceUSD").get(i))<5000){
                    System.out.println("The saving account's USD balance is less than 5000 and can't transfer");
                    return false;
                }
                if(Double.parseDouble(outputs.get("BalanceUSD").get(i)) < amount){
                    System.out.println("The saving account doesn't have that much money to transfer");
                    return false;
                }
            }
        }
        if(hasAccount(personnelID,"Account/SecurityAccounts.txt")){
            withdrawFromCheckingOrSaving(personnelID,amount,"saving","BalanceUSD");
            updateSecurityAccount("BalanceUSD",amount,"Account/SecurityAccounts.txt",personnelID);
            addOneTransaction(personnelID,"transferFunds",amount);
            return true;
        }else{
            System.out.println("This customer doesn't have security account");
            return false;
        }

    }


    public static boolean hasAccount(int personnelID, String fileName){
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> outputs = fileOperator.readFile(fileName);
        for(int i = 0; i<outputs.get("personnelID").size();i++){
            if(outputs.get("personnelID").get(i).equals(Integer.toString(personnelID))){
                return true;
            }
        }
        return false;
    }

    private static void deleteTheAccountFromFile(int ID, String fileName){
        try {
            StringBuffer inputBuffer = new StringBuffer();
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            String line;
            if(input.hasNextLine()){ //add first line
                line = input.nextLine();
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            while(true){
                if(input.hasNextLine()){
                    line = input.nextLine(); //if now last line
                    String[] splitLine = line.split("\\s+");
                    String newLine;
                    if(!(splitLine[0].equals(Integer.toString(ID)))) { //The account is not our account, we add it back to the file. If it is the account we want to delete, we ignore it.
                        newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + splitLine[3] + " " + splitLine[4];
                        if (input.hasNextLine()) {
                            inputBuffer.append(newLine);
                            inputBuffer.append('\n');
                        } else {
                            inputBuffer.append(newLine);
                            break;
                        }
                    }else{ //if now the last line is the account we look for:
                        break;
                    }
                }else{
                    break;
                }
            }
            input.close();
            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputBuffer.toString().trim().getBytes());
            fileOut.close();
        }catch (Exception e){
            System.out.println("Problem reading file");
        }
    }

    private static void updateCurrency(String currencyType, double amount, String fileName, int personnelID) {
        try {
            StringBuffer inputBuffer = new StringBuffer();
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            String line;
            if(input.hasNextLine()){
                line = input.nextLine();
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            while(true){
                if(input.hasNextLine()){
                    line = input.nextLine();
                    String[] splitLine = line.split("\\s+");
                    String newLine;
                    if(splitLine[0].equals(Integer.toString(personnelID))) { //found this customer and the account
                        if(currencyType.equals("BalanceUSD")){
                            newLine = splitLine[0] + " " + splitLine[1] + " " + (Double.parseDouble(splitLine[2])+amount) + " " + splitLine[3] + " " + splitLine[4];
                        }else if(currencyType.equals("BalanceEURO")){
                            newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + (Double.parseDouble(splitLine[3])+amount) + " " + splitLine[4];
                        }else{
                            newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + splitLine[3] + " " + (Double.parseDouble(splitLine[4])+amount);
                        }
                    }else{
                        newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + splitLine[3] + " " + splitLine[4];
                    }
                    if(input.hasNextLine()){//not the last line
                        inputBuffer.append(newLine);
                        inputBuffer.append('\n');
                    }else{ //is the last line
                        inputBuffer.append(newLine);
                        break;
                    }
                }else{
                    break;
                }
            }
            input.close();
            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }catch (Exception e){
            System.out.println("Problem reading file");
        }
    }

    private static void updateSecurityAccount(String currencyType, double amount, String fileName, int personnelID){
        try {
            StringBuffer inputBuffer = new StringBuffer();
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            String line;
            if(input.hasNextLine()){
                line = input.nextLine();
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            while(true){
                if(input.hasNextLine()){
                    line = input.nextLine();
                    String[] splitLine = line.split("\\s+");
                    String newLine;
                    if(splitLine[0].equals(Integer.toString(personnelID))) { //found this customer and the account
                        newLine = splitLine[0] + " " + splitLine[1] + " " + (Double.parseDouble(splitLine[2])+amount) + " " + splitLine[3];
                    }else{
                        newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + splitLine[3];
                    }
                    if(input.hasNextLine()){//not the last line
                        inputBuffer.append(newLine);
                        inputBuffer.append('\n');
                    }else{ //is the last line
                        inputBuffer.append(newLine);
                        break;
                    }
                }else{
                    break;
                }
            }
            input.close();
            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }catch (Exception e){
            System.out.println("Problem reading file");
        }
    }

    /**
     *
     * @param currencyType BalanceUSD/BalanceEURO/BalanceRMB
     * @param amount
     * @param fileName
     * @param personnelID
     */
    private static void updateWithdraw(String currencyType, double amount, String fileName, int personnelID) {
        try {
            StringBuffer inputBuffer = new StringBuffer();
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            String line;
            if(input.hasNextLine()){
                line = input.nextLine();
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            while(true){
                if(input.hasNextLine()){
                    line = input.nextLine();
                    String[] splitLine = line.split("\\s+");
                    String newLine;
                    if(splitLine[0].equals(Integer.toString(personnelID))) { //found this customer and the account
                        if(currencyType.equals("BalanceUSD")){
                            newLine = splitLine[0] + " " + splitLine[1] + " " + (Double.parseDouble(splitLine[2])-amount) + " " + splitLine[3] + " " + splitLine[4];
                        }else if(currencyType.equals("BalanceEURO")){
                            newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + (Double.parseDouble(splitLine[3])-amount) + " " + splitLine[4];
                        }else{
                            newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + splitLine[3] + " " + (Double.parseDouble(splitLine[4])-amount);
                        }
                    }else{
                        newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + splitLine[3] + " " + splitLine[4];
                    }
                    if(input.hasNextLine()){//not the last line
                        inputBuffer.append(newLine);
                        inputBuffer.append('\n');
                    }else{ //is the last line
                        inputBuffer.append(newLine);
                        break;
                    }
                }else{
                    break;
                }
            }
            input.close();
            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }catch (Exception e){
            System.out.println("Problem reading file");
        }
    }



    private static boolean putNewAccountIntoSecurityTxt(String fileName, int ID){
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> accounts = fileOperator.readFile(fileName);
        try{
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            int accID = 0;
            if(accounts.get("accID").size() != 0){
                accID = Integer.parseInt(accounts.get("accID").get(accounts.get("accID").size()-1))+1;
            }
            bw.write("\n"+ID+" "+accID+" "+(-50.0)+" "+"[]");
            bw.close();
            System.out.println("Successfully added new account");
            return true;
        }catch (Throwable e){
            e.printStackTrace();
            return false;
        }
    }


    public void setCustomerCollateralFalse() {
        try {
            StringBuffer inputBuffer = new StringBuffer();
            File file = new File("Personnel/Personnels.txt");
            Scanner input = new Scanner(file);
            String line;
            if(input.hasNextLine()){
                line = input.nextLine();
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            while(true){
                if(input.hasNextLine()){
                    line = input.nextLine();
                    String[] splitLine = line.split("\\s+");
                    String newLine;
                    if(splitLine[1].equals(super.getName())) {
                        newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + "false" + " " + splitLine[4];
                    }else{
                        newLine = splitLine[0] + " " + splitLine[1] + " " + splitLine[2] + " " + splitLine[3] + " " + splitLine[4];
                    }
                    if(input.hasNextLine()){//not the last line
                        inputBuffer.append(newLine);
                        inputBuffer.append('\n');
                    }else{ //is the last line
                        inputBuffer.append(newLine);
                        break;
                    }
                }else{
                    break;
                }
            }
            input.close();
            FileOutputStream fileOut = new FileOutputStream("Personnel/Personnels.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }catch (Exception e){
            System.out.println("Problem reading file");
        }
    }


    private static boolean putNewAccountIntoCheckingOrSavingTxt(String accountType, int ID){
        String fileName = "";
        if(accountType.equals("Checking")){
            fileName = "Account/CheckingAccounts.txt";
        }else if(accountType.equals("Saving")){
            fileName = "Account/SavingsAccounts.txt";
        }else{
            System.out.println("No such account type");
            return false;
        }
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> accounts = fileOperator.readFile(fileName);
        if(accounts.get("personnelID").contains(String.valueOf(ID))){
            System.out.println("The customer already has this type of account");
            return false;
        }else{
            try{
                FileWriter fw = new FileWriter(fileName, true);
                BufferedWriter bw = new BufferedWriter(fw);
                int accID = 0;
                if(accounts.get("accID").size() != 0){
                    accID = Integer.parseInt(accounts.get("accID").get(accounts.get("accID").size()-1))+1;
                }
                bw.write("\n"+ID+" "+accID+" "+(-50.0)+" "+0+" "+0);
                bw.close();
                System.out.println("Successfully added new account");
                return true;
            }catch (Throwable e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public static boolean checkAccountExist(String fileName, int ID){
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> accounts = fileOperator.readFile(fileName);
        if(accounts.get("personnelID").contains(String.valueOf(ID))){
            System.out.println("The customer already has this type of account");
            return true;
        }else{
            return false;
        }

    }

    private boolean isCollateral(){
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> accounts = fileOperator.readFile("Personnel/Personnels.txt");
        for(int i = 0; i<accounts.get("name").size(); i++){
            if(accounts.get("name").get(i).equals(super.getName())){ //if we found this customer
//                System.out.println("Found the person: "+accounts.get("name").get(i));
//                System.out.println("Collateral: "+accounts.get("collateral").get(i));
                if(accounts.get("collateral").get(i).equals("true")){ //and this customer is collateral
                    return true;
                }
            }
        }
        return false;
    }


    private HashMap<String, Double>  getSecurityBalance(int customer_ID, String fileName) {
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> outputs = fileOperator.readFile(fileName);
        HashMap<String, Double> results = new HashMap<>();
        for(int i = 0; i<outputs.get("personnelID").size(); i++){
            if(outputs.get("personnelID").get(i).equals(Integer.toString(customer_ID))){
                results.put("BalanceUSD",Double.parseDouble(outputs.get("BalanceUSD").get(i)));
            }
        }
        return results;
    }

    private HashMap<String, Double> getCheckingOrSavingBalance(int customer_ID, String fileName) {
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> outputs = fileOperator.readFile(fileName);
        HashMap<String, Double> results = new HashMap<>();
        for(int i = 0; i<outputs.get("personnelID").size(); i++){
            if(outputs.get("personnelID").get(i).equals(Integer.toString(customer_ID))){
                results.put("USD Balance",Double.parseDouble(outputs.get("BalanceUSD").get(i)));
                results.put("EURO Balance",Double.parseDouble(outputs.get("BalanceEURO").get(i)));
                results.put("RMB Balance",Double.parseDouble(outputs.get("BalanceRMB").get(i)));
            }
        }
        return results;
    }
}

