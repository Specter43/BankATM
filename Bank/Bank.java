package Bank;

import Input.FileOperator;
import Personnel.Customer;
import Personnel.Manager;
import Service.StockMarket;
import Views.FrameATM;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {
    ArrayList<Customer> getAllCustomers;
    Manager bankManager;
    FrameATM ATM;
    StockMarket stockMarket;

    /**
     * It takes a userName and the pin of the user to login.
     * If the username and the pin match the backend data, it
     * returns true. If it doesn't match, returns false
     * @return true if identity matches, false if not.
     */
    public static boolean login(String userName, String pin){
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> output = fileOperator.readFile("Personnel/Personnels.txt");
        for(int i = 0; i<output.get("name").size(); i++){
            if(output.get("name").get(i).equals(userName)){
                return output.get("PIN").get(i).equals(pin);
            }
        }
        return false;
    }


    /**It prompts the user to enter userName and pin to register
     * a customer.
     * If the username is taken, it will return false.
     * If either of the field is empty, they will both return false.
     * Only if both fields are filled, and the username is not-taken,
     * it will return true.
     * @return true/false
     */
    public static boolean signUp(String userName, String pin){
        if(pin.trim().equals("")){ //if the pin is empty, return false
            return false;
        }
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> output = fileOperator.readFile("Personnel/Personnels.txt");
        if(output.get("name").contains(userName)){
            return false; //If the username already exist, return false.
        }
        try{
            FileWriter fw = new FileWriter("Personnel/Personnels.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            int id = Integer.parseInt(output.get("ID").get(output.get("ID").size()-1))+1;
            bw.write("\n"+id+" "+userName+" "+pin+" "+"true"+" "+"abced");
            bw.close();
            System.out.println("Finished writing");
            return true;
        }catch (Throwable e){
            e.printStackTrace();
            return false;
        }
    }
}
