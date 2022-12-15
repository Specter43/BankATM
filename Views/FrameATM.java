package Views;

import Account.Account;
import Bank.Bank;
import Personnel.Customer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        JFrame current = this;
        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameTextField.getText().equals("admin") && Arrays.equals(PINPasswordField.getPassword(), "admin".toCharArray())) {
                    ManagerInfoWindow managerInfoWindow = new ManagerInfoWindow();
                    setVisible(false);
                } else {
                    char[] passwordInput = PINPasswordField.getPassword();
                    String password = Stream.of(passwordInput).map(String::new).collect(Collectors.joining());
                    ArrayList<Object> login = Bank.login(usernameTextField.getText(), password);
                    System.out.println(login);
                    if ((Boolean) login.get(0)) {
                        Customer customer = (Customer) login.get(1);
                        SpecificCustomerWindow specificCustomerWindow = new SpecificCustomerWindow(current, "Customer", customer);
                    }
                }
            }
        });
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                char[] passwordInput = PINPasswordField.getPassword();
                String password = Stream.of(passwordInput).map(String::new).collect(Collectors.joining());
                ArrayList<Object> signupInput = Bank.signUp(usernameTextField.getText(), password);
                if ((Boolean) signupInput.get(0)) {
                    SpecificCustomerWindow specificCustomerWindow = new SpecificCustomerWindow(current, "Customer", (Customer) signupInput.get(1));
                }
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
