package Views;

import Input.FileOperator;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockWindow extends JFrame {
    private JPanel panel;

    public StockWindow(String checkerMode, String customerName) {
        createWindow(checkerMode, customerName);
    }

    private void createWindow(String checkerMode, String customerName) {
        setContentPane(panel);
        setTitle("Stock Market");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonList content =  new ButtonList(500,500, new Color(57, 155, 255));
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("Date");
        sections.add("Name");
        sections.add("Price");
        sections.add("Tradable");
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> stocks = fileOperator.readFile("Service/Stocks.txt");

        if (checkerMode.equals("Manager Update Price")) {
            buttons.add("Update");
            content.initLayout(50,10, sections, buttons);
            LocalDate currentDate = java.time.LocalDate.now();
            int found = 0;
            for (int i = 0; i < stocks.get("date").size(); i++) {
                if (LocalDate.parse(stocks.get("date").get(i)).equals(currentDate)) {
                    content.addOneLine(50, 10, 1, sections, buttons);
                    content.getInfoSections().get(found).get("Date").setText(stocks.get("date").get(i));
                    content.getInfoSections().get(found).get("Name").setText(stocks.get("name").get(i));
                    content.getInfoSections().get(found).get("Price").setText(stocks.get("price").get(i));
                    content.getInfoSections().get(found).get("Tradable").setText(stocks.get("tradable").get(i));
                    int finalFound = found;
                    content.getActions().get(found).get("Update").addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            String price = JOptionPane.showInputDialog("Enter a price");
                            content.getInfoSections().get(finalFound).get("Price").setText(price);
                        }
                    });
                    found++;
                }
            }
            setContentPane(content);
            setVisible(true);
        }
        else if (checkerMode.equals("Manager Change Status")) {
            buttons.add("Change");
            content.initLayout(50,10, sections, buttons);
            LocalDate currentDate = java.time.LocalDate.now();
            int found = 0;
            for (int i = 0; i < stocks.get("date").size(); i++) {
                if (LocalDate.parse(stocks.get("date").get(i)).equals(currentDate)) {
                    content.addOneLine(50, 10, 1, sections, buttons);
                    content.getInfoSections().get(found).get("Date").setText(stocks.get("date").get(i));
                    content.getInfoSections().get(found).get("Name").setText(stocks.get("name").get(i));
                    content.getInfoSections().get(found).get("Price").setText(stocks.get("price").get(i));
                    content.getInfoSections().get(found).get("Tradable").setText(stocks.get("tradable").get(i));
                    int finalFound = found;
                    content.getActions().get(found).get("Change").addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            content.getInfoSections().get(finalFound).get("Tradable").setText(content.getInfoSections().get(finalFound).get("Tradable").getText().equals("true") ? "false" : "true");
                        }
                    });
                    found++;
                }
            }
            setContentPane(content);
            setVisible(true);
        }
        else if (checkerMode.equals("Customer")) {

        }
    }
}
