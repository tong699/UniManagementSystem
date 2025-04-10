/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.TutorCourseAssociation;

/**
 *
 * @author Kong Jia Le
 */
public class TutorCourseInitializer {

    public DoublyLinkedList<TutorCourseAssociation> initializeTutorCourse() {
        DoublyLinkedList<TutorCourseAssociation> tutorCourseList = new DoublyLinkedList<>();
        tutorCourseList.add(new TutorCourseAssociation("T0001", "BACS1111", "TUTORIAL"));
        tutorCourseList.add(new TutorCourseAssociation("T0001", "BAET1111", "PRACTICAL"));
        tutorCourseList.add(new TutorCourseAssociation("T0001", "BAET4444", "LECTURE"));
        tutorCourseList.add(new TutorCourseAssociation("T0002", "BACS1111", "TUTORIAL"));
        tutorCourseList.add(new TutorCourseAssociation("T0002", "BAET2222", "PRACTICAL"));
        tutorCourseList.add(new TutorCourseAssociation("T0002", "BACS6666", "LECTURE"));
        tutorCourseList.add(new TutorCourseAssociation("T0003", "BAET4444", "PRACTICAL"));
        tutorCourseList.add(new TutorCourseAssociation("T0004", "BACS3333", "LECTURE"));
        tutorCourseList.add(new TutorCourseAssociation("T0004", "BAET1111", "PRACTICAL"));
        tutorCourseList.add(new TutorCourseAssociation("T0005", "BACS5555", "PRACTICAL"));
        tutorCourseList.add(new TutorCourseAssociation("T0005", "BACS5555", "LECTURE"));
        tutorCourseList.add(new TutorCourseAssociation("T0006", "BACS5555", "LECTURE"));

        return tutorCourseList;
    }

}
