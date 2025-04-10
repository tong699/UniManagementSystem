/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.TutorTutorialGrpAssociation;

/**
 *
 * @author Kong Jia Le
 */
public class TutorTutGroupInitializer {

    public DoublyLinkedList<TutorTutorialGrpAssociation> initializeTutorTutGroup() {
        DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList = new DoublyLinkedList<>();
        tutorialGrpsTutorList.add(new TutorTutorialGrpAssociation("G0001", "T0001"));
        tutorialGrpsTutorList.add(new TutorTutorialGrpAssociation("G0002", "T0002"));
        tutorialGrpsTutorList.add(new TutorTutorialGrpAssociation("G0003", "T0003"));
        tutorialGrpsTutorList.add(new TutorTutorialGrpAssociation("G0005", "T0005"));

        return tutorialGrpsTutorList;
    }

}
