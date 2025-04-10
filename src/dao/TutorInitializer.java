/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.Tutor;

/**
 *
 * @author Kong Jia Le
 */
public class TutorInitializer {

    public DoublyLinkedList<Tutor> initializeTutor() {
        DoublyLinkedList<Tutor> tutorList = new DoublyLinkedList<>();
        tutorList.add(new Tutor(null, "CHAN WEI KIEN", 30, "M", "DEGREE"));
        tutorList.add(new Tutor(null, "CHONG WEI LE", 40, "M", "DEGREE"));
        tutorList.add(new Tutor(null, "KONG JIA XIN", 25, "M", "MASTER"));
        tutorList.add(new Tutor(null, "JEERY TAY KIEN MUN", 35, "F", "DOCTORATE"));
        tutorList.add(new Tutor(null, "YAP JIE YING", 30, "F", "DOCTORATE"));
        tutorList.add(new Tutor(null, "WONG JIA MEI", 50, "F", "MASTER"));
        
        return tutorList;
    }

}
