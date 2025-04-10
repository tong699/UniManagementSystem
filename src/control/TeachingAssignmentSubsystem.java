/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.TeachingAssignmentUI;
import entity.Course;
import entity.Tutor;
import entity.TutorCourseAssociation;
import entity.TutorTutorialGrpAssociation;
import entity.TutorialGroup;
import entity.TutorialGrpCourseAssociation;
import utility.UI;


/**
 *
 * @author Chan Wei Xin
 */
public class TeachingAssignmentSubsystem {
    private static final TeachingAssignmentUI ui = new TeachingAssignmentUI();
    private final CourseTutorManagement courseTutorManagement;
    private final TutorManagement tutorManagement;
    private final TeachingAssignmentReport teachingAssignmentReport;

    
    public TeachingAssignmentSubsystem( DoublyLinkedList<Tutor> tutorList, DoublyLinkedList<TutorCourseAssociation> tutorCourseList,DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList,  DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList,DoublyLinkedList<TutorialGroup> tutorialGroupList, DoublyLinkedList<Course> courseList){
        courseTutorManagement = new CourseTutorManagement(tutorList, tutorCourseList, tutorialGrpCourseList, tutorialGroupList, courseList);
        tutorManagement = new TutorManagement(tutorList, tutorialGroupList, tutorialGrpsTutorList, tutorCourseList);
        teachingAssignmentReport = new TeachingAssignmentReport(tutorList, tutorCourseList, tutorialGrpsTutorList);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.TeachingAssignmentMenu()) {
                case 1 ->
                    tutorManagement.run();
                case 2 ->
                    courseTutorManagement.run();
                case 3 ->
                    teachingAssignmentReport.run();
                case 4 ->
                    exit = true;
            }
        }
        UI.exit();
    }
}
