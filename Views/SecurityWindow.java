package Views;

import Account.AccountSecurity;
import Personnel.Personnel;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A class representing the security account window.
 */
public class SecurityWindow extends  JFrame{

    static Color defaultColor = new Color(137,187,136);
    Personnel operator;
    AccountSecurity detail;

    ButtonList content;

    ButtonList accountInfo;
    private JPanel basePanel;
    private JPanel WestPanel;
    private JPanel NorthPanel;
    private JPanel EastPanel;
    private JPanel SouthPanel;
    private JButton exitButton;
    private JPanel CenterPanel;

    public SecurityWindow(Personnel operator, AccountSecurity detail){
        this.operator = operator;
        this.detail = detail;
        createUIComponents();
    }

    private void createUIComponents() {
        CenterPanel.setLayout(new GridLayout(2,1));
        content =  new ButtonList(800,200,defaultColor);
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("Stocks");
        sections.add("Holdings");
        content.initLayout(50,10,sections,buttons);
        content.addOneLine(50,10,1,sections,buttons);

        //NorthPanel.setPreferredSize(new Dimension(0,100));
        CenterPanel.add(content,BorderLayout.CENTER);

        accountInfo=  new ButtonList(800,200,defaultColor);
        ArrayList<String> accountSections = new ArrayList<>();
        ArrayList<String> accountButtons= new ArrayList<>();
        accountSections.add("Account");
        accountSections.add("BalanceUSD");
        accountSections.add("RealizedProfit");
        accountSections.add("UnrealizedProfit");
        accountInfo.initLayout(50,10,accountSections,accountButtons);
        accountInfo.addOneLine(50,10,1,accountSections,accountButtons);
        CenterPanel.add(accountInfo);
        updateAccountInfoDisplay();

        //content.getInfoSections().get
        //content.setBackground(defaultColor);
        setContentPane(basePanel);
        //content.add(new Button("hello"));
        setTitle("Security Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void updateAccountInfoDisplay(){
        content.getInfoSections().get(0).get("Account#").setText(detail.getAccID());
        content.getInfoSections().get(0).get("BalanceUSD").setText(Double.toString( detail.getUSDBalance()));
        content.getInfoSections().get(0).get("RealizedProfit").setText(Double.toString( detail.getRrealizedProfit()));
        content.getInfoSections().get(0).get("UnrealizedProfit").setText(Double.toString( detail.getUnrealizedProfit()));
        //content.repa

    }
}
