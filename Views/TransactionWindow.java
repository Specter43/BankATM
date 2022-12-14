package Views;

import Input.FileOperator;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionWindow extends JFrame {
    private JFrame frame;
    private JPanel panel;
    public TransactionWindow(String checkerMode, String customerName) {
        createWindow(checkerMode, customerName);
    }

    private void createWindow(String checkerMode, String customerName) {
        setContentPane(panel);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonList content =  new ButtonList(500,500, new Color(57, 155, 255));
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("From");
        sections.add("To");
        sections.add("Amount");
        sections.add("Date");
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> transactions = fileOperator.readFile("Account/Transactions.txt");

        if (checkerMode.equals("Manager")) {
            setTitle("Daily Report");
            content.initLayout(50,10, sections, buttons);
            LocalDate currentDate = java.time.LocalDate.now();
            int found = 0;
            for (int i = 0; i < transactions.get("Date").size(); i++) {
                if (LocalDate.parse(transactions.get("Date").get(i)).equals(currentDate)) {
                    content.addOneLine(50, 10, 1, sections, buttons);
                    content.getInfoSections().get(found).get("From").setText(transactions.get("sourcePersonnel").get(i));
                    content.getInfoSections().get(found).get("To").setText(transactions.get("toPersonnel").get(i));
                    content.getInfoSections().get(found).get("Amount").setText(transactions.get("amount").get(i));
                    content.getInfoSections().get(found).get("Date").setText(transactions.get("Date").get(i));
                    found++;
                }
            }
            if (found > 0) {
                setContentPane(content);
                setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "There is no transactions today.");
            }
        } else if (checkerMode.equals("Customer")) {
            setTitle("Transactions");

        }
    }
}
