package Views;

import Input.FileOperator;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing the check customer window.
 */
public class CheckCustomerWindow extends JFrame {
    private final String checkMode;
    private final String customerName;
    private JFrame frame;
    private JPanel panel;
    private JPanel westPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JButton backButton;
    private JPanel centerPanel;

    public CheckCustomerWindow(JFrame previous, String checkerMode, String customerName) {
        previous.setVisible(false);
        this.checkMode = checkerMode;
        this.customerName = customerName;
        createWindow(checkerMode, customerName);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                previous.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void createWindow(String checkerMode, String customerName) {
        setContentPane(panel);
        setTitle("Customer Checker");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonList content =  new ButtonList(1000,600, new Color(57, 155, 255));
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("ID");
        sections.add("Name");
        sections.add("Account");
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> customers = fileOperator.readFile("Personnel/Personnels.txt");
        centerPanel.setLayout(new GridLayout(1,1));

        if (checkerMode.equals("Specific")) {
            int found = 0;
            content.initLayout(50,10, sections, buttons);
            for (int i = 0; i < customers.get("ID").size(); i++) {
                if (customers.get("name").get(i).equals(customerName)) {
                    content.addOneLine(50, 10, 1, sections, buttons);
                    content.getInfoSections().get(i).get("ID").setText(customers.get("ID").get(i));
                    content.getInfoSections().get(i).get("Name").setText(customers.get("name").get(i));
                    content.getInfoSections().get(i).get("Account").setText(customers.get("accountID").get(i));
                    found++;
                    centerPanel.add(content);
                    setVisible(true);
                }
            }
            if (found == 0) {
                JOptionPane.showMessageDialog(null, "No such customer exist.");
            }
        }
        else if (checkerMode.equals("All")) {
            content.initLayout(50,10, sections, buttons);
            for (int i = 0; i < customers.get("ID").size(); i++) {
                content.addOneLine(50, 10,1, sections, buttons);
                content.getInfoSections().get(i).get("ID").setText(customers.get("ID").get(i));
                content.getInfoSections().get(i).get("Name").setText(customers.get("name").get(i));
                content.getInfoSections().get(i).get("Account").setText(customers.get("accountID").get(i));
            }
            centerPanel.add(content);
            setVisible(true);
        }
        else if (checkerMode.equals("Poor")){
            sections.add("Owe USD");
            sections.add("Owe EURO");
            sections.add("Owe RMB");
            content.initLayout(50,10, sections, buttons);
            HashMap<String, List<String>> checkingAccounts = fileOperator.readFile("Account/CheckingAccounts.txt");
            HashMap<String, List<String>> savingsAccounts = fileOperator.readFile("Account/SavingsAccounts.txt");
            HashMap<String, List<String>> securityAccounts = fileOperator.readFile("Account/SecurityAccounts.txt");
            int found = 0;
            for (int i = 0; i < checkingAccounts.get("personnelID").size(); i++) {
                String currentAccID = customers.get("accountID").get(i);
                for (String accID : checkingAccounts.get("accID")) {
                    if (currentAccID.equals(accID)) {
                        double currentUSD = Double.parseDouble(checkingAccounts.get("BalanceUSD").get(i));
                        double currentEURO = Double.parseDouble(checkingAccounts.get("BalanceEURO").get(i));
                        double currentRMB = Double.parseDouble(checkingAccounts.get("BalanceRMB").get(i));
                        if (currentUSD < 0 || currentEURO < 0 || currentRMB < 0) {
                            content.addOneLine(50, 10,1, sections, buttons);
                            content.getInfoSections().get(found).get("ID").setText(customers.get("ID").get(found));
                            content.getInfoSections().get(found).get("Name").setText(customers.get("name").get(found));
                            content.getInfoSections().get(found).get("Account").setText(customers.get("accountID").get(found));
                            if (currentUSD < 0) {
                                content.getInfoSections().get(found).get("Owe USD").setText(Double.toString(currentUSD));
                            } else {
                                content.getInfoSections().get(found).get("Owe USD").setText(Double.toString(0));
                            }
                            if (currentEURO < 0) {
                                content.getInfoSections().get(found).get("Owe EURO").setText(Double.toString(currentEURO));
                            } else {
                                content.getInfoSections().get(found).get("Owe EURO").setText(Double.toString(0));
                            }
                            if (currentRMB < 0) {
                                content.getInfoSections().get(found).get("Owe RMB").setText(Double.toString(currentRMB));
                            } else {
                                content.getInfoSections().get(found).get("Owe RMB").setText(Double.toString(0));
                            }
                            found++;
                        }
                    }
                }
            }
            if (found > 0) {
                centerPanel.add(content);
                setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No one is owing money!");
            }
        } else {

        }

    }
}