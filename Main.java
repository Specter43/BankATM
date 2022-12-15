import Bank.Bank;
import Personnel.Customer;
import Views.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        FrameATM frameATM = new FrameATM();
        //CheckingWindow test = new CheckingWindow(new Customer(),new AccountChecking("wasd",0,0,0));
        //SavingWindow test = new SavingWindow(frameATM,  new Customer(),new AccountSaving("wasd"));
        //SecurityWindow test = new SecurityWindow( new Customer(),new AccountSecurity("wasd"));
        //CheckSpecificCustomerWindow test = new CheckSpecificCustomerWindow(frameATM,"abc","ahaha");


        //test.setVisible(true);
//        Customer.createAccount("checking",2);
//        Bank.signUp("samantha","abc");
//        Customer.requestLoan(5000,3);
        Customer.closeCheckingAccount(1);


    }
}