/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tester;

import adt.DoublyLinkedList;
import adt.LinkedQueue;
import control.UniSystem;
import dao.*;
import entity.*;

/**
 *
 * @author Team
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Kong Jia Le
        RegistrantInitializer initialRegistrant = new RegistrantInitializer();
        LinkedQueue<Registrant> registrantQueue = initialRegistrant.initializeRegistrant();
        StudentInitializer initialStudent = new StudentInitializer();
        DoublyLinkedList<Student> studentList = initialStudent.initializeStudent(registrantQueue);
        DoublyLinkedList<Registrant> rejectedList = new DoublyLinkedList<>();
        DoublyLinkedList<Student> blackList = new DoublyLinkedList<>();
        DoublyLinkedList<Student> removedList = new DoublyLinkedList<>();
        StudentCourseInitializer initialStudentCourse = new StudentCourseInitializer();
        DoublyLinkedList<StudentCourseAssociation> studentCourseList = initialStudentCourse.initializeStudentCourse();
        DoublyLinkedList<StudentCourseAssociation> studentCourseHistory = new DoublyLinkedList<>();

        // Jerry Tay Kien Hui
        FacultyInitializer initialFaculty = new FacultyInitializer();
        DoublyLinkedList<Faculty> facultyList = initialFaculty.initializeFaculty();
        DoublyLinkedList<Course> courseList = new DoublyLinkedList<>();
        DoublyLinkedList<Programme> programmeList = new DoublyLinkedList<>();
        DoublyLinkedList<ProgrammeCourseAssociation> programmeCourseList = new DoublyLinkedList<>();
        for (Faculty e : facultyList) {
            courseList = DoublyLinkedList.combine(courseList, e.getCourseList());
            programmeList = DoublyLinkedList.combine(programmeList, e.getProgrammeList());
            programmeCourseList = DoublyLinkedList.combine(programmeCourseList, e.getProgrammeCourseList());
        }

        // Tong Chun Mun
        TutGroupInitializer initialTutGroup = new TutGroupInitializer();
        DoublyLinkedList<TutorialGroup> tutorialGroupList = initialTutGroup.initializeTutGroup(programmeList, studentList);
        DoublyLinkedList<Student> waitingListHistory = new DoublyLinkedList<>();

        // Chan Wei Xin
        TutorInitializer initialTutor = new TutorInitializer();
        DoublyLinkedList<Tutor> tutorList = initialTutor.initializeTutor();
        TutorCourseInitializer initialTutorCourse = new TutorCourseInitializer();
        DoublyLinkedList<TutorCourseAssociation> tutorCourseList = initialTutorCourse.initializeTutorCourse();
        TutGrpCourseInitializer initialTutGrpCourse = new TutGrpCourseInitializer();
        DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList = initialTutGrpCourse.initializeTutGrpCourse();
        TutorTutGroupInitializer initialTutorTutGroup = new TutorTutGroupInitializer();
        DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList = initialTutorTutGroup.initializeTutorTutGroup();

        UniSystem uniSystem = new UniSystem(registrantQueue, studentList, courseList, rejectedList, blackList, removedList, studentCourseList, studentCourseHistory,
                programmeList, programmeCourseList, facultyList, tutorialGroupList, waitingListHistory, tutorList, tutorCourseList, tutorialGrpCourseList, tutorialGrpsTutorList);

        uniSystem.run();
    }

}
