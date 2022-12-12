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
    private JPanel panel1;
    private JLabel name;
    private JPanel LeftFields;
    private JPanel RightFields;
    private JButton transferButton;
    private JTextField accountBalance;
    private JButton withDrawButton;

    private JTextField balance;

    public CheckingWindow(Customer operator,Account detail){
        this.operator = operator;
        this.detail = detail;
        ButtonList content =  new ButtonList();
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("people");
        buttons.add("transfer");
        content.populateLayout(1,sections,buttons);
        content.setBackground(defaultColor);
        setContentPane(content);
        setTitle("Checking Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showOnScreen(0,this);
        setVisible(true);
        //this.validate();

    }

    //https://stackoverflow.com/questions/4627553/show-jframe-in-a-specific-screen-in-dual-monitor-configuration
    public static void showOnScreen( int screen, JFrame frame )
    { GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        if( screen > -1 && screen < gd.length ) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x,
                    gd[0].getDefaultConfiguration().getBounds().y + frame.getY());
        } else if( gd.length > 0 ) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x,
                    gd[0].getDefaultConfiguration().getBounds().y + frame.getY());
        } else {
            throw new RuntimeException( "No Screens Found" );
        }
    }
    public ButtonList getCustomeLayout(){
        return new ButtonList();
    }

    public CheckingWindow(Customer subject, Manager operator,Account detail){
        this.operator = operator;
        this.detail = detail;
    }






}
