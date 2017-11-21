/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171107aeat;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 216-03
 */
public class Main {

    public Main() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400,600));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        AEAPanel aeaPanel = new AEAPanel();
        HeaderPanel headerPanel = new HeaderPanel();
        AEATextPanel aeaTextPanel = new AEATextPanel();
        LiveMediaPanel liveMediaPanel  = new LiveMediaPanel();
        MediaPanel mediaPanel = new MediaPanel();
        
        mainPanel.add(aeaPanel);
        mainPanel.add(headerPanel);
        mainPanel.add(aeaTextPanel);
        mainPanel.add(liveMediaPanel);
        mainPanel.add(mediaPanel);
        
        
        frame.getContentPane().add(mainPanel);//frame의 container에 aea패널 추가
        
        frame.setSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main();
    }}
/*
    private static class AEAPanel  extends JPanel{

        public AEAPanel() {
            super();
            this.setPreferredSize(new Dimension(400,200));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
        
        
        }
    }
    
}
*/