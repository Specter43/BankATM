package Views;

import Account.Account;

import javax.swing.*;
import java.awt.*;

public class FrameATM extends JFrame {
    private JFrame frame;
    private Account currentAccount;
    private boolean isManager;

    public FrameATM() {
        frame = new JFrame();
        frame.setTitle("Bank ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(204, 0, 0));
    }
}
