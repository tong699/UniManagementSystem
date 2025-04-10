/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.TutorialGrpCourseAssociation;

/**
 *
 * @author Kong Jia Le
 */
public class TutGrpCourseInitializer {

    public DoublyLinkedList<TutorialGrpCourseAssociation> initializeTutGrpCourse() {
        DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList = new DoublyLinkedList<>();
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0001", "BACS1111", null));
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0001", "BAET4444", null));
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0002", "BACS3333", null));
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0002", "BAET4444", null));
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0003", "BACS6666", null));
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0003", "BAET6666", null));
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0004", "BACS2222", null));
        tutorialGrpCourseList.add(new TutorialGrpCourseAssociation("G0004", "BAET4444", null));
        
        return tutorialGrpCourseList;
    }

}
