/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import adt.LinkedQueue;
import boundary.StudentManagementUI;
import entity.Programme;
import entity.Registrant;
import entity.Student;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class StudentManagement {

    private static final StudentManagementUI ui = new StudentManagementUI();
    private final LinkedQueue<Registrant> registrantQueue;
    private final DoublyLinkedList<Student> studentList;
    private final DoublyLinkedList<Registrant> rejectedList;
    private final DoublyLinkedList<Student> blackList;
    private final DoublyLinkedList<Student> removedList;
    private final DoublyLinkedList<Programme> programmeList;
    private boolean regisStatus;

    public StudentManagement(LinkedQueue<Registrant> registrantQueue,
            DoublyLinkedList<Student> studentList, DoublyLinkedList<Registrant> rejectedList,
            DoublyLinkedList<Student> blackList, DoublyLinkedList<Student> removedList,
            DoublyLinkedList<Programme> programmeList) {
        this.registrantQueue = registrantQueue;
        this.studentList = studentList;
        this.rejectedList = rejectedList;
        this.blackList = blackList;
        this.removedList = removedList;
        this.programmeList = programmeList;
        this.regisStatus = true;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.studentManagementMenu()) {
                case 1 ->
                    registration();
                case 2 ->
                    aboutStudent();
                case 3 ->
                    blackList();
                case 4 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void registration() {
        boolean exit = false;
        while (!exit) {
            switch (ui.registrationMenu()) {
                case 1 ->
                    addNewRegistrant();
                case 2 ->
                    addNewStudent(false);
                case 3 ->
                    addNewStudent(true);
                case 4 ->
                    showRejectedRegistrant();
                case 5 ->
                    setRegisStatus();
                case 6 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void addNewRegistrant() {
        UI.nextSlide();
        if (regisStatus) {
            Registrant newRegistrant = ui.inputRegistrantDetails(programmeList);
            if (newRegistrant == null) {
                UI.message("Invalid new registrant.");
            } else {
                UI.nextSlide();
                if (UI.confirmUI(newRegistrant.details())) {
                    registrantQueue.enqueue(newRegistrant);
                } else {
                    UI.message("Add new registrant cancelled.");
                }
            }
        } else {
            ui.showRegisStatus(regisStatus);
        }

    }

    private void addNewStudent(boolean manual) {
        Student newStudent;
        if (!manual) {
            do {
                if (!registrantQueue.isEmpty()) {
                    Registrant commingRegistrant = registrantQueue.dequeue();
                    newStudent = ui.approveRegistrant(commingRegistrant);
                    if (newStudent != null) {
                        studentList.add(newStudent);
                    } else {
                        rejectedList.add(commingRegistrant);
                    }
                } else {
                    UI.message("No registrant in the system.");
                    break;
                }
            } while (ui.nextStudent());

        } else {
            UI.nextSlide();
            Registrant newRegistrant = ui.inputRegistrantDetails(programmeList);
            if (newRegistrant == null) {
                UI.message("Invalid new registrant.");
            } else {
                UI.nextSlide();
                if (UI.confirmUI(newRegistrant.details())) {
                    newStudent = new Student(newRegistrant);
                    UI.nextSlide();
                    System.out.print(newStudent.details());
                    studentList.add(newStudent);
                } else {
                    UI.message("Add new student cancelled.");
                }
            }
        }
    }

    private void showRejectedRegistrant() {
        if (!rejectedList.isEmpty()) {
            ui.showRejectedRegistrant(rejectedList);
        } else {
            UI.message("No rejected registrant in the system.");
        }
    }

    private void setRegisStatus() {
        regisStatus = ui.setToReject(regisStatus);
    }

    private void aboutStudent() {
        boolean exit = false;
        while (!exit) {
            switch (ui.aboutStudentMenu()) {
                case 1 ->
                    showStudentList();
                case 2 ->
                    amendStudentDetails();
                case 3 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void showStudentList() {
        if (!studentList.isEmpty()) {
            ui.showStudentList(studentList);
        } else {
            UI.message("No student in the system.");
        }
        UI.exit();
    }

    private void amendStudentDetails() {
        Student getStudent = ui.getStudent(studentList);
        if (getStudent != null) {
            if (ui.amendStudentDetails(getStudent)) {
                UI.message("Student amend successful.");
            } else {
                UI.message("Student amend cancelled.");
            }
        } else {
            UI.message("Student ID not found.");
        }

    }

    private void blackList() {
        boolean exit = false;
        while (!exit) {
            switch (ui.blackList()) {
                case 1 ->
                    showBlackList();
                case 2 ->
                    addToBlackList();
                case 3 ->
                    removeFromBlackList();
                case 4 ->
                    removeStudent();
                case 5 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void showBlackList() {
        if (!blackList.isEmpty()) {
            ui.showBlackList(blackList);
        } else {
            UI.message("No student in blacklist.");
        }

    }

    private void addToBlackList() {
        UI.nextSlide();
        // show fail to pay student list, and remove student from student list
        Student blackListStudent = ui.addToBlackList(studentList);
        if (blackListStudent != null) {
            UI.nextSlide();
            if (UI.confirmUI(blackListStudent.details())) {
                blackList.add(blackListStudent);
                studentList.remove(studentList.positionOf(blackListStudent));
            } else {
                UI.message("Add student to blacklist cancelled.");
            }
        } else {
            UI.message("Student ID not found.");
        }
    }

    private void removeFromBlackList() {
        if (!blackList.isEmpty()) {
            Student blackListStudent = ui.getStudent(blackList);
            if (blackListStudent != null) {
                UI.nextSlide();
                if (UI.confirmUI(blackListStudent.details())) {
                    studentList.add(blackListStudent);
                    blackList.remove(blackList.positionOf(blackListStudent));
                } else {
                    UI.message("Student move cancelled.");
                }
            } else {
                UI.message("Student ID not found.");
            }
        } else {
            UI.message("No student in blacklist.");
        }
    }

    private void removeStudent() {
        // move student to removed student list
        if (!blackList.isEmpty()) {
            Student blackListStudent = ui.getStudent(blackList);
            if (blackListStudent != null) {
                UI.nextSlide();
                if (UI.confirmUI(blackListStudent.details())) {
                    removedList.add(blackListStudent);
                    blackList.remove(blackList.positionOf(blackListStudent));
                } else {
                    UI.message("Student remove cancelled.");
                }
            } else {
                UI.message("Student ID not found.");
            }
        } else {
            UI.message("No student in blacklist.");
        }
    }
}
