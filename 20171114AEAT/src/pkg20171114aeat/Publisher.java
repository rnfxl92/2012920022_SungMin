/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20171114aeat;



import pkg20171114aeat.view.Frame;
import tag.atsc_org_2016.xmlschemas.atsc3.delivery.aeat._1.AEATType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author comkeen
 */
public class Publisher {

    private Frame frame;
    
    public static final String AEAT_EXAM = "xml/AEAT-Example-20170920.xml";
    
    public Publisher() {
        MyButtonActionListener buttonActionListener = new MyButtonActionListener(this);
        frame = new Frame(buttonActionListener);    
    }
    
    //Marshalling
    private void aeatMarshalling(AEATType aeat, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            marshaller.marshal(aeat, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    //UnMarshalling
    private AEATType aeatUnmarshalling(String path) {
        AEATType aeat = null;
        try {
            File file = new File(path);

            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(file)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }

    //load버튼을 눌렀을 때
    public void onClickedLoadButton() {
        
        AEATType aeat = aeatUnmarshalling(AEAT_EXAM);
        frame.loadAeat(aeat);
    }
    //save버튼을 눌렀을 때
    public void onClickedSaveButton() {
        AEATType aeat = frame.getAeat();
        aeatMarshalling(aeat, "xml/output.xml");
    }
}    


