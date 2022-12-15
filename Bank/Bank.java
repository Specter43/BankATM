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
     *
     * @return true if identity matches, false if not.
     */
    public static ArrayList<Object> login(String userName, String pin){
        System.out.println(pin);
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> output = fileOperator.readFile("Personnel/Personnels.txt");
        for(int i = 0; i<output.get("name").size(); i++){
            if(output.get("name").get(i).equals(userName)){
                Customer customer = new Customer(Integer.parseInt(output.get("ID").get(i)), output.get("name").get(i));
                int finalI = i;
                return new ArrayList<Object>(){{add(output.get("PIN").get(finalI).equals(pin)); add(customer);}};
            }
        }
        return new ArrayList<Object>(){{add(false); add(null);}};
    }


    /**It prompts the user to enter userName and pin to register
     * a customer.
     * If the username is taken, it will return false.
     * If either of the field is empty, they will both return false.
     * Only if both fields are filled, and the username is not-taken,
     * it will return true.
     * @return true/false
     */
    public static ArrayList<Object> signUp(String userName, String pin){
        if(pin.trim().equals("")){ //if the pin is empty, return false
            return new ArrayList<Object>(){{add(false); add(null);}};
        }
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> output = fileOperator.readFile("Personnel/Personnels.txt");
        if(output.get("name").contains(userName)){
            return new ArrayList<Object>(){{add(false); add(null);}}; //If the username already exist, return false.
        }
        try{
            FileWriter fw = new FileWriter("Personnel/Personnels.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            int id = Integer.parseInt(output.get("ID").get(output.get("ID").size()-1))+1;
            bw.write("\n"+id+" "+userName+" "+pin+" "+"true"+" "+"_");
            bw.close();
            Customer customer = new Customer(id, userName);
            return new ArrayList<Object>(){{add(true); add(customer);}};
        }catch (Throwable e){
            e.printStackTrace();
            return new ArrayList<Object>(){{add(false); add(null);}};
        }
    }
}
