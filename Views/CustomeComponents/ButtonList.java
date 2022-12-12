package Views.CustomeComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ButtonList extends JPanel {
    int lineCount = 0;
    ArrayList <LinkedHashMap<String,JLabel>> infos = new ArrayList<>();
    ArrayList<JPanel>infoLines = new ArrayList<>();
    ArrayList<LinkedHashMap<String,JButton>> actions = new ArrayList<>();
    ArrayList<JPanel>actionLines= new ArrayList<>();

    public ButtonList() {
        this.setSize(800,600);
        this.setVisible(true);

    }

    public void populateLayout(int lineCount, ArrayList<String> sections , ArrayList<String> buttons){

        this.setLayout(new GridLayout(lineCount,2));
        this.lineCount= lineCount;
        for(int i = 0 ; i < lineCount; i++){

            LinkedHashMap<String,JLabel> infoLineSections =  new LinkedHashMap<>();
            JPanel labelLine = new JPanel(new GridLayout());
            for(String s: sections){
                JLabel   label=  new JLabel(s);
                labelLine.add(label);
                infoLineSections.put(s,label);
            }
            infoLines.add(labelLine);
            infos.add(infoLineSections);

            LinkedHashMap<String,JButton> buttonLineSections=  new LinkedHashMap<>();
            JPanel buttonLine= new JPanel(new GridLayout());
            for(String s: buttons){
                JButton button  = new JButton(s);
                buttonLine.add(button);
                buttonLineSections.put(s,button);
            }
            actionLines.add(buttonLine);
            actions.add(buttonLineSections);
        }
        for(int i = 0 ; i < lineCount; i++){
            this.getLayout().addLayoutComponent("info", infoLines.get(i));
            this.getLayout().addLayoutComponent( "action",actionLines.get(i));
        }
    }

    //public void addLine(){
    //
    //}

    @Override
    public void repaint() {
        this.setLayout(new GridLayout(1,2));
        for(int i = 0 ; i < lineCount; i++){
            this.getLayout().addLayoutComponent("info", infoLines.get(i));
            this.getLayout().addLayoutComponent( "action",actionLines.get(i));
        }
    }

    public ArrayList< LinkedHashMap<String,JLabel>>  getInfoSections(){
        return infos;
    }
    public ArrayList< LinkedHashMap<String,JButton>>  getActions(){
        return actions;
    }

}
