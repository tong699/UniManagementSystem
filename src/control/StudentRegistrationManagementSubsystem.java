/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import adt.LinkedQueue;
import boundary.StudentRegistrationManagementUI;
import entity.Course;
import entity.Programme;
import entity.ProgrammeCourseAssociation;
import entity.Registrant;
import entity.Student;
import entity.StudentCourseAssociation;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class StudentRegistrationManagementSubsystem {

    private static final StudentRegistrationManagementUI ui = new StudentRegistrationManagementUI();
    private final StudentManagement studentManagement;
    private final StudentCoursesManagement studentCoursesManagement;
    private final StudentRegistrationManagementReport report;

    public StudentRegistrationManagementSubsystem(LinkedQueue<Registrant> registrantQueue, 
            DoublyLinkedList<Student> studentList, DoublyLinkedList<Course> courseList, 
            DoublyLinkedList<Registrant> rejectedList, DoublyLinkedList<Student> blackList,
            DoublyLinkedList<Student> removedList, DoublyLinkedList<StudentCourseAssociation> studentCourseList,
            DoublyLinkedList<StudentCourseAssociation> studentCourseHistory,
            DoublyLinkedList<ProgrammeCourseAssociation> programmeCourseList,
            DoublyLinkedList<Programme> programmeList) {
        studentManagement = new StudentManagement(
                registrantQueue, studentList, rejectedList, blackList, removedList, programmeList);
        studentCoursesManagement = new StudentCoursesManagement(
                studentList, courseList, studentCourseList, studentCourseHistory, programmeCourseList);
        report = new StudentRegistrationManagementReport(
                studentList, rejectedList, courseList, studentCourseList);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.studentRegistrationManagementMenu()) {
                case 1 ->
                    studentManagement.run();
                case 2 ->
                    studentCoursesManagement.run();
                case 3 ->
                    report.run();
                case 4 ->
                    exit = true;
            }
        }
        UI.exit();
    }
}
