package Views;

import Input.FileOperator;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanWindow extends JFrame {
    private JPanel panel;
    public LoanWindow(String customerID) {
        createWindow(customerID);
    }

    private void createWindow(String customerID) {
        setContentPane(panel);
        setTitle("Loan");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                found++;
            }
        }
        if (found > 0) {
            setContentPane(content);
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "You have no loans.");
        }
    }
}
