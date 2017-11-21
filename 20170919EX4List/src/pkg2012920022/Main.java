/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2012920022;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 216-03
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //인터페이스는 객체는 생성할 수 없다.
        //List: interface 이름
        //ArrayList: 구현 Class이름
        List<String> list = new ArrayList();
//        ArrayList<String> alist = new ArrayList();
//        List<String> llist = new LinkedList();

        //     llist.add("my god");
        for (int i = 0; i < 10; i++) {
            list.add("hello" + i);
        }
        for (int i = 0; i < 10; i++) {
           System.out.println(list.get(i));
        }
        //1.리스트와 연관된 iterator를 얻는다.
        Iterator itr = list.iterator();
        while(itr.hasNext()){//리스트에 찾지 않은 요소가 있나?
            System.out.println(itr.next());//요소를 반환해! 이후 화살표를 다음으로 가리켜
        }

    }

}
