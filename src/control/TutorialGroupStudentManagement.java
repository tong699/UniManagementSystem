/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import adt.LinkedQueue;
import boundary.TutorialGroupStudentManagementUI;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import utility.UI;

/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroupStudentManagement {
    
    public static final TutorialGroupStudentManagementUI ui
            = new TutorialGroupStudentManagementUI();
    private final DoublyLinkedList<Student> studentList;
    private final DoublyLinkedList<TutorialGroup> tutorialGroupList;
    private final DoublyLinkedList<Programme> programmeList;
    private final DoublyLinkedList<Student> waitingListHistory;
    
    public TutorialGroupStudentManagement(DoublyLinkedList<Student> studentList,
            DoublyLinkedList<TutorialGroup> tutorialGroupList,
            DoublyLinkedList<Programme> programmeList,
            DoublyLinkedList<Student> waitingListHistory) {
        this.studentList = studentList;
        this.tutorialGroupList = tutorialGroupList;
        this.programmeList = programmeList;
        this.waitingListHistory = waitingListHistory;
    }
    
    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.tutorialGroupStudentManagementMenu()) {
                case 1 ->
                    addStudentToTutorialGroup();
                case 2 ->
                    removeStudentFromTutorialGroup();
                case 3 ->
                    changeTutorialGroupForStudent();
                case 4 ->
                    listAllStudentsInTutorialGroup();
                case 5 ->
                    searchStudentInTutorialGroup();
                case 6 ->
                    manageWaitingList();
                case 7 ->
                    exit = true;
            }
            // Add more functionalities as needed
        }
        UI.exit();
    }
    
    private void addStudentToTutorialGroup() {
        // Implement logic to add a student to a tutorial group
        UI.nextSlide();
        UI.title("Add Student To Tutorial Group");
        Programme selectedProgramme = ui.getProgramme(programmeList);
        if (selectedProgramme != null) {
            TutorialGroup selectedGroup
                    = ui.getTutorialGroup(
                            selectedProgramme.getProgrammeId(),
                            selectedProgramme.
                                    getTutorialGroupList());
            if (selectedGroup != null) {
                if (!selectedProgramme.getWaitingListQueue().isEmpty()) {
                    do {
                        Student studentToAdd
                                = selectedProgramme.getWaitingListQueue()
                                        .peek();
                        
                        if (studentToAdd != null) {
                            
                            if (UI.confirmUI(studentToAdd.details()
                                    + studentToAdd.getName()
                                    + " will be added to tutorial group "
                                    + selectedGroup.getGroupId())) {
                                selectedProgramme.getWaitingListQueue()
                                        .dequeue();
                                selectedGroup.getEnrolledStudents()
                                        .add(studentToAdd);
                                System.out.println("Student added successfully "
                                        + "to tutorial group "
                                        + selectedGroup.getGroupId()
                                        + " Of Programme "
                                        + selectedProgramme.getProgrammeId());
                            } else {
                                UI.message("Adding student to tutorial group "
                                        + "is cancelled.");
                            }
                        } else {
                            System.out.println("Student not found.");
                            break;
                        }
                    } while (ui.nextStudent());
                } else {
                    UI.message("Waiting list queue is empty.");
                }
            } else {
                UI.message("Tutorial Group not found.");
            }
        } else {
            UI.message("Programme not found.");
        }
    }
    
    private void removeStudentFromTutorialGroup() {
        UI.nextSlide();
        UI.title("Remove Student From Tutorial Group");
        Programme selectedProgramme = ui.getProgramme(programmeList);
        if (selectedProgramme != null) {
            TutorialGroup selectedGroup
                    = ui.getTutorialGroup(
                            selectedProgramme.getProgrammeId(),
                            selectedProgramme
                                    .getTutorialGroupList());
            if (selectedGroup != null) {
                DoublyLinkedList<Student> selectedStudentList
                        = selectedGroup.getEnrolledStudents();
                ui.showStudentList(selectedStudentList);
                Student studentToRemove
                        = ui.getStudent(selectedStudentList);
                if (studentToRemove != null) {
                    if (UI.confirmUI(studentToRemove.details()
                            + studentToRemove.getName()
                            + " will be removed from tutorial group "
                            + selectedGroup.getGroupId())) {
                        
                        selectedGroup.getEnrolledStudents()
                                .remove(selectedStudentList
                                        .positionOf(studentToRemove));
                        System.out.println("Student is removed successfully "
                                + "from tutorial group "
                                + selectedGroup.getGroupId()
                                + " Of Programme "
                                + selectedProgramme.getProgrammeId());
                    } else {
                        UI.message("Removing Student from tutorial group is "
                                + "cancelled.");
                    }
                } else {
                    UI.message("Student not found.");
                }
            } else {
                UI.message("Tutorial Group not found.");
            }
        } else {
            UI.message("Programme not found.");
        }
    }
    
    private void changeTutorialGroupForStudent() {
        UI.nextSlide();
        UI.title("Change Tutorial Group For Student");
        Programme selectedProgramme = ui.getProgramme(programmeList);
        if (selectedProgramme != null) {
            TutorialGroup selectedGroup1
                    = ui.getTutorialGroup(
                            selectedProgramme.getProgrammeId(),
                            selectedProgramme
                                    .getTutorialGroupList());
            if (selectedGroup1 != null) {
                DoublyLinkedList<Student> selectedStudentList
                        = selectedGroup1.getEnrolledStudents();
                Student studentToChange
                        = ui.getStudent(selectedStudentList);
                if (studentToChange != null) {
                    System.out.println("Change to : ");
                    TutorialGroup selectedGroup2
                            = ui.getTutorialGroup(
                                    selectedProgramme.getProgrammeId(),
                                    selectedProgramme
                                            .getTutorialGroupList());
                    if (selectedGroup2 != null) {
                        if (selectedGroup2.getEnrolledStudents().size()
                                < selectedGroup2.getCapacity()) {
                            
                            if (UI.confirmUI(studentToChange.details()
                                    + studentToChange.getName()
                                    + " will be changed from tutorial group "
                                    + selectedGroup1.getGroupId() + " to "
                                    + selectedGroup2.getGroupId())) {
                                selectedGroup2.getEnrolledStudents()
                                        .add(studentToChange);
                                selectedGroup1.getEnrolledStudents()
                                        .remove(selectedStudentList
                                                .positionOf(
                                                        studentToChange));
                            } else {
                                UI.message("Changing tutorial groups for "
                                        + "student is cancelled.");
                            }
                        } else {
                            UI.message("Out of capacity. ");
                        }
                    } else {
                        UI.message("Tutorial Group not found.");
                    }
                } else {
                    UI.message("Student not found.");
                }
            } else {
            }
        } else {
            UI.message("Programme not found.");
        }
    }
    
    private void listAllStudentsInTutorialGroup() {
        UI.nextSlide();
        UI.title("List All Students");
        Programme selectedProgramme = ui.getProgramme(programmeList);
        if (selectedProgramme != null) {
            TutorialGroup selectedGroup1
                    = ui.getTutorialGroup(
                            selectedProgramme.getProgrammeId(),
                            selectedProgramme
                                    .getTutorialGroupList());
            if (selectedGroup1 != null) {
                // Prompt user to select a student to add
                DoublyLinkedList<Student> selectedStudentList
                        = selectedGroup1.getEnrolledStudents();
                if (!selectedStudentList.isEmpty()) {
                    ui.showStudentList(selectedStudentList);
                    UI.pressToCont();
                } else {
                    UI.message("Student not found.");
                }
            } else {
                UI.message("Tutorial Group not found.");
            }
        } else {
            UI.message("Programme not found.");
        }
    }
    
    private void searchStudentInTutorialGroup() {
        UI.nextSlide();
        UI.title("Search Student");
        Programme selectedProgramme = ui.getProgramme(programmeList);
        if (selectedProgramme != null) {
            TutorialGroup selectedGroup1
                    = ui.getTutorialGroup(
                            selectedProgramme.getProgrammeId(),
                            selectedProgramme
                                    .getTutorialGroupList());
            if (selectedGroup1 != null) {
                DoublyLinkedList<Student> selectedStudentList
                        = selectedGroup1.getEnrolledStudents();
                Student student = ui.getStudent(selectedStudentList);
                if (student != null) {
                    System.out.println(student.details());
                    UI.pressToCont();
                } else {
                    UI.message("Student not found.");
                }
            } else {
                UI.message("Tutorial Group not found.");
            }
        } else {
            UI.message("Programme not found.");
        }
    }
    
    private void manageWaitingList() {
        UI.nextSlide();
        boolean exit = false;
        while (!exit) {
            switch (ui.waitingListMenu()) {
                case 1 -> {
                    UI.nextSlide();
                    UI.title("Add Students To Waiting List Queue");
                    Programme programme = ui.getProgramme(programmeList);
                    if (programme != null) {
                        if (!studentList.isEmpty()) {
                            DoublyLinkedList<Student> eligibleStudents
                                    = new DoublyLinkedList<>();
                            int noOfStudentAdd = 0;
                            for (Student student : studentList) {
                                if (student.getChosenProgramme()
                                        .equals(programme
                                                .getProgrammeId())) {
                                    eligibleStudents.add(student);
                                }
                            }
                            for (Student student : eligibleStudents) {
                                if (!waitingListHistory
                                        .contains(student)) {
                                    if (UI.confirmUI(student.details()
                                            + student.getName()
                                            + " will be added to waiting list "
                                            + "queue.")) {
                                        programme.getWaitingListQueue()
                                                .enqueue(student);
                                        waitingListHistory.add(student);
                                        noOfStudentAdd++;
                                        System.out.println("Student added to "
                                                + "waiting list.");
                                        if (!ui.nextStudent()) {
                                            break;
                                        }
                                    } else {
                                        UI.message("Action cancelled.");
                                    }
                                }
                            }
                            
                            if (noOfStudentAdd == 0) {
                                UI.message("Student Not found.");
                            }
                        } else {
                            UI.message("Students not found.");
                        }
                    } else {
                        UI.message("Programme not found.");
                    }
                }
                case 2 -> {
                    UI.nextSlide();
                    UI.title("View Waiting List Queue");
                    Programme programme = ui.getProgramme(programmeList);
                    
                    if (programme != null) {
                        LinkedQueue<Student> queue
                                = programme.getWaitingListQueue();
                        if (queue.size() > 0) {
                            System.out.println("Waiting list queue: "
                                    + queue.size());
                        } else {
                            UI.message(" No Students are in the queue.");
                        }
                    } else {
                        UI.message("Programme Not Found.F");
                    }
                    
                }
                case 3 ->
                    exit = true;
            }
        }
    }
}
