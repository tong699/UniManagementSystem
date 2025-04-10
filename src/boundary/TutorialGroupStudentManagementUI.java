/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.DoublyLinkedList;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.util.Scanner;
import utility.Menu;
import utility.UI;

/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroupStudentManagementUI {

    private final Menu menu = new Menu("Tutorial Group Student Management");
    private final Menu subMenu = new Menu("Waiting List Queue Management");

    public TutorialGroupStudentManagementUI() {
        menu.add("Add Student to Tutorial Group");
        menu.add("Remove Student from Tutorial Group");
        menu.add("Change Tutorial Group for Student");
        menu.add("List All Students in Tutorial Group");
        menu.add("Search Student in Tutorial Group");
        menu.add("Manage Waiting List Queue");
        menu.add("Exit");

        subMenu.add("Add Students to Waiting List Queue");
        subMenu.add("View Waiting List Queue");
        subMenu.add("Exit");
        // Add submenu options if needed
    }

    public int tutorialGroupStudentManagementMenu() {
        return menu.displayAndGetChoice();
    }

    public int waitingListMenu() {
        return subMenu.displayAndGetChoice();
    }

    public boolean nextStudent() {
        System.out.print("Continue? (Y/Else): ");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim().toUpperCase();
        return confirm.equals("Y");
    }

    public Programme getProgramme(DoublyLinkedList<Programme> programmeList) {
        Scanner scanner = new Scanner(System.in);
        Programme s = null;
        System.out.print("Enter Programme ID (eg. RDS) : ");
        String programmeId = scanner.nextLine().trim().toUpperCase();
        if (!programmeList.isEmpty()) {
            for (Programme e : programmeList) {
                if (e.getProgrammeId().equals(programmeId)) {
                    s = e;
                }
            }
        }
        return s;
    }

    public TutorialGroup getTutorialGroup(String programmeId, DoublyLinkedList<TutorialGroup> tutorialGroupList) {
        Scanner scanner = new Scanner(System.in);
        TutorialGroup s = null;
        System.out.print("Enter Group ID (eg. G0001) : ");
        String groupId = scanner.nextLine().trim().toUpperCase();
        if (!tutorialGroupList.isEmpty()) {
            for (TutorialGroup e : tutorialGroupList) {
                if (e.getGroupId().equals(groupId)) {
                    s = e;
                }
            }
        }
        return s;
    }

    public Student getStudent(DoublyLinkedList<Student> studentList) {
        Scanner scanner = new Scanner(System.in);
        Student s = null;
        System.out.print("Enter Student ID (eg. S000001): ");
        String studentId = scanner.nextLine().trim().toUpperCase();
        if (!studentList.isEmpty()) {
            for (Student e : studentList) {
                if (e.getStudentId().equals(studentId)) {
                    s = e;
                }
            }
        }
        return s;
    }

    public void showStudentList(DoublyLinkedList<Student> studentList) {
        System.out.println("Student List : ");
        System.out.println("===================================="
                + "=====================================");
        System.out.println(
                String.format(
                        "| %-20s | %-10s | %-10s | %-20s |",
                        "Student Name", "Student ID",
                        "Programme", "Registered at"));
        System.out.println("===================================="
                + "=====================================");
        System.out.print(studentList);
        System.out.println("===================================="
                + "=====================================");
    }

}
