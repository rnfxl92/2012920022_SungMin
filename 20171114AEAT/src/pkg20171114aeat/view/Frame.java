/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171114aeat.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEATType;
import pkg20171114aeat.MyButtonActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEAType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEAtypeType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AudienceType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.HeaderType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.LangType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.LiveMediaType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.LocationType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.MediaType;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.TypeType;

/**
 *
 * @author comkeen
 */
public class Frame {

    private JFrame frame;
    private ExpirePanel expirePanel;
    private AeaPanel aeaPanel;
    private HeaderPanel headerPanel;
    private AeatextPanel aeatextPanel;
    private LiveMediaPanel liveMediaPanel;
    private MediaPanel mediaPanel;
    private JPanel buttonPanel;
   
    private JRadioButtonPanel radioButtonPanel;

    private MyButtonActionListener buttonActionListener;

    public static final String TITLE = "AEAT Publisher";
    public static final Dimension LABEL_DIMENSION = new Dimension(80, 40);
    public static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);

    
    //프레임에 각각의 패널을 붙임
    public Frame(MyButtonActionListener buttonActionListener) {
        this.buttonActionListener = buttonActionListener;

        this.frame = new JFrame(TITLE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        expirePanel = new ExpirePanel(buttonActionListener);
        frame.getContentPane().add(expirePanel);

        aeaPanel = new AeaPanel(buttonActionListener);
        frame.getContentPane().add(aeaPanel);

              
        
        headerPanel = new HeaderPanel(buttonActionListener);
        frame.getContentPane().add(headerPanel);

        aeatextPanel = new AeatextPanel(buttonActionListener);
        frame.getContentPane().add(aeatextPanel);

        liveMediaPanel = new LiveMediaPanel(buttonActionListener);
        frame.getContentPane().add(liveMediaPanel);

        mediaPanel = new MediaPanel(buttonActionListener);
        frame.getContentPane().add(mediaPanel);

        buttonPanel = initButtonPanel();
        frame.getContentPane().add(buttonPanel);

        frame.setSize(new Dimension(400, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //버튼 패널
    private JPanel initButtonPanel() {

        JPanel panel = new JPanel();
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(buttonActionListener);
        panel.add(loadButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(buttonActionListener);
        panel.add(saveButton);

        return panel;
    }

    public void loadAeat(AEATType aeat) {//값들을 로드
        aeaPanel.loadAeat(aeat);
        headerPanel.loadAeat(aeat);
        aeatextPanel.loadAeat(aeat);
        liveMediaPanel.loadAeat(aeat);
        mediaPanel.loadAeat(aeat);
        expirePanel.loadAeat(aeat);
        
    }

    //TextField에있는 값들을 읽어서 aeat를 반환
      public AEATType getAeat(){
        AEATType aeat = new AEATType();
        AEAType aea = new AEAType();
        
       
        //데이터를 가져오기위한 각각 타입별 변수 생성
        HeaderType header = new HeaderType();
        TypeType typeType = new TypeType();//EventCode
        LangType langType1 = new LangType();//EventDesc
        LangType langType2 = new LangType();//Aeatext
        LangType langType3 = new LangType();//LiveMedia
        LiveMediaType liveMediaType = new LiveMediaType();//LiveMedia
        MediaType mediaType = new MediaType();//media
        
        
        //각각의 패널에서 값을 가져옴
        typeType.setValue(headerPanel.getEventCode());
        
        LocationType location= new LocationType();
        location.setValue(headerPanel.getLocation1());
        langType1.setValue(headerPanel.getEventDesc());
        langType2.setValue(aeatextPanel.getAeatext());
        langType3.setValue(liveMediaPanel.getLiveMedia());
        liveMediaType.getServiceName().add(langType3);
        mediaType.setUrl(mediaPanel.getMediaUrl());//media
        mediaType.setMediaDesc(mediaPanel.getMediaDesc());
        mediaType.setAlternateUrl(mediaPanel.getAlternateUrl());
        mediaType.setMediaAssoc(mediaPanel.getMediaAssoc());
        
        //각각 aea와 header에 붙임
        aea.setAeaId(aeaPanel.getAeaId());
        aea.setIssuer(aeaPanel.getIssuer());
        aea.setAudience(AudienceType.fromValue(aeaPanel.getAudience()));
        aea.setAeaType(AEAtypeType.fromValue(aeaPanel.getAeaType()));
        aea.setRefAEAId(aeaPanel.getRefAEAId());
        aea.setPriority(aeaPanel.getPriority());
        aea.setWakeup(aeaPanel.getWakeup());
        aea.getAEAText().add(langType2);//aeaText
        aea.getMedia().add(mediaType);//Media
        aea.setLiveMedia(liveMediaType);//LiveMedia
        
        header.setEffective(stringToXMLGregorianCalendar(expirePanel.getEffective()));
        header.setExpires(stringToXMLGregorianCalendar(expirePanel.getExpire()));
        header.getLocation().add(location);//Location
        header.setEventCode(typeType);//EventCode
        header.getEventDesc().add(langType1);//EventDesc
                                 
       
        aea.setHeader(header);//header를 aea에 붙임
        aeat.getAEA().add(aea);//aea를 aeat에 붙임
        return aeat;
        
    }
      //String을 캘린더 형식으로 바꿔줌
          private XMLGregorianCalendar stringToXMLGregorianCalendar(String s) {
        XMLGregorianCalendar result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = simpleDateFormat.parse(s);
            GregorianCalendar gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (ParseException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
      
}
