package Views;

import Input.FileOperator;
import Personnel.Customer;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing the loan window.
 */
public class LoanWindow extends JFrame {
    private JPanel panel;
    private JPanel centerPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JPanel westPanel;
    private JFrame previous;
    private JButton backButton;

    public LoanWindow(JFrame previous, String customerID) {
        this.previous = previous;
        previous.setVisible(false);
        createWindow(customerID);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                previous.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void createWindow(String customerID) {
        setContentPane(panel);
        setTitle("Loan");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerPanel.setLayout(new GridLayout(1, 1));
        ButtonList content =  new ButtonList(500,500, new Color(57, 155, 255));
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("Amount");
        sections.add("Interest Rate");
        buttons.add("Pay");
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> loans = fileOperator.readFile("Service/Loans.txt");

        content.initLayout(50,10, sections, buttons);
        int found = 0;
        for (int i = 0; i < loans.get("debtorID").size(); i++) {
            if (loans.get("debtorID").get(i).equals(customerID)) {
                content.addOneLine(50, 10, 1, sections, buttons);
                content.getInfoSections().get(found).get("Amount").setText(loans.get("amount").get(i));
                content.getInfoSections().get(found).get("Interest Rate").setText(loans.get("interestRate").get(i));
                int finalI = i;
                content.getActions().get(found).get("Pay").addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String amount = JOptionPane.showInputDialog("Enter an amount to pay:");
                        // Take money from checking
                        boolean canWithdrawal = Customer.withdrawFromCheckingOrSaving(Integer.parseInt(customerID), Double.parseDouble(amount), "checking", "BalanceUSD");
                        if (!canWithdrawal) {
                            JOptionPane.showMessageDialog(null, "You don't have this amount of money in your checking account.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Thank you for your payment!");
                        }
                        double newLoanBalance = Double.parseDouble(loans.get("amount").get(finalI)) - Double.parseDouble(amount);
                        String newLine = customerID + " " + newLoanBalance + " " + loans.get("interestRate").get(finalI);
                        try {
                            fileOperator.changeLine("Service/Loans.txt", finalI - 1, newLine);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                found++;
            }
        }
        if (found > 0) {
            centerPanel.add(content);
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "You have no loans.");
            previous.setVisible(true);
        }
    }
}
