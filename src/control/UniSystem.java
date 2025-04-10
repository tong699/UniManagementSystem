/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import adt.LinkedQueue;
import boundary.UniSystemUI;
import entity.*;
import utility.UI;

/**
 *
 * @author Team
 */
public class UniSystem {

    private static final UniSystemUI ui = new UniSystemUI();
    private final StudentRegistrationManagementSubsystem srms;
    private final ProgrammeCourseSubsystem pcs;
    private final TutorialGroupManagementSubsystem tgms;
    private final TeachingAssignmentSubsystem tas;

    public UniSystem(LinkedQueue<Registrant> registrantQueue, DoublyLinkedList<Student> studentList, DoublyLinkedList<Course> courseList, DoublyLinkedList<Registrant> rejectedList, DoublyLinkedList<Student> blackList, DoublyLinkedList<Student> removedList, DoublyLinkedList<StudentCourseAssociation> studentCourseList, DoublyLinkedList<StudentCourseAssociation> studentCourseHistory,
            DoublyLinkedList<Programme> programmeList, DoublyLinkedList<ProgrammeCourseAssociation> programmeCourseList, DoublyLinkedList<Faculty> facultyList, DoublyLinkedList<TutorialGroup> tutorialGroupList,DoublyLinkedList<Student> waitingListHistory, DoublyLinkedList<Tutor> tutorList, DoublyLinkedList<TutorCourseAssociation> tutorCourseList,
            DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList, DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList) {

        this.srms = new StudentRegistrationManagementSubsystem(registrantQueue, studentList, courseList, rejectedList, blackList, removedList, studentCourseList, studentCourseHistory, programmeCourseList, programmeList);
        this.pcs = new ProgrammeCourseSubsystem(facultyList);
        this.tgms = new TutorialGroupManagementSubsystem(studentList, tutorialGroupList, programmeList,waitingListHistory);
        this.tas = new TeachingAssignmentSubsystem(tutorList, tutorCourseList, tutorialGrpCourseList, tutorialGrpsTutorList, tutorialGroupList, courseList);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.uniSystemMenu()) {
                case 1 ->
                    srms.run();
                case 2 ->
                    pcs.run();
                case 3 ->
                    tgms.run();
                case 4 ->
                    tas.run();
                case 5 ->
                    exit = true;
            }
        }
        UI.exit();
    }
}
