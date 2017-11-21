/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171114aeat.view;

import java.awt.Color;
import java.awt.Dimension;
import static java.lang.Short.parseShort;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import pkg20171114aeat.MyButtonActionListener;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEATType;


public class JRadioButtonPanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    private Map<String, JComboBox> nameToComboBox;
    private Map<String, JRadioButton> nameToRadioButton;
    
    public JRadioButtonPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 200));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.PINK));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        nameToRadioButton = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {

        this.add(createComboRecord("wakeup"));
       
    }
    
    
    //콤보박스 생성
    private Box createComboRecord(String name){
       Box box = Box.createHorizontalBox();
       JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
       JRadioButton radioButton = new JRadioButton();
      
       box.add(radioButton);
            
       nameToRadioButton.put(name, radioButton);
       return box;
      
    }
    
   
    //xml파일에서 값을 가져와 각각 입력
    public void loadAeat(AEATType aeat) {
       
        nameToRadioButton.get("wakeup");
        
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
