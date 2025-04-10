/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.TeachingAssignmentReportUI;
import entity.Tutor;
import entity.TutorCourseAssociation;
import entity.TutorTutorialGrpAssociation;

import utility.UI;

/**
 *
 * @author Chan Wei Xin
 */
public class TeachingAssignmentReport {

    private static final TeachingAssignmentReportUI ui = new TeachingAssignmentReportUI();
    private final DoublyLinkedList<Tutor> tutorList;
    private final DoublyLinkedList<TutorCourseAssociation> tutorCourseList;
    private final DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList;
    

    public TeachingAssignmentReport(DoublyLinkedList<Tutor> tutorList, DoublyLinkedList<TutorCourseAssociation> tutorCourseList, DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList) {
        this.tutorList = tutorList;
        this.tutorCourseList = tutorCourseList;
        this.tutorialGrpsTutorList =  tutorialGrpsTutorList;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.TeachingAssignmentReportUIMenu()) {
                case 1 ->
                    generateTutorCourseAssociationSummaryReport();
                case 2 ->
                    generateTutorPerformanceReport();
                case 3 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    public void generateTutorCourseAssociationSummaryReport() {
        ui.displayTutorCourseAssociationSummary(tutorList, tutorCourseList);
        UI.pressToCont();
    }
    
    public void generateTutorPerformanceReport(){
        ui.displayTutorPerformanceReport(tutorList, tutorCourseList, tutorialGrpsTutorList);
        UI.pressToCont();
    }

}
