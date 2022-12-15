package Views;

import Input.FileOperator;
import Views.CustomeComponents.ButtonList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockWindow extends JFrame {
    private JPanel panel;
    private JPanel westPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JPanel centerPanel;
    private JButton backButton;
    ButtonList contentSell;
    ArrayList<String> sellButton = new ArrayList<String>(){{add("Sell");}};
    ArrayList<String> sellSections = new ArrayList<String>(){{add("Name"); add("Share"); add("Price"); add("Tradable");}};

    public StockWindow(JFrame previous, String checkerMode, String customerID) {
        previous.setVisible(false);
        createWindow(checkerMode, customerID);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                previous.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void createWindow(String checkerMode, String customerID) {
        setContentPane(panel);
        setTitle("Stock Market");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonList content =  new ButtonList(800,600, new Color(57, 155, 255));
        ArrayList<String> sections = new ArrayList<>();
        ArrayList<String> buttons= new ArrayList<>();
        sections.add("Date");
        sections.add("Name");
        sections.add("Price");
        sections.add("Tradable");
        FileOperator fileOperator = new FileOperator();
        HashMap<String, List<String>> stocks = fileOperator.readFile("Service/Stocks.txt");

        if (checkerMode.equals("Manager Update Price")) {
            centerPanel.setLayout(new GridLayout(1, 1));
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
                    int finalI = i;
                    content.getActions().get(found).get("Update").addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            String price = JOptionPane.showInputDialog("Enter a price");
                            content.getInfoSections().get(finalFound).get("Price").setText(price);
                            String newLine = stocks.get("date").get(finalI) + " " + stocks.get("name").get(finalI) + " " +
                                    price + " " + stocks.get("tradable").get(finalI);
                            try {
                                fileOperator.changeLine("Service/Stocks.txt", finalI, newLine);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    found++;
                }
            }
            centerPanel.add(content);
            setVisible(true);
        }

        else if (checkerMode.equals("Manager Change Status")) {
            centerPanel.setLayout(new GridLayout(1, 1));
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
                    int finalI = i;
                    content.getActions().get(found).get("Change").addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            content.getInfoSections().get(finalFound).get("Tradable").setText(content.getInfoSections().get(finalFound).get("Tradable").getText().equals("true") ? "false" : "true");
                            String newLine = stocks.get("date").get(finalI) + " " + stocks.get("name").get(finalI) + " " +
                                    stocks.get("price").get(finalI) + " " + content.getInfoSections().get(finalFound).get("Tradable").getText();
                            try {
                                fileOperator.changeLine("Service/Stocks.txt", finalI, newLine);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    found++;
                }
            }
            centerPanel.add(content);
            setVisible(true);
        }

        else if (checkerMode.equals("Customer")) {
            centerPanel.setLayout(new GridLayout(2, 1));
            HashMap<String, String> Tradability = new HashMap<>();
            // Add buys
            buttons.add("Buy");
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
                    Tradability.put(stocks.get("name").get(i), stocks.get("tradable").get(i));
                    int finalFound = found;
                    int finalI = i;
                    content.getActions().get(found).get("Buy").addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            // Actually buy
                            String share = JOptionPane.showInputDialog("Enter how many shares you want to buy:");
                            try {
                                List<String> lines = Files.readAllLines(Paths.get("Account/SecurityAccounts.txt"));
                                for (int j = 0; j < lines.size(); j++) {
                                    String line = lines.get(j);
                                    String[] securityAccountInfo = line.split("\\s+");
                                    if (securityAccountInfo[0].equals(customerID)) {
                                        securityAccountInfo[2] = Double.toString(Double.parseDouble(securityAccountInfo[2]) - Integer.parseInt(share) * Double.parseDouble(stocks.get("price").get(finalI)));
                                        String newLine = String.join(" ", securityAccountInfo) + "," + stocks.get("name").get(j) + "-" + share + "-" + stocks.get("price").get(finalI);
                                        fileOperator.changeLine("Account/SecurityAccounts.txt", j-1, newLine);
                                        contentSell.addOneLine(50, 10, 1, sellSections, sellButton);
                                        contentSell.getInfoSections().get(contentSell.getInfoSections().size()-1).get("Name").setText(stocks.get("name").get(j));
                                        contentSell.getInfoSections().get(contentSell.getInfoSections().size()-1).get("Share").setText(share);
                                        contentSell.getInfoSections().get(contentSell.getInfoSections().size()-1).get("Price").setText(stocks.get("price").get(finalI));
                                        contentSell.getInfoSections().get(contentSell.getInfoSections().size()-1).get("Tradable").setText(Tradability.get(stocks.get("name").get(j)));
                                        contentSell.getActions().get(contentSell.getInfoSections().size()-1).get("Sell").addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                super.mouseClicked(e);
                                                // Actually Sell

                                            }
                                        });
                                    }
                                }
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    found++;
                }
            }
            centerPanel.add(content);

            // Add Sells
            contentSell =  new ButtonList(1000,600, new Color(57, 155, 255));
            contentSell.initLayout(50,10, sellSections, sellButton);
            HashMap<String, List<String>> stockHoldings = fileOperator.readFile("Account/SecurityAccounts.txt");
            found = 0;
            for (int i = 0; i < stockHoldings.get("personnelID").size(); i++) {
                if (stockHoldings.get("personnelID").get(i).equals(customerID)) {
                    String[] holdingStocks = stockHoldings.get("HoldingStocks").get(i).split(",");
                    for (String stock : holdingStocks) {
                        String[] stockInfo = stock.split("-");
                        String stockName = stockInfo[0];
                        String stockHoldingShares = stockInfo[1];
                        String stockHoldingPrice = stockInfo[2];
                        contentSell.addOneLine(50, 10, 1, sellSections, sellButton);
                        contentSell.getInfoSections().get(found).get("Name").setText(stockName);
                        contentSell.getInfoSections().get(found).get("Share").setText(stockHoldingShares);
                        contentSell.getInfoSections().get(found).get("Price").setText(stockHoldingPrice);
                        contentSell.getInfoSections().get(found).get("Tradable").setText(Tradability.get(stockName));
                        int finalFound = found;
                        contentSell.getActions().get(found).get("Sell").addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                // Actually Sell

                            }
                        });
                        found++;
                    }
                }
            }
            centerPanel.add(contentSell);
            setVisible(true);
        }
    }
}