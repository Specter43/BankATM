package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CheckCustomerWindow extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JTable customerTable;

    public CheckCustomerWindow(String checkerMode, String customerName) {
        createWindow(checkerMode, customerName);
    }

    private void createWindow(String checkerMode, String customerName) {
        setContentPane(panel);
        setTitle("Customer Checker");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        customerTable.setModel(model);
        model.addColumn("Customer Name");
        model.addColumn("Number of Accounts");
        model.addColumn("Total Balance");
        model.addColumn("Total debt");
        model.addColumn("Funds in Stocks");
        model.addColumn("Buttons");
        // Append a row
        model.addRow(new Object[]{"Customer Name", "Number of Accounts", "Total Balance", "Total debt", "Funds in Stocks"});
        model.addRow(new Object[]{"A", 5, 1000, 300, 500});

        setVisible(true);
    }
}
