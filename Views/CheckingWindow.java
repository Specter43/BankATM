package Views;

import Personnel.*;

import javax.swing.*;
import Account.*;
import Views.CustomeComponents.ButtonList;

import java.awt.*;
import java.util.ArrayList;

public class CheckingWindow extends JFrame {

    private final Color  defaultColor = new Color(137,187,136);

    Personnel operator;
    Account detail;
    private JPanel basePanel;
    private JPanel WestPanel;
    private JPanel NorthPanel;
    private JPanel EastPanel;
    private JPanel SouthPanel;
    private JButton button1;
    private JTextField textField1;

    private JTextField balance;

    public CheckingWindow(Customer operator,Account detail){
        this.operator = operator;
        this.detail = detail;
        createUIComponents();
        //this.validate();

    }

    //https://stackoverflow.com/questions/4627553/show-jframe-in-a-specific-screen-in-dual-monitor-configuration
    public static void showOnScreen( int screen, JFrame frame )
    { GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        if( screen > -1 && screen < gd.length ) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x,
                    gd[1].getDefaultConfiguration().getBounds().y + frame.getY());
        } else if( gd.length > 0 ) {
            frame.setLocation(gd[1].getDefaultConfiguration().getBounds().x,
                    gd[1].getDefaultConfiguration().getBounds().y + frame.getY());
        } else {
            throw new RuntimeException( "No Screens Found" );
        }
    }

    public CheckingWindow(Customer subject, Manager operator,Account detail){
        this.operator = operator;
        this.detail = detail;
        createUIComponents();
    }


    private void createUIComponents() {
        ButtonList content =  new ButtonList(800,200,defaultColor);
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("Account#");
        sections.add("Account Type");
        sections.add("Balance");
        buttons.add("deposit");
        buttons.add("withdrawal");
        buttons.add("transfer");
        content.initLayout(50,10,sections,buttons);
        content.addOneLine(50,10,1,sections,buttons);

        //NorthPanel.setPreferredSize(new Dimension(0,100));
        textField1.setPreferredSize(new Dimension(100,50));
        basePanel.add(content,BorderLayout.CENTER);


        //content.getInfoSections().get
        //content.setBackground(defaultColor);
        setContentPane(basePanel);
        //content.add(new Button("hello"));
        setTitle("Checking Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showOnScreen(0,this);
        setVisible(true);
    }
}
