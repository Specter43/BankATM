package Views;

import Personnel.*;

import javax.swing.*;
import Account.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Views.CustomeComponents.ButtonList;

import java.awt.*;
import java.util.ArrayList;

public class CheckingWindow extends JFrame {

    private final Color  defaultColor = new Color(137,187,136);

    Personnel operator;
    AccountChecking detail;
    private JPanel basePanel;
    private JPanel WestPanel;
    private JPanel NorthPanel;
    private JPanel EastPanel;
    private JPanel SouthPanel;
    private JButton exitButton;


    private  ButtonList content;

    public CheckingWindow(Customer operator,AccountChecking detail){
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
                    gd[0].getDefaultConfiguration().getBounds().y + frame.getY());
        } else if( gd.length > 0 ) {
            frame.setLocation(gd[1].getDefaultConfiguration().getBounds().x,
                    gd[1].getDefaultConfiguration().getBounds().y + frame.getY());
        } else {
            throw new RuntimeException( "No Screens Found" );
        }
    }



    private void createUIComponents() {
        content =  new ButtonList(800,200,defaultColor);

        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("Account#");
        sections.add("BalanceUSD");
        sections.add("BalanceEURO");
        sections.add("BalanceRMB");
        content.initLayout(50,10,sections,buttons);
        content.addOneLine(50,10,1,sections,buttons);

        //NorthPanel.setPreferredSize(new Dimension(0,100));
        basePanel.add(content,BorderLayout.CENTER);
        updateAccountInfoDisplay();


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

    public void updateAccountInfoDisplay(){
        content.getInfoSections().get(0).get("Account#").setText(detail.getAccID());
        content.getInfoSections().get(0).get("BalanceUSD").setText(Double.toString( detail.getUSDBalance()));
        content.getInfoSections().get(0).get("BalanceEURO").setText(Double.toString( detail.getEUROBalance()));
        content.getInfoSections().get(0).get("BalanceRMB").setText(Double.toString( detail.getRMBBalance()));
        //content.repa

    }
}
