package Views;

import Account.Account;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameATM extends JFrame {
    private JFrame frame;
    private Account currentAccount;
    private boolean isManager;
    private JLabel welcomeLabel;
    private JTextField usernameTextField;
    private JPasswordField PINPasswordField;
    private JButton logInButton;
    private JPanel mainPanel;
    private JButton signUpButton;

    public FrameATM() {
        createWindow();
        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ManagerInfoWindow managerInfoWindow = new ManagerInfoWindow();
                setVisible(false);
            }
        });
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("SHIT");
            }
        });
    }

    private void createWindow() {
        setContentPane(mainPanel);
        setTitle("BANK ATM");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
