package Views;

import Account.AccountChecking;
import Account.AccountSaving;
import Personnel.Customer;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SpecificCustomerWindow extends JFrame{
    private final Color defaultColor  = new Color(137,187,136);
    private final String checkerMode;
    private final Customer customerName;
    private JFrame previous;

    private JFrame current;
    private JPanel basePanel;
    private JPanel CenterPanel;
    private JPanel EastPanel;
    private JPanel SouthPanel;
    private JPanel WestPanel;
    private JPanel NorthPanel;
    private JButton exitButton;
    private JComboBox currencyBox;

    private ButtonList checkingAccountsList;
    private ButtonList savingAccountsList;
    private ButtonList securityAccounts;

    public SpecificCustomerWindow(JFrame previous, String checkerMode, Customer customer) {

        this.previous= previous;
        this.checkerMode = checkerMode;
        this.customerName = customer;
        current = this;
        previous.setVisible(false);
        createWindow(checkerMode, customer);

        currencyBox.addItem("BalanceUSD");
        currencyBox.addItem("BalanceEURO");
        currencyBox.addItem("BalanceRMB");
    }

    private void createWindow(String checkerMode, Customer customer) {
        CenterPanel.setLayout(new GridLayout(2,1));
        if(customer.getChecking() == null){
            initCheckingWithAdd();
        }else{
           initChecking(customer.getChecking());
        }


        //NorthPanel.setPreferredSize(new Dimension(0,100));
        CenterPanel.add(checkingAccountsList,BorderLayout.CENTER);
        //updateAccountInfoDisplay();

        if(customer.getSaving() == null){
            initSavingWithAdd();
        }else{
            initSaving(customer.getSaving());
        }

        CenterPanel.add(savingAccountsList,BorderLayout.CENTER);



        setContentPane(basePanel);
        setTitle("CheckCustomerWindow");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initChecking(AccountChecking account){

        checkingAccountsList =  new ButtonList(800,200,defaultColor);
        this.repaint();

        ArrayList<String> checkingSections = new ArrayList<>();
        ArrayList<String> checkingButtons= new ArrayList<>();
        checkingSections.add("Account#");
        checkingSections.add("BalanceUSD");
        checkingSections.add("BalanceEURO");
        checkingSections.add("BalanceRMB");
        checkingButtons.add("WithDrawl");
        checkingButtons.add("Deposit");
        checkingButtons.add("Close");
        checkingAccountsList.initLayout(50,10,checkingSections,checkingButtons);
        checkingAccountsList.addOneLine(50,10,1,checkingSections,checkingButtons);
        checkingAccountsList.getInfoSections().get(0).get("Account#").setText(account.getAccID());
        checkingAccountsList.getInfoSections().get(0).get("BalanceUSD").setText(Double.toString( account.getUSDBalance()));
        checkingAccountsList.getInfoSections().get(0).get("BalanceEURO").setText(Double.toString( account.getEUROBalance()));
        checkingAccountsList.getInfoSections().get(0).get("BalanceRMB").setText(Double.toString( account.getRMBBalance()));

        checkingAccountsList.getActions().get(0).get("WithDrawl")
                .addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        int amount = askForValidNumber();
                        Customer.withdrawFromCheckingOrSaving(customerName.getID(),
                                amount,"checking",(String)currencyBox.getSelectedItem());
                        customerName.findAllAccounts(customerName);
                        initChecking(customerName.getChecking());
                        CenterPanel.remove(0);
                        CenterPanel.add(checkingAccountsList,0);
                        CenterPanel.revalidate();
                        CenterPanel.repaint();

                    }
                });
        checkingAccountsList.getActions().get(0).get("Deposit").addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                int amount = askForValidNumber();
                                Customer.deposit (customerName.getID(),
                                        amount,"checking",(String)currencyBox.getSelectedItem());
                                customerName.findAllAccounts(customerName);
                                initChecking(customerName.getChecking());
                                CenterPanel.remove(0);
                                CenterPanel.add(checkingAccountsList,0);
                                CenterPanel.revalidate();
                                CenterPanel.repaint();

                            }
                        });

        checkingAccountsList.getActions().get(0).get("Close").addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        if(!Customer.closeCheckingAccount(customerName.getID())){
                            System.out.println("Not closing with Close Button");
                            return;
                        }
                        System.out.println("Close activated");

                        customerName.findAllAccounts(customerName);
                        initCheckingWithAdd();
                        CenterPanel.remove(0);
                        CenterPanel.add(checkingAccountsList,0);
                        CenterPanel.revalidate();
                        CenterPanel.repaint();

                    }

                });


    }

    private int askForValidNumber(){
        int num = 0;
        try{ num = Integer.parseInt( JOptionPane.showInputDialog("Enter The Amount"));}
        catch (RuntimeException e){
            return askForValidNumber();
        }
       return num;
    }

    private void initCheckingWithAdd(){
        System.out.println("Displaying add on checking");

        checkingAccountsList =  new ButtonList(800,200,defaultColor);
        ArrayList<String> checkingSections = new ArrayList<>();
        ArrayList<String> checkingButtons= new ArrayList<>();
        checkingButtons.add("Add");
        checkingAccountsList.initLayout(50,10,checkingSections,checkingButtons);
        checkingAccountsList.addOneLine(50,10,1,checkingSections,checkingButtons);
        checkingAccountsList.getActions().get(0).get("Add").addMouseListener(

                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        Customer.createAccount("checking",customerName.getID());
                        customerName.findAllAccounts(customerName);
                        initChecking(customerName.getChecking());
                        CenterPanel.remove(0);
                        CenterPanel.add(checkingAccountsList,0);
                        CenterPanel.revalidate();
                        CenterPanel.repaint();
                    }
                }
        );


        current.invalidate();
        current.revalidate();
        current.repaint();
    }

    private void initSaving(AccountSaving account){

        savingAccountsList =  new ButtonList(800,200,defaultColor);

        ArrayList<String> savingSections= new ArrayList<>();
        ArrayList<String> savingButtons= new ArrayList<>();
        savingSections.add("Account#");
        savingSections.add("BalanceUSD");
        savingSections.add("BalanceEURO");
        savingSections.add("BalanceRMB");
        savingButtons.add("WithDrawl");
        savingButtons.add("Deposit");
        savingButtons.add("Transfer");
        savingAccountsList.initLayout(50,10,savingSections,savingButtons);
        savingAccountsList.addOneLine(50,10,1,savingSections,savingButtons);
        savingAccountsList.getInfoSections().get(0).get("Account#").setText(account.getAccID());
        savingAccountsList.getInfoSections().get(0).get("BalanceUSD").setText(Double.toString( account.getUSDBalance()));
        savingAccountsList.getInfoSections().get(0).get("BalanceEURO").setText(Double.toString( account.getEUROBalance()));
        savingAccountsList.getInfoSections().get(0).get("BalanceRMB").setText(Double.toString( account.getRMBBalance()));

        savingAccountsList.getActions().get(0).get("WithDrawl")
                .addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                int amount = askForValidNumber();
                                Customer.withdrawFromCheckingOrSaving(customerName.getID(),
                                        amount,"saving",(String)currencyBox.getSelectedItem());

                                customerName.findAllAccounts(customerName);
                                initSaving(customerName.getSaving());
                                CenterPanel.remove(1);
                                CenterPanel.add(savingAccountsList,1);
                                CenterPanel.revalidate();
                                CenterPanel.repaint();



                            }
                        });
        savingAccountsList.getActions().get(0).get("Deposit").addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        int amount = askForValidNumber();
                        Customer.deposit (customerName.getID(),
                                amount,"saving",(String)currencyBox.getSelectedItem());
                        customerName.findAllAccounts(customerName);
                        initSaving(customerName.getSaving());
                        CenterPanel.remove(1);
                        CenterPanel.add(savingAccountsList,1);
                        CenterPanel.revalidate();
                        CenterPanel.repaint();

                    }
                });
        ;
        savingAccountsList.getActions().get(0).get("Transfer")
                .addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                int amount = askForValidNumber();
                                Customer.transferFunds(customerName.getID(),amount);
                                customerName.findAllAccounts(customerName);
                                initSaving(customerName.getSaving());
                                CenterPanel.remove(1);
                                CenterPanel.add(savingAccountsList,1);
                                CenterPanel.revalidate();
                                CenterPanel.repaint();

                            }
                        });
        ;

        current.invalidate();
        current.revalidate();
        current.repaint();
    }
    private void initSavingWithAdd(){

        savingAccountsList =  new ButtonList(800,200,defaultColor);
        ArrayList<String> savingSections = new ArrayList<>();
        ArrayList<String> savingButtons= new ArrayList<>();
        savingButtons.add("Add");
        savingAccountsList.initLayout(50,10,savingSections,savingButtons);
        savingAccountsList.addOneLine(50,10,1,savingSections,savingButtons);
        savingAccountsList.getActions().get(0).get("Add").addMouseListener(

                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);

                        Customer.createAccount("saving",customerName.getID());
                        System.out.println("Trying to initialize saving");
                        customerName.findAllAccounts(customerName);
                        initSaving(customerName.getSaving());
                        CenterPanel.remove(1);
                        CenterPanel.add(savingAccountsList,1);
                        CenterPanel.revalidate();
                        CenterPanel.repaint();
                    }
                }
        );

        current.invalidate();
        current.revalidate();
        current.repaint();
    }

        public void initializeInfo(){


        //checkingAccounts.getInfoSections().get(0).get("Account#").setText("");


    }

}
