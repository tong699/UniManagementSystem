/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.DoublyLinkedList;
import entity.Tutor;
import entity.TutorCourseAssociation;
import entity.TutorTutorialGrpAssociation;
import entity.TutorialGroup;
import java.util.Scanner;
import utility.Menu;
import utility.UI;

/**
 *
 * @author Chan Wei Xin
 */
public class TutorManagementUI {

    private final Menu menu = new Menu("Teaching Assignment Management | Tutor Management");
    private final Menu subMenu1 = new Menu("Teaching Assignment Management | Tutor Management | Add tutorial Groups to tutor");
    private final Menu subMenu2 = new Menu("Teaching Assignment Management | Tutor Management | List tutorial Groups and tutor");
    private final Menu subMenu3 = new Menu("Teaching Assignment Management | Tutor Management | Search/List Courses by tutor Id");
    private final Menu subMenu4 = new Menu("Teaching Assignment Management | Tutor Management | Filter Tutor");

    public TutorManagementUI() {
        menu.add("Add tutorial Groups to tutor");
        menu.add("List tutorial Groups and tutor");
        menu.add("Search/List Courses by tutor Id");
        menu.add("Filter Tutor");
        menu.add("Exit");

        subMenu3.add("Search Course by tutor id");
        subMenu3.add("List Course for each tutor");
        subMenu3.add("Exit");

        subMenu4.add("Display all tutor details");
        subMenu4.add("Filter by tutor id");
        subMenu4.add("Filter by tutor name");
        subMenu4.add("Filter by tutor age");
        subMenu4.add("Filter by tutor gender");
        subMenu4.add("Filter by tutor education level");
        subMenu4.add("Exit");

    }

    public int TutorManagementMenu() {
        return menu.displayAndGetChoice();
    }

    public int AssignTutorialGrpsTutorMenu() {
        return subMenu1.displayAndGetChoice();
    }

    public Tutor getTutor(DoublyLinkedList<Tutor> tutorList) {
        Scanner scanner = new Scanner(System.in);
        Tutor t = null;
        //search tutor
        System.out.print("Enter Tutor Id :");
        String tutorId = scanner.nextLine().trim().toUpperCase();
        if (!tutorList.isEmpty()) {
            for (Tutor e : tutorList) {
                if (e.getTutorId().equals(tutorId)) {
                    t = e;
                    break;
                }
            }
        }
        return t;
    }

    public TutorialGroup getTutorialGroup(DoublyLinkedList<TutorialGroup> tutorialGroupList) {
        Scanner scanner = new Scanner(System.in);
        TutorialGroup tg = null;
        //search tutorial group
        System.out.print("Enter Tutorial group No :");
        String groupId = scanner.nextLine().trim().toUpperCase();
        if (!tutorialGroupList.isEmpty()) {
            for (TutorialGroup e : tutorialGroupList) {
                if (e.getGroupId().equals(groupId)) {
                    tg = e;
                    break;
                }
            }
        }
        return tg;
    }

    public TutorTutorialGrpAssociation inputTutorialGrpsTutor(String groupId, String tutorId) {

        return new TutorTutorialGrpAssociation(groupId, tutorId);
    }

    public int ListTutorialGrpsTutor() {
        return subMenu2.displayAndGetChoice();
    }

    public void displayTutorialGrpTutorList(DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList) {
        if (!tutorialGrpsTutorList.isEmpty()) {
            UI.title("List of tutorial groups added to tutor");
            for (int i = 0; i < tutorialGrpsTutorList.size(); i++) {
                TutorTutorialGrpAssociation checkTutor = tutorialGrpsTutorList.get(i);
                String currentTutorId = checkTutor.getTutorId();

                boolean alreadyPrinted = false;
                for (int j = 0; j < i; j++) {
                    if (tutorialGrpsTutorList.get(j).getTutorId().equals(currentTutorId)) {
                        alreadyPrinted = true;
                        break;
                    }
                }


                if (!alreadyPrinted) {
                    System.out.println("\nTutor ID: " + currentTutorId);
                    System.out.println("-------------------");
                    System.out.println("Tutorial Group:");
                    for (TutorTutorialGrpAssociation getCourseTutorType : tutorialGrpsTutorList) {
                        if (getCourseTutorType.getTutorId().equals(currentTutorId)) {
                            System.out.println("- " + getCourseTutorType.getGroupId());
                        }
                    }
                }
            }
        } else {
            UI.message("No tutorial groups added to tutor.");
        }
    }

    public int SearchCoursesMenu() {
        return subMenu3.displayAndGetChoice();
    }

    public DoublyLinkedList<TutorCourseAssociation> getCourses(DoublyLinkedList<TutorCourseAssociation> tutorCourseList) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList<TutorCourseAssociation> courses = new DoublyLinkedList<>();

        System.out.print("Enter Tutor Id: ");
        String tutorId = scanner.nextLine().trim().toUpperCase();

        for (TutorCourseAssociation course : tutorCourseList) {
            if (course.getTutorId().equals(tutorId)) {
                courses.add(course);
            }
        }

        return courses;
    }

    public void listCoursesEachTutor(DoublyLinkedList<TutorCourseAssociation> tutorCourseList) {
        if (!tutorCourseList.isEmpty()) {
            UI.title("List of tutors added to courses");
            for (int i = 0; i < tutorCourseList.size(); i++) {
                TutorCourseAssociation checkTutor = tutorCourseList.get(i);
                String currentTutorId = checkTutor.getTutorId();

                // check if this tutor ID is already printed
                boolean alreadyPrinted = false;
                for (int j = 0; j < i; j++) {
                    if (tutorCourseList.get(j).getTutorId().equals(currentTutorId)) {
                        alreadyPrinted = true;
                        break;
                    }
                }

                if (!alreadyPrinted) {
                    System.out.println("\nTutor ID: " + currentTutorId);
                    System.out.println("Courses:");
                    System.out.println("-------------------");
                    for (TutorCourseAssociation getCourseTutorType : tutorCourseList) {
                        if (getCourseTutorType.getTutorId().equals(currentTutorId)) {
                            System.out.println("- " + getCourseTutorType.getCourseCode() + "    - " + getCourseTutorType.getTutorType());
                        }
                    }
                }
            }
        } else {
            UI.message("No tutors added to courses.");
        }
    }

    public int FilterTutorMenu() {
        return subMenu4.displayAndGetChoice();
    }

    public DoublyLinkedList<Tutor> getFilterTutorId(DoublyLinkedList<Tutor> tutorList) {
        Scanner scanner = new Scanner(System.in);
        String tutorId;
        DoublyLinkedList<Tutor> filteredTutors = new DoublyLinkedList<>();

        // input tutor id filter
        System.out.print("Enter tutor Id: ");
        tutorId = scanner.nextLine().toUpperCase().trim();
        for (Tutor tutor : tutorList) {
            if (tutor.getTutorId().equals(tutorId)) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;
    }

    public DoublyLinkedList<Tutor> getFilterTutorName(DoublyLinkedList<Tutor> tutorList) {
        Scanner scanner = new Scanner(System.in);
        String tutorName;
        DoublyLinkedList<Tutor> filteredTutors = new DoublyLinkedList<>();

        // input tutor name filter
        System.out.print("Enter tutor Name (with space): ");
        tutorName = scanner.nextLine().toUpperCase();
        for (Tutor tutor : tutorList) {
            if (tutor.getTutorName().equals(tutorName)) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;

    }

    public DoublyLinkedList<Tutor> getFilterTutorAge(DoublyLinkedList<Tutor> tutorList) {
        Scanner scanner = new Scanner(System.in);
        int tutorAge;
        DoublyLinkedList<Tutor> filteredTutors = new DoublyLinkedList<>();

        // input tutor age filter
        System.out.print("Enter tutor age: ");
        tutorAge = scanner.nextInt();
        for (Tutor tutor : tutorList) {
            if (tutor.getTutorAge() == tutorAge) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;
    }

    public DoublyLinkedList<Tutor> getFilterTutorGender(DoublyLinkedList<Tutor> tutorList) {
        Scanner scanner = new Scanner(System.in);
        String gender;
        DoublyLinkedList<Tutor> filteredTutors = new DoublyLinkedList<>();

        // input tutor gender filter
        System.out.print("Enter tutor gender: ");
        gender = scanner.nextLine().toUpperCase().trim();
        for (Tutor tutor : tutorList) {
            if (tutor.getGender().equals(gender)) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;

    }

    public DoublyLinkedList<Tutor> getFilterTutorEducationLevel(DoublyLinkedList<Tutor> tutorList) {
        Scanner scanner = new Scanner(System.in);
        String educationLevel;
        DoublyLinkedList<Tutor> filteredTutors = new DoublyLinkedList<>();

        // input tutor gender filter
        System.out.print("Enter tutor education background: ");
        educationLevel = scanner.nextLine().toUpperCase().trim();
        for (Tutor tutor : tutorList) {
            if (tutor.getEducationLevel().equals(educationLevel)) {
                filteredTutors.add(tutor);
            }
        }
        return filteredTutors;

    }

    public void displayFilterTutor(DoublyLinkedList<Tutor> tutorList) {
        if (!tutorList.isEmpty()) {
            System.out.println("| Tutor ID | Name                      | Age | Gender | Education Level |");
            System.out.println(" -----------------------------------------------------------------------");

            for (Tutor t : tutorList) {
                // get the details of the filtered tutor
                String tutorId = t.getTutorId();
                String tutorName = t.getTutorName();
                int tutorAge = t.getTutorAge();
                String gender = t.getGender();
                String educationLevel = t.getEducationLevel();

                StringBuilder output = new StringBuilder();
                output.append(String.format("| %-8s | %-25s | %-3s | %-6s | %-15s |", tutorId, tutorName, tutorAge, gender, educationLevel));
                System.out.println(output);
            }

        } else {
            UI.message("Not Match.");
        }
    }

}
