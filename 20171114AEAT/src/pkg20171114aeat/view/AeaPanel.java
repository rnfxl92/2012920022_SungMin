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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author comkeen
 */
public class AeaPanel extends JPanel {

    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    private Map<String, JComboBox> nameToComboBox;
    private Map<String, JRadioButton> nameToRadioButton;

    public AeaPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 200));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        nameToRadioButton = new HashMap<>();

        initComponents();
    }

    private void initComponents() {
        //TextField
        this.add(createRecord("aeaId"));
        this.add(createRecord("issuer"));
        this.add(createRecord("audience"));
        this.add(createRecord("aeaType"));
        this.add(createRecord("refAEAId"));
        this.add(createRecord("priority"));
        this.add(createRecord("wakeup"));
        //ComboBox
        this.add(createComboRecord("audience"));
        nameToComboBox.get("audience").addItem("public");
        nameToComboBox.get("audience").addItem("restricted");
        nameToComboBox.get("audience").addItem("private");
        nameToComboBox.get("audience").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) nameToComboBox.get("audience").getSelectedItem();
                nameToTextField.get("audience").setText(str);
            }

        });
        this.add(createComboRecord("aeaType"));
        nameToComboBox.get("aeaType").addItem("alert");
        nameToComboBox.get("aeaType").addItem("update");
        nameToComboBox.get("aeaType").addItem("cancel");
        nameToComboBox.get("aeaType").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) nameToComboBox.get("aeaType").getSelectedItem();
                nameToTextField.get("aeaType").setText(str);
            }

        });
        this.add(createComboRecord("priority"));
        nameToComboBox.get("priority").addItem("Minor Priority");
        nameToComboBox.get("priority").addItem("Low Priority");
        nameToComboBox.get("priority").addItem("Moderate Priority");
        nameToComboBox.get("priority").addItem("High Priority");
        nameToComboBox.get("priority").addItem("Maximum Priority");
        nameToComboBox.get("priority").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "" + nameToComboBox.get("priority").getSelectedIndex();
                nameToTextField.get("priority").setText(str);
            }
        });

        this.add(createRadioButton("wakeup"));
        nameToRadioButton.get("wakeup0").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameToTextField.get("wakeup").setText("true");
            }
        });
        nameToRadioButton.get("wakeup1").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameToTextField.get("wakeup").setText("false");
            }
        });

    }

    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);

        box.add(Box.createHorizontalGlue());

        JTextField textField = new JTextField("");
        box.add(textField);

        nameToTextField.put(name, textField);
        return box;
    }

    //콤보박스 생성
    private Box createComboRecord(String name) {
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

    private Box createRadioButton(String name) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        JRadioButton button1 = new JRadioButton("true");
        JRadioButton button2 = new JRadioButton("false");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(button1);
        buttonGroup.add(button2);
        box.add(button1);
        box.add(button2);

        nameToRadioButton.put(name + "0", button1);
        nameToRadioButton.put(name + "1", button2);

        return box;
    }

    //xml파일에서 값을 가져와 각각 TextField에 입력
    public void loadAeat(AEATType aeat) {
        //textField
        nameToTextField.get("aeaId").setText(aeat.getAEA().get(0).getAeaId());
        nameToTextField.get("issuer").setText(aeat.getAEA().get(0).getIssuer());
        nameToTextField.get("audience").setText(aeat.getAEA().get(0).getAudience().value());
        nameToTextField.get("aeaType").setText(aeat.getAEA().get(0).getAeaType().value());
        nameToTextField.get("refAEAId").setText(aeat.getAEA().get(0).getRefAEAId());
        nameToTextField.get("priority").setText(aeat.getAEA().get(0).getPriority().toString());
        nameToTextField.get("wakeup").setText(String.valueOf(aeat.getAEA().get(0).isWakeup()));
        //comboBox
        nameToComboBox.get("audience").setSelectedItem(aeat.getAEA().get(0).getAudience().value());
        nameToComboBox.get("aeaType").setSelectedItem(aeat.getAEA().get(0).getAeaType().value());
        nameToComboBox.get("priority").setSelectedIndex(aeat.getAEA().get(0).getPriority());
        //radioButton
        if (aeat.getAEA().get(0).isWakeup()) {
            nameToRadioButton.get("wakeup0").setSelected(true);
            nameToRadioButton.get("wakeup1").setSelected(false);
        } else {
            nameToRadioButton.get("wakeup0").setSelected(false);
            nameToRadioButton.get("wakeup1").setSelected(true);
        }
    }

    //각각 값들을 string으로 보여줌
    public String getAeaId() {
        return nameToTextField.get("aeaId").getText();
    }

    public String getIssuer() {
        return nameToTextField.get("issuer").getText();
    }

    public String getAudience() {
        return nameToTextField.get("audience").getText();
    }

    public String getAeaType() {
        return nameToTextField.get("aeaType").getText();
    }

    public String getRefAEAId() {
        return nameToTextField.get("refAEAId").getText();
    }

    public short getPriority() {
        return parseShort(nameToTextField.get("priority").getText());
    }

    public boolean getWakeup() {
        return parseBoolean(nameToTextField.get("wakeup").getText());
    }

    public String getAudienceComboBox() {
        return nameToComboBox.get("audience").getSelectedItem().toString();
    }

    public short getPriorityComboBox() {
        return (short) nameToComboBox.get("priority").getSelectedIndex();
    }

}
