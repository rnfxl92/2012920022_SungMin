/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbmaptest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author comkeen
 */
public class JaxbTest {

    public JaxbTest() {
        System.out.println("Marshalling:");
        testMarshalling();

        System.out.println("\nUnmarshalling:");
        testUnmarshalling();
    }

    private void testMarshalling() {
        String path = "xml/dictionary.xml";
        try {
            File file = new File("xml/Dictionary.xml");

            //Unmarshalling을 위한 Unmashaller 생성 및 초기화
            JAXBContext jaxbContext = JAXBContext.newInstance(Dictionary.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            //Unmarshaller를 이용해 XML파일로 부터 Dictionarty 객체 생성
            Dictionary dictionary = (Dictionary) jaxbUnmarshaller.unmarshal(file);
            //Dictionary dictionary = initDictionary();//HashMap 초기화 및 엔트리 생성 후 반환
            dictionary.getWords().put("test","테스트");
            //Marshalling을 위한 Mashaller 생성 및 초기화
            //JAXBContext jaxbContext = JAXBContext.newInstance(Dictionary.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//XML 띄어쓰기 포맷 설정
            
            marshaller.marshal(dictionary, System.out);//생성된 XML프린트
            marshaller.marshal(dictionary, new File(path));// 생성된 XML파일 저장
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void testUnmarshalling() {
        try {
            //자바 객체로 변환하려는 XML 파일 객체 생성
            File file = new File("xml/Dictionary.xml");

            //Unmarshalling을 위한 Unmashaller 생성 및 초기화
            JAXBContext jaxbContext = JAXBContext.newInstance(Dictionary.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            //Unmarshaller를 이용해 XML파일로 부터 Dictionarty 객체 생성
            Dictionary dic = (Dictionary) jaxbUnmarshaller.unmarshal(file);
            
            //생성된 Dictionary 객체 엔트리들을 foreach 구문으로 프린트
            for(Map.Entry<String, String> entry : dic.getWords().entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    //XML로 변환할 java 객체, Dictionary 객체 초기화 메소드
    private Dictionary initDictionary() {
        Dictionary dictionary = new Dictionary(); //Dictionary 객체 생성
        dictionary.setWords(new HashMap<>());// Dictionary 객체의 맴버변수를 HashMap으로 초기화 tree맵으로 하자.
        //Dictionary HashMap에 엔트리 추가
        dictionary.getWords().put("이히", "테스트");
        dictionary.getWords().put("banana", "바나나");
        dictionary.getWords().put("case", "케이스");
        dictionary.getWords().put("apple", "사과");
        return dictionary;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new JaxbTest();
    }
}
