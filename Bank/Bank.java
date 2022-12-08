package Bank;

import Personnel.Customer;
import Personnel.Manager;
import Service.StockMarket;
import Views.FrameATM;

import java.util.ArrayList;

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
    public boolean login(String userName, String pin){
        return true;
    }


    /**It prompts the user to enter userName and pin to register
     * a customer.
     * If the username is taken, it will return false.
     * If either of the field is empty, they will both return false.
     * Only if both fields are filled, and the username is not-taken,
     * it will return true.
     * @return true/false
     */
    public boolean singUp(){
        String userName;
        //check if the userName is in the txt file. If it is in, then return false. If not, keep going.
        String pin;
        return true;
    }
}
