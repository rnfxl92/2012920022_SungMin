/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171114aeat.view;


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
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEATType;

/**
 *
 * @author 216-03
 */
public class MediaPanel extends JPanel {

    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;

    public MediaPanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setPreferredSize(new Dimension(400, 200));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("MediaAssoc"));
        this.add(createRecord("MediaDesc"));
        this.add(createRecord("MediaUrl"));
        this.add(createRecord("AlternateUrl"));
        
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
        nameToTextField.get("MediaUrl").setText(aeat.getAEA().get(0).getMedia().get(0).getUrl().toString());
        nameToTextField.get("MediaDesc").setText(aeat.getAEA().get(0).getMedia().get(0).getMediaDesc().toString());
        nameToTextField.get("AlternateUrl").setText(aeat.getAEA().get(0).getMedia().get(0).getAlternateUrl().toString());
        nameToTextField.get("MediaAssoc").setText(aeat.getAEA().get(0).getMedia().get(0).getMediaAssoc().toString());
    }
    //각각 값들을 string으로 보여줌
    public String getMediaUrl(){
        return nameToTextField.get("MediaUrl").getText();
    }
    public String getMediaDesc(){
        return nameToTextField.get("MediaDesc").getText();
    }
    public String getAlternateUrl(){
        return nameToTextField.get("AlternateUrl").getText();
    }
    public String getMediaAssoc(){
        return nameToTextField.get("MediaAssoc").getText();
    }
}
