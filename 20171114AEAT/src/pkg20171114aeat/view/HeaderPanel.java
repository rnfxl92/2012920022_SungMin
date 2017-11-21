/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171114aeat.view;

import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEATType;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pkg20171114aeat.MyButtonActionListener;

/**
 *
 * @author comkeen
 */
public class HeaderPanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    
    public HeaderPanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setPreferredSize(new Dimension(400, 200));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("EventCode"));
        this.add(createRecord("EventDesc"));
        this.add(createRecord("Location"));
    }
    
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        
        JTextField textField = new JTextField("");
        box.add(textField);
        
        nameToTextField.put(name, textField);
        return box;
    }
      //xml파일에서 값을 가져와 각각 TextField에 입력
    public void loadAeat(AEATType aeat) {
        nameToTextField.get("EventCode").setText(aeat.getAEA().get(0).getHeader().getEventCode().getValue());
        nameToTextField.get("EventDesc").setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(0).getValue());
        nameToTextField.get("Location").setText(aeat.getAEA().get(0).getHeader().getLocation().get(0).getValue());
    }
    //각각 값들을 string으로 보여줌
    public String getEventCode(){
        return nameToTextField.get("EventCode").getText();
    }
       public String getEventDesc(){
        return nameToTextField.get("EventDesc").getText();
    }
        public String getLocation1(){
        return nameToTextField.get("Location").getText();
    }
}
