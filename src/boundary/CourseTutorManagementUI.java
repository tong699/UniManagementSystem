/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.DoublyLinkedList;
import entity.Course;
import entity.Tutor;
import entity.TutorCourseAssociation;
import entity.TutorialGroup;
import entity.TutorialGrpCourseAssociation;
import java.util.Scanner;
import utility.Menu;
import utility.UI;

/**
 *
 * @author Chan Wei Xin
 */
public class CourseTutorManagementUI {

    private final Menu menu = new Menu("Teaching Assignment Management | Course Tutor Management");
    private final Menu subMenu1 = new Menu("Teaching Assignment Management | Course Tutor Management | Assign tutor to courses");
    private final Menu subMenu2 = new Menu("Teaching Assignment Management | Course Tutor Management | Add tutor to a tutorial group for a course");
    private final Menu subMenu3 = new Menu("Teaching Assignment Management | Course Tutor Management | Search / List tutor for a course");
    private final Menu subMenu4 = new Menu("Teaching Assignment Management | Course Tutor Management | List tutors and tutorial groups for a course");

    public CourseTutorManagementUI() {
        menu.add("Assign tutor to courses");
        menu.add("Add tutor to a tutorial group for a course");
        menu.add("Search tutor for a course");
        menu.add("List tutors and tutorial groups for a course");
        menu.add("Exit");

        subMenu3.add("Search tutor by course Id");
        subMenu3.add("List all tutor added in courses");
        subMenu3.add("Exit");

    }

    public int CourseTutorManagementMenu() {
        return menu.displayAndGetChoice();
    }

    public int AssignTutorToCourseMenu() {
        return subMenu1.displayAndGetChoice();
    }

    public Tutor getTutorId(DoublyLinkedList<Tutor> tutorList) {
        Scanner scanner = new Scanner(System.in);
        Tutor tg = null;
        //search tutor
        System.out.print("Enter tutor Id :");
        String tutorId = scanner.nextLine().trim().toUpperCase();
        if (!tutorList.isEmpty()) {
            for (Tutor e : tutorList) {
                if (e.getTutorId().equals(tutorId)) {
                    tg = e;
                    break;
                }
            }
        }
        return tg;
    }

    public Course getCourseId(DoublyLinkedList<Course> courseList) {
        Scanner scanner = new Scanner(System.in);
        Course tg = null;
        //search course
        System.out.print("Enter course Id :");
        String course = scanner.nextLine().trim().toUpperCase();
        if (!courseList.isEmpty()) {
            for (Course e : courseList) {
                if (e.getCourseCode().equals(course)) {
                    tg = e;
                    break;
                }
            }
        }
        return tg;
    }


    public TutorCourseAssociation inputTutorCourse(String tutor, String courseId) {
        Scanner scanner = new Scanner(System.in);
        String tutorType;
        while (true) {
            System.out.print("Enter Tutor Type (Lecture/Tutorial/Practical): ");
            tutorType = scanner.nextLine().trim().toUpperCase();
            if (tutorType.equals("LECTURE") || tutorType.equals("TUTORIAL") || tutorType.equals("PRACTICAL")) {
                break;
            } else {
                System.out.println("Invalid tutor type. Please enter either 'Lecture', 'Tutorial', or 'Practical'.");
            }
        }

        return new TutorCourseAssociation(tutor, courseId, tutorType);
    }

    public int AssignTutorToTutGrpMenu() {
        return subMenu2.displayAndGetChoice();
    }

    public TutorialGroup getTutorialGroup(DoublyLinkedList<TutorialGroup> tutorialList) {
        Scanner scanner = new Scanner(System.in);
        TutorialGroup tg = null;
        //search tutorial group
        System.out.print("Enter Tutorial group No :");
        String groupId = scanner.nextLine().trim().toUpperCase();
        if (!tutorialList.isEmpty()) {
            for (TutorialGroup e : tutorialList) {
                if (e.getGroupId().equals(groupId)) {
                    tg = e;
                    break;
                }
            }
        }
        return tg;
    }

    public DoublyLinkedList<TutorialGrpCourseAssociation> getTutorialGrpTutor(DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList, String tutorialGrp) {
        DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpWithoutTutor = new DoublyLinkedList<>();
        if (!tutorialGrpCourseList.isEmpty()) {
            for (TutorialGrpCourseAssociation e : tutorialGrpCourseList) {
                if (e.getTutorid() == null && e.getGroupId().equals(tutorialGrp)) {
                    tutorialGrpWithoutTutor.add(e);
                }
            }
        }
        return tutorialGrpWithoutTutor;
    }

    public TutorialGrpCourseAssociation inputTutorsTutorialGrp(String tutorialGrp) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine().toUpperCase();
        System.out.print("Enter Tutor Id: ");
        String tutorId = scanner.nextLine().toUpperCase();

        return new TutorialGrpCourseAssociation(tutorialGrp, courseCode, tutorId);
    }

    public int SearchTutorCourseMenu() {
        return subMenu3.displayAndGetChoice();
    }

    public DoublyLinkedList<TutorCourseAssociation> getTutorsFromCourse(DoublyLinkedList<TutorCourseAssociation> tutorCourseList) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList<TutorCourseAssociation> tutorCourse = new DoublyLinkedList<>();

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine().trim().toUpperCase();
        if (!tutorCourseList.isEmpty()) {
            for (TutorCourseAssociation tutor : tutorCourseList) {
                if (tutor.getCourseCode().equals(courseCode)) {
                    tutorCourse.add(tutor);
                }
            }
        }
        return tutorCourse;
    }

    public void displayTutorCourseList(DoublyLinkedList<TutorCourseAssociation> tutorCourseList) {
        if (!tutorCourseList.isEmpty()) {
            UI.title("List of tutors added to tutorial group");
            System.out.println("Tutor ID    Course Codes");

            // Iterate through the list of tutor-course associations
            for (int i = 0; i < tutorCourseList.size(); i++) {
                TutorCourseAssociation current = tutorCourseList.get(i);
                String tutorId = current.getTutorId();

                // Check if this tutor ID has already been printed
                boolean alreadyPrinted = false;
                for (int j = 0; j < i; j++) {
                    if (tutorCourseList.get(j).getTutorId().equals(tutorId)) {
                        alreadyPrinted = true;
                        break;
                    }
                }

                // If the tutor ID has not been printed yet, print it along with its course codes
                if (!alreadyPrinted) {
                    System.out.print(tutorId + "       ");
                    StringBuilder courseCodes = new StringBuilder();
                    for (TutorCourseAssociation tutorCourse : tutorCourseList) {
                        if (tutorCourse.getTutorId().equals(tutorId)) {
                            courseCodes.append(tutorCourse.getCourseCode()).append(", ");
                        }
                    }
                    // Remove the trailing comma and space if there are course codes
                    if (courseCodes.length() > 0) {
                        courseCodes.setLength(courseCodes.length() - 2); // Remove the last two characters
                    }
                    System.out.println(courseCodes);
                }
            }
        } else {
            UI.message("No tutors added to courses.");
        }
    }

    public int ListTutorsAndTutorialGrpMenu() {
        return subMenu4.displayAndGetChoice();
    }

    public void displayTutorialGrpCourseList(DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList) {
        if (!tutorialGrpCourseList.isEmpty()) {
            UI.title("List of tutor added in courses");
            System.out.println("Course          Tutorial Group       Tutor Id");
            for (TutorialGrpCourseAssociation TutorTutGrp : tutorialGrpCourseList) {
                System.out.println(TutorTutGrp.getCourseCode() + "             " + TutorTutGrp.getGroupId() + "                   " + TutorTutGrp.getTutorid());
            }
        } else {
            UI.message("No tutor added in a courses.");
        }
    }
}
