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
 * A class representing the transactions for the manager.
 */
public class TransactionWindow extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JPanel westPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JPanel centerPanel;
    private JFrame previous;
    private JButton backButton;

    public TransactionWindow(JFrame previous, String checkerMode, String customerName) {
        this.previous = previous;
        previous.setVisible(false);
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
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerPanel.setLayout(new GridLayout(1, 1));
        ButtonList content =  new ButtonList(800,600, new Color(57, 155, 255));
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("Date");
        sections.add("Name");
        sections.add("ID");
        sections.add("Type");
        sections.add("Amount");
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> transactions = fileOperator.readFile("Bank/transactions.txt");

        if (checkerMode.equals("Manager")) {
            setTitle("Daily Report");
            content.initLayout(50,10, sections, buttons);
            int found = 0;
            for (int i = 0; i < transactions.get("date").size(); i++) {
                if (transactions.get("date").get(i).equals("this-day")) {
                    content.addOneLine(50, 10, 1, sections, buttons);
                    content.getInfoSections().get(found).get("Date").setText(transactions.get("date").get(i));
                    content.getInfoSections().get(found).get("Name").setText(transactions.get("name").get(i));
                    content.getInfoSections().get(found).get("ID").setText(transactions.get("id").get(i));
                    content.getInfoSections().get(found).get("Type").setText(transactions.get("transaction_type").get(i));
                    content.getInfoSections().get(found).get("Amount").setText(transactions.get("amount").get(i));
                    found++;
                }
            }
            if (found > 0) {
                centerPanel.add(content);
                setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "There is no transactions today.");
                previous.setVisible(true);
            }
        } else if (checkerMode.equals("Customer")) {
            setTitle("Transactions");
        }
    }
}
