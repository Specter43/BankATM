package Views;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A class representing the manager main page.
 */
public class ManagerInfoWindow extends JFrame {
    private JFrame frame;
    private JPanel Panel;
    private JLabel checkSpecificCustomerLabel;
    private JLabel checkAllCustomerLabel;
    private JLabel checkPoorCustomerLabel;
    private JLabel getDailyReportLabel;
    private JLabel updateStockPriceLabel;
    private JLabel changeStockStatusLabel;
    private JButton checkSpecificCustomerButton;
    private JButton checkAllCustomersButton;
    private JButton checkPoorCustomersButton;
    private JButton getDailyReportButton;
    private JButton updateStockPriceButton;
    private JButton changeStockStatusButton;
    private JLabel welcomeLabel;
    private JButton exitButton;
    private JLabel customerNameLabel;
    private JTextField customerNameTextField;

    public ManagerInfoWindow() {
        createWindow();
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameATM frameATM = new FrameATM();
                setVisible(false);
            }
        });

        setVisible(true);
        JFrame current = this;
        checkSpecificCustomerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CheckCustomerWindow checkSpecificCustomerWindow = new CheckCustomerWindow(current,"Specific", customerNameTextField.getText());
            }
        });
        checkAllCustomersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CheckCustomerWindow checkSpecificCustomerWindow = new CheckCustomerWindow(current,"All","" );
            }
        });
        checkPoorCustomersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CheckCustomerWindow checkSpecificCustomerWindow = new CheckCustomerWindow(current,"Poor","" );
            }
        });
        getDailyReportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TransactionWindow transactionWindow = new TransactionWindow(current,"Manager", "");
            }
        });
        updateStockPriceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StockWindow stockWindow = new StockWindow(current,"Manager Update Price", null);
            }
        });
        changeStockStatusButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StockWindow stockWindow = new StockWindow(current, "Manager Change Status", null);
            }
        });
    }

    private void createWindow() {
        setContentPane(Panel);
        setTitle("MANAGER INFO");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
