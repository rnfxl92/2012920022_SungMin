/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171114aeat.view;


import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEATType;
import pkg20171114aeat.MyButtonActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static java.lang.Short.parseShort;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author comkeen
 */
public class ComboBoxPanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    private Map<String, JComboBox> nameToComboBox;
    
    public ComboBoxPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 200));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.PINK));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {

        this.add(createComboRecord("audience"));
        this.add(createComboRecord("aeaType"));
        this.add(createComboRecord("priority"));
        this.add(createComboRecord("mediaType"));
    }
    
    
    //콤보박스 생성
    private Box createComboRecord(String name){
       Box box = Box.createHorizontalBox();
       JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
       JComboBox comboBox = new JComboBox();
      
       box.add(comboBox);
            
       nameToComboBox.put(name, comboBox);
       return box;
      
    }
    

    
    
    //xml파일에서 값을 가져와 각각 입력
    public void loadAeat(AEATType aeat) {
       
        nameToComboBox.get("audience").addItem(aeat.getAEA().get(0).getAudience().value());
        nameToComboBox.get("aeaType").addItem(aeat.getAEA().get(0).getAeaType().value());
        nameToComboBox.get("priority").addItem(aeat.getAEA().get(0).getPriority().toString());
        nameToComboBox.get("mediaType").addItem(aeat.getAEA().get(0).getMedia().get(0).getMediaType().toString());
    }
    
       
    
   //각각 값들을 보여줌
     public String getAudience(){
       return nameToComboBox.get("audience").getSelectedItem().toString();
   }
   public String getAeaType(){
       return nameToComboBox.get("aeaType").getSelectedItem().toString();
   }
  
   public short getPriority(){
       return parseShort(nameToComboBox.get("priority").getSelectedItem().toString());
       
      }
   public String getMediaType(){
       return nameToComboBox.get("mediaType").getSelectedItem().toString();
   }
         
}
