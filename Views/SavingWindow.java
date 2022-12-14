package Views;

import Account.*;
import Personnel.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SavingWindow extends JFrame{

    static Color defaultColor = new Color(137,187,136);
    Personnel operator;
    AccountSaving detail;

    ButtonList loanList;

    ButtonList accountInfo;
    private JPanel basePanel;
    private JPanel NorthPanel;
    private JPanel EastPanel;
    private JPanel WestPanel;
    private JPanel SouthPanel;
    private JTextField textField1;
    private JPanel CenterPanel;
    private JButton exitButton;

    private JFrame previous;


    public SavingWindow(JFrame previous, Personnel operator, AccountSaving detail){
        previous.setVisible(false);
        this.previous = previous;
        this.operator = operator;
        this.detail = detail;
        createUIComponents();
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                previous.setVisible(true);
                setVisible(false);
            }
        });
    }



    private void createUIComponents() {
        loanList =  new ButtonList(800,200,defaultColor);
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        CenterPanel.setLayout(new GridLayout(2,1));
        sections.add("Loan");
        sections.add("Amount Owed");
        loanList.initLayout(50,10,sections,buttons);
        loanList.addOneLine(50,10,1,sections,buttons);

        //NorthPanel.setPreferredSize(new Dimension(0,100));
        CenterPanel.add(loanList);

        accountInfo=  new ButtonList(800,200,defaultColor);
        ArrayList<String> accountSections = new ArrayList<>();
        ArrayList<String> accountButtons= new ArrayList<>();
        accountSections.add("Account");
        accountSections.add("BalanceUSD");
        //buttons.add("Pay");
        accountInfo.initLayout(50,10,accountSections,accountButtons);
        accountInfo.addOneLine(50,10,1,accountSections,accountButtons);
        CenterPanel.add(accountInfo);


        //content.getInfoSections().get
        //content.setBackground(defaultColor);
        setContentPane(basePanel);
        //content.add(new Button("hello"));
        setTitle("Saving Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void updateAccountInfoDisplay(){
        accountInfo.getInfoSections().get(0).get("Account#").setText(detail.getAccID());
        accountInfo.getInfoSections().get(0).get("BalanceUSD").setText(Double.toString( detail.getUSDBalance()));
        //content.repa

    }
}
