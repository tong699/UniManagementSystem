/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;

/**
 *
 * @author Kong Jia Le
 */
public class TutGroupInitializer {

    public DoublyLinkedList<TutorialGroup> initializeTutGroup(DoublyLinkedList<Programme> programmeList, DoublyLinkedList<Student> studentList) {
        DoublyLinkedList<TutorialGroup> tutorialGroupList = new DoublyLinkedList<>();

        TutorialGroup tutorialGroup1 = new TutorialGroup("RDS");
        tutorialGroup1.getEnrolledStudents().add(studentList.get(0));
        tutorialGroup1.getEnrolledStudents().add(studentList.get(1));

        TutorialGroup tutorialGroup2 = new TutorialGroup("RSW");
        
        tutorialGroup2.getEnrolledStudents().add(studentList.get(2));
        TutorialGroup tutorialGroup3 = new TutorialGroup("REE");
        TutorialGroup tutorialGroup4 = new TutorialGroup("RME");

        programmeList.get(0).getTutorialGroupList().add(tutorialGroup1);
        programmeList.get(1).getTutorialGroupList().add(tutorialGroup2);
        programmeList.get(2).getTutorialGroupList().add(tutorialGroup3);
        programmeList.get(3).getTutorialGroupList().add(tutorialGroup4);
        
        
        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);
        tutorialGroupList.add(tutorialGroup4);

        return tutorialGroupList;
    }

}
