/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171107aeat;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author 216-03
 */
class LiveMediaPanel extends JPanel{
    
       public LiveMediaPanel() {
        super();
        this.setPreferredSize(new Dimension(400, 200));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        initComponents();

    }

    private void initComponents() {
        this.add(createBox("lang"));
      
   

    }

    private Box createBox(String name) {
        JLabel label = new JLabel(name);
        JTextField textField = new JTextField("");
        Box box = Box.createHorizontalBox();//box 생성
        box.add(label);//box에 label 추가
        box.add(textField);//box에 textField추가
        return box; //panel에 box추가

    }
}
