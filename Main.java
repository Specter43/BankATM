import Account.AccountChecking;
import Account.AccountSaving;
import Account.AccountSecurity;
import Personnel.Customer;
import Views.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        FrameATM frameATM = new FrameATM();
        //CheckingWindow test = new CheckingWindow(new Customer(),new AccountChecking("wasd",0,0,0));
        //SavingWindow test = new SavingWindow(frameATM,  new Customer(),new AccountSaving("wasd"));
        //SecurityWindow test = new SecurityWindow( new Customer(),new AccountSecurity("wasd"));
        CheckCustomerWindow test = new CheckCustomerWindow(frameATM,"abc","ahaha");

        //test.setVisible(true);



    }
}