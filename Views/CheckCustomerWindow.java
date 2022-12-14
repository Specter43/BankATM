package Views;

import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CheckCustomerWindow extends JFrame{
    private final Color defaultColor  = new Color(137,187,136);
    private final String checkerMode;
    private final String customerName;
    private JFrame previous;
    private JPanel basePanel;
    private JPanel CenterPanel;
    private JPanel EastPanel;
    private JPanel SouthPanel;
    private JPanel WestPanel;
    private JPanel NorthPanel;
    private JButton exitButton;

    private ButtonList checkingAccounts;
    private ButtonList savingAccounts;
    private ButtonList securityAccounts;

    public CheckCustomerWindow(JFrame previous,String checkerMode, String customerName) {

        this.previous= previous;
        this.checkerMode = checkerMode;
        this.customerName = customerName;
        previous.setVisible(false);
        createWindow(checkerMode, customerName);
    }

    private void createWindow(String checkerMode, String customerName) {
        CenterPanel.setLayout(new GridLayout(3,1));
        checkingAccounts =  new ButtonList(800,200,defaultColor);

        ArrayList<String> checkingSections = new ArrayList<>();
        ArrayList<String> checkingButtons= new ArrayList<>();
        checkingSections.add("Account#");
        checkingSections.add("BalanceUSD");
        checkingSections.add("BalanceEURO");
        checkingSections.add("BalanceRMB");
        checkingButtons.add("WithDrawl");
        checkingButtons.add("Deposit");
        checkingButtons.add("Transfer");
        checkingButtons.add("Close");
        checkingAccounts.initLayout(50,10,checkingSections,checkingButtons);
        checkingAccounts.addOneLine(50,10,1,checkingSections,checkingButtons);

        //NorthPanel.setPreferredSize(new Dimension(0,100));
        CenterPanel.add(checkingAccounts,BorderLayout.CENTER);
        //updateAccountInfoDisplay();

        savingAccounts=  new ButtonList(800,200,defaultColor);

        ArrayList<String> savingSections= new ArrayList<>();
        ArrayList<String> savingButtons= new ArrayList<>();
        savingSections.add("Account#");
        savingSections.add("BalanceUSD");
        savingSections.add("BalanceEURO");
        savingSections.add("BalanceRMB");
        savingButtons.add("WithDrawl");
        savingButtons.add("Deposit");
        savingButtons.add("Transfer");
        savingAccounts.initLayout(50,10,savingSections,savingButtons);
        savingAccounts.addOneLine(50,10,1,savingSections,savingButtons);

        CenterPanel.add(savingAccounts,BorderLayout.CENTER);


        securityAccounts =  new ButtonList(800,200,defaultColor);

        ArrayList<String> securitySections= new ArrayList<>();
        ArrayList<String> securityButtons= new ArrayList<>();
        securitySections.add("Account#");
        securitySections.add("BalanceUSD");
        securitySections.add("Stock");
        securitySections.add("Opening");
        securityAccounts.initLayout(50,10,securitySections,securityButtons);
        securityAccounts.addOneLine(50,10,1,securitySections,securityButtons);

        CenterPanel.add(securityAccounts,BorderLayout.CENTER);

        setContentPane(basePanel);
        setTitle("CheckCustomerWindow");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
