package Views.CustomeComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A class representing a table of contents and buttons.
 */
public class ButtonList extends JPanel {

    Color color;
    int lineCount = 0;
    int width,height;
    static int buttonsPerLine = 3;

    JScrollPane scrollableList;
    ArrayList <LinkedHashMap<String,JLabel>> infos = new ArrayList<>();
    ArrayList<JPanel>infoLines = new ArrayList<>();
    ArrayList<LinkedHashMap<String,JButton>> actions = new ArrayList<>();
    ArrayList<JPanel>actionLines= new ArrayList<>();
    private ArrayList<Integer> filters = new ArrayList<>();

    public ButtonList(int width, int height,Color color) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.setSize(width,height);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

    public void initLayout(int lineHeight, int unitWidth, ArrayList<String> sections , ArrayList<String> buttons){
        JPanel labelTop = new JPanel();
        labelTop.setMinimumSize(new Dimension(width,lineHeight));

        //labelTop.setPreferredSize(new Dimension(width,lineHeight));
        labelTop.setLayout(new BoxLayout(labelTop,BoxLayout.X_AXIS));
        for(String s:sections){
            JLabel   label=  new JLabel(s);
            label.setMaximumSize(new Dimension(  width/ 2 / sections.size(),lineHeight));
            label.setAlignmentX(JLabel.LEADING);
            label.setAlignmentY(JLabel.TOP_ALIGNMENT);
            //label.setVisible(true);
            labelTop.add(label);
        }
        JLabel   actionLabel=  new JLabel("Actions");
        actionLabel.setAlignmentX(LEFT_ALIGNMENT);
        actionLabel.setAlignmentY(TOP_ALIGNMENT);
        actionLabel.setMinimumSize(new Dimension(  width/ 2 ,lineHeight));
        labelTop.add(actionLabel);
        labelTop.setAlignmentX(LEFT_ALIGNMENT);
        this.add(labelTop);

        updateLines();

    }

    public void removeLine(int i ){
        lineCount--;
        infoLines.remove(i);
        infos.remove(i);
        actionLines.remove(i);
        //buttonLine.setVisible(true);
        actions.remove(i);
        updateLines();

    }

    public void addOneLine(int lineHeight,int unitWidth, int lineCount, ArrayList<String> sections , ArrayList<String> buttons){
        this.lineCount++;
        LinkedHashMap<String,JLabel> infoLineSections =  new LinkedHashMap<>();
        JPanel labelLine = new JPanel();
        labelLine.setLayout(new BoxLayout(labelLine,BoxLayout.X_AXIS));
        //labelLine.setLayout(new GridLayout());
        labelLine.setBackground(color);
        //labelLine.setPreferredSize(new Dimension(maxLength * sections.size(),lineHeight));
        labelLine.setMinimumSize(new Dimension(width/ 2,lineHeight));
        labelLine.setPreferredSize(new Dimension(width/2,lineHeight));

        labelLine.setAlignmentY(JLabel.TOP);
        labelLine.setAlignmentX(JLabel.LEFT);
        for(String s: sections){
            JLabel   label=  new JLabel(s);
            label.setMaximumSize(new Dimension(  width/ 2 / sections.size(),lineHeight));
            label.setAlignmentX(LEFT_ALIGNMENT);
            label.setAlignmentY(TOP_ALIGNMENT);
            //label.setVisible(true);
            labelLine.add(label);
            infoLineSections.put(s,label);//hashmap for accessing the String field of the line
        }
        infoLines.add(labelLine);
        infos.add(infoLineSections);


        LinkedHashMap<String,JButton> buttonLineSections=  new LinkedHashMap<>();
        JPanel buttonLine= new JPanel();

        //buttonLine.setLayout(new BoxLayout(buttonLine,BoxLayout.X_AXIS));
        //buttonLine.setPreferredSize(new Dimension(maxLength* buttons.size(),lineHeight));
        buttonLine.setMinimumSize(new Dimension(width/ 2,50));
        //buttonLine.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //buttonLine.setLayout(new GridLayout(-1,3));
        buttonLine.setLayout(new BoxLayout(buttonLine,BoxLayout.X_AXIS));
        buttonLine.setBackground(color);
        buttonLine.setAlignmentX(Button.LEFT_ALIGNMENT);
        buttonLine.setAlignmentY(Button.TOP_ALIGNMENT);
        for(String s: buttons){
            JButton button  = new JButton(s);
            button.setMaximumSize(new Dimension( width/2/ buttonsPerLine,lineHeight));
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            button.setBackground(color);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.NORTH);
            //button.setVisible(true);
            buttonLine.add(button);
            buttonLineSections.put(s,button);
            //hashmap for accessing the String field of the line
        }
        actionLines.add(buttonLine);
        //buttonLine.setVisible(true);
        actions.add(buttonLineSections);
        updateLines();
    }

    public void updateLines(){

        this.setSize(width,height);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for( Component c : this.getComponents()){
            if(c instanceof  JScrollPane)this.remove(c);
        }

        JPanel leftAndRight = new JPanel();
        leftAndRight.setLayout(new BoxLayout(leftAndRight,  BoxLayout.X_AXIS));
        leftAndRight.setBounds(0,0,width ,height);
        leftAndRight.setMinimumSize(new Dimension( width,height));
        scrollableList  = new JScrollPane();
        scrollableList.setBackground(color);
        this.add(scrollableList);
        scrollableList.setViewportView(leftAndRight);
        scrollableList.setPreferredSize(new Dimension(width,height));
        //setting left and right
        JPanel left= new JPanel();
        left.setLayout(new BoxLayout(left  ,BoxLayout.Y_AXIS));
        left.setMinimumSize(new Dimension(width/2,height));
        left.setAlignmentY(TOP_ALIGNMENT);
        //left.setLayout(new FlowLayout());
        leftAndRight.add(left,0);
        JPanel right  = new JPanel();
        right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));
        right.setMinimumSize(new Dimension(width/2,height));
        right.setAlignmentY(TOP_ALIGNMENT);

        leftAndRight.add(right,1);
        leftAndRight.setBackground(color);
        //this.setForeground(color);
        for(int i = 0 ; i < lineCount; i++){//from left to right from up to down
            if(filters.contains(i))continue;;
            left.add(infoLines.get(i));
            right.add(actionLines.get(i));
            //this.getLayout().addLayoutComponent("info", infoLines.get(i));
            //this.getLayout().addLayoutComponent( "action",actionLines.get(i));
        }
        revalidate();
        repaint();
    }

    //public void addLine(){
    //
    //}

    public void updateFilterIndices(ArrayList<Integer> filters){
       this.filters = filters;
    }

    public ArrayList< LinkedHashMap<String,JLabel>>  getInfoSections(){
        return infos;
    }
    public ArrayList< LinkedHashMap<String,JButton>>  getActions(){
        return actions;
    }

}
