/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.TutorialGroupManagementUI;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import utility.UI;

/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroupManagementSubsystem {

    private static final TutorialGroupManagementUI ui = new TutorialGroupManagementUI();
    private final TutorialGroupAdministration tutorialGroupAdministration;
    private final TutorialGroupStudentManagement tutorialGroupStudentManagement;
    private final TutorialGroupManagementReport tutorialGroupManagementReport;

    public TutorialGroupManagementSubsystem(DoublyLinkedList<Student> studentList, DoublyLinkedList<TutorialGroup> tutorialGroupList, DoublyLinkedList<Programme> programmeList,DoublyLinkedList<Student> waitingListHistory) {
        tutorialGroupAdministration = new TutorialGroupAdministration(tutorialGroupList, programmeList);
        tutorialGroupStudentManagement = new TutorialGroupStudentManagement(studentList, tutorialGroupList, programmeList,waitingListHistory);
        tutorialGroupManagementReport = new TutorialGroupManagementReport(tutorialGroupList, programmeList);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.tutorialGroupManagementMenu()) {
                case 1 ->
                    tutorialGroupAdministration.run();
                case 2 ->
                    tutorialGroupStudentManagement.run();
                case 3 ->
                    tutorialGroupManagementReport.run();
                case 4 ->
                    exit = true;
            }
        }
        UI.exit();
    }
}
