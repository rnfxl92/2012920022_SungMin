/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg2012920022;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author comkeen
 */
//Root Element에는 반드시 @XmlRootElement 어노테이션 명시
@XmlRootElement
public class Dictionary {

    private Map<String, String> words;

    public Dictionary() {}
    //XML 엘리먼트는 반드시 getter , setter 메소드를 정의해야한다.
    public Map<String, String> getWords() {
        return this.words;
    }
    
    public void setWords(Map<String, String> words) {
        this.words = words;
    }

    Object getWord() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

