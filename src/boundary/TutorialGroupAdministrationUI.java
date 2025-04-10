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
public class TutorialGroupAdministrationUI {

    private final Menu menu = new Menu("Tutorial Group Management | Tutorial Group Administration");
    private final Menu subMenu1 = new Menu("Tutorial Group Management | Tutorial Group Administration | Creation And Modification");
    private final Menu subMenu2 = new Menu("Tutorial Group Management | Tutorial Group Administration | Representative");
    private final Menu subMenu3 = new Menu("Tutorial Group Management | Tutorial Group Administration | Group Information");

    public TutorialGroupAdministrationUI() {
        menu.add("Creation And Modification");
        menu.add("Assign Representative");
        menu.add("Group Information");
        menu.add("Exit");

        subMenu1.add("Add New Tutorial Group");
        subMenu1.add("Remove Tutorial Group");
        subMenu1.add("Merge Tutorial Groups");
        subMenu1.add("Set Group Capacity");
        subMenu1.add("Exit");

        subMenu2.add("Add Representative");
        subMenu2.add("Change Representative");
        subMenu2.add("Remove Representative");
        subMenu2.add("Exit");

    }

    public int tutorialGroupAdministrationMenu() {

        return menu.displayAndGetChoice();
    }

    public int creationAndModificationMenu() {

        return subMenu1.displayAndGetChoice();
    }

    public int assignRepresentativeMenu() {

        return subMenu2.displayAndGetChoice();
    }

    public int groupInformationMenu() {

        return subMenu3.displayAndGetChoice();
    }

    public Integer inputGroupToAdd() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of group to add : ");
        String input = scanner.nextLine();

        if (input.matches("^\\d+$")) {
            int totalGroupToAdd = Integer.parseInt(input);
            return totalGroupToAdd;
        } else {
            return 0;
        }
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

    public Integer enterNewCapacityForMergedGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter New Capacity For Mergeed Group : ");
        String input = scanner.nextLine();
        if (input.matches("^\\d+$")) {
            int newCapacity = Integer.parseInt(input);
            return newCapacity;
        } else {
            return 0;
        }
    }

    public Integer enterNewCapacity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new capacity: ");
        String input = scanner.nextLine();
        if (input.matches("^\\d+$")) {
            int newCapacity = Integer.parseInt(input);
            return newCapacity;
        } else {
            return 0;
        }
    }

    public void showGroupList(DoublyLinkedList<TutorialGroup> tutorialGroupList, DoublyLinkedList<Programme> programmeList) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            tutorialGroupList.sort();
            UI.nextSlide();
            UI.title("Show Tutorial Group List");
            System.out.println("*Sorted by registered date*");
            System.out.println("[1]List all groups\n[2]List groups by programme\n[Else]Exit");
            System.out.print("Enter your selection: ");
            String select = scanner.nextLine();
            switch (select) {
                case "1" -> {
                    UI.nextSlide();
                    UI.title("All Tutorial Group");
                    showGroups(tutorialGroupList);
                    UI.pressToCont();
                }
                case "2" -> {
                    UI.nextSlide();
                    Programme programme = getProgramme(programmeList);
                    if (programme != null) {
                        UI.nextSlide();
                        UI.title("Tutorial Groups For Programme " + programme.getProgrammeId());
                        programme.getTutorialGroupList().sort();
                        showGroups(programme.getTutorialGroupList());
                        UI.pressToCont();
                    } else {
                        UI.message("Programme not found.");
                    }
                }
                default ->
                    exit = true;
            }
        }
    }

    public void showGroups(DoublyLinkedList<TutorialGroup> groupList) {
        System.out.println("==============================================================================================");
        System.out.println(String.format("| %-5s | %-10s | %-30s | %-20s | %-10s |", "Group ID", "Capacity", "Group Representative", "Enrolled Students", "Programme"));
        System.out.println("==============================================================================================");
        System.out.print(groupList);
        System.out.println("==============================================================================================");
    }

    public void showStudentList(DoublyLinkedList<Student> studentList) {
        System.out.println("\nStudent List : ");
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
