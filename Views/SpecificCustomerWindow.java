package Views;

import Account.AccountChecking;
import Account.AccountSaving;
import Account.AccountSecurity;
import Personnel.Customer;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A class representing the main page for a customer.
 */
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
    private JButton stockMarketButton;
    private JButton loanButton;

    private ButtonList checkingAccountsList;
    private ButtonList savingAccountsList;
    private ButtonList securityAccountsList;

    public SpecificCustomerWindow(JFrame previous, String checkerMode, Customer customer) {

        this.previous= previous;
        this.checkerMode = checkerMode;
        this.customerName = customer;
        current = this;
        previous.setVisible(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                current.setVisible(false);
                previous.setVisible(true);
            }
        });


        createWindow(checkerMode, customer);

        currencyBox.addItem("BalanceUSD");
        currencyBox.addItem("BalanceEURO");
        currencyBox.addItem("BalanceRMB");
        stockMarketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (customer.getSaving().getUSDBalance() >= 2500 && customer.getSecurity() != null) {
                    StockWindow stockWindow = new StockWindow(current, "Customer", Integer.toString(customer.getID()));
                    setVisible(false);
                }
                else if (customer.getSecurity() == null) {
                    JOptionPane.showMessageDialog(null, "You currently don't have a security account.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "You don't have enough money in savings to start trading.");
                }
            }
        });
        loanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoanWindow loanWindow = new LoanWindow(current, Integer.toString(customer.getID()));
            }
        });
    }

    private void createWindow(String checkerMode, Customer customer) {
        CenterPanel.setLayout(new GridLayout(3,1));
        if(customer.getChecking() == null){
            initCheckingWithAdd();
        }else{
           initChecking(customer.getChecking());
        }
        CenterPanel.add(checkingAccountsList,BorderLayout.CENTER);

        //updateAccountInfoDisplay();

        if(customer.getSaving() == null){
            initSavingWithAdd();
        }else{
            initSaving(customer.getSaving());
        }

        CenterPanel.add(savingAccountsList,BorderLayout.CENTER);

        if(customer.getSecurity() == null){
            initSecurityWithAdd();
        }else{
            initSecurity(customer.getSecurity());
        }

        CenterPanel.add(securityAccountsList,BorderLayout.CENTER);



        setContentPane(basePanel);
        setTitle("CheckCustomerWindow");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initSecurityWithAdd() {

        System.out.println("Displaying add on security");

        securityAccountsList=  new ButtonList(800,200,defaultColor);
        ArrayList<String> checkingSections = new ArrayList<>();
        ArrayList<String> checkingButtons= new ArrayList<>();
        checkingButtons.add("Add");
        securityAccountsList.initLayout(50,10,checkingSections,checkingButtons);
        securityAccountsList.addOneLine(50,10,1,checkingSections,checkingButtons);
        securityAccountsList.getActions().get(0).get("Add").addMouseListener(

                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        Customer.createAccount("security",customerName.getID());
                        customerName.findAllAccounts(customerName);
                        initSecurity(customerName.getSecurity());
                        CenterPanel.remove(2);
                        CenterPanel.add(securityAccountsList,2);
                        CenterPanel.revalidate();
                        CenterPanel.repaint();

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

    private void initSecurity(AccountSecurity security) {
        securityAccountsList=  new ButtonList(800,200,defaultColor);
        this.repaint();

        ArrayList<String> securitySections = new ArrayList<>();
        ArrayList<String> securityButtons= new ArrayList<>();
        securitySections.add("Account#");
        securitySections.add("BalanceUSD");
        securityAccountsList.initLayout(50,10,securitySections,securityButtons);
        securityAccountsList.addOneLine(50,10,1,securitySections,securityButtons);
        securityAccountsList.getInfoSections().get(0).get("Account#").setText(security.getAccID());
        securityAccountsList.getInfoSections().get(0).get("BalanceUSD").setText(Double.toString( security.getUSDBalance()));
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
        if( customerName.getSecurity() != null ){
        savingButtons.add("Transfer");
        }

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
        if( customerName.getSecurity() != null ){
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

                                    initSecurity(customerName.getSecurity());
                                    CenterPanel.remove(2);
                                    CenterPanel.add(securityAccountsList,2);
                                    CenterPanel.revalidate();
                                    CenterPanel.repaint();

                                }
                            });
            ;
        }


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

        public void updateSecurityDisplay(){
            customerName.findAllAccounts(customerName);
            initSecurity(customerName.getSecurity());
            CenterPanel.remove(2);
            CenterPanel.add(securityAccountsList,2);
            CenterPanel.revalidate();
            CenterPanel.repaint();
            current.invalidate();
            current.revalidate();
            current.repaint();
            //checkingAccounts.getInfoSections().get(0).get("Account#").setText("");


        }

}
