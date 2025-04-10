package control;

import adt.DoublyLinkedList;
import boundary.ProgrammeCourseSubsystemUI;
import entity.Course;
import entity.Faculty;
import java.util.Scanner;

import utility.UI;

/**
 *
 * @author jerry
 */
public class ProgrammeCourseSubsystem {

    private static final ProgrammeCourseSubsystemUI ui = new ProgrammeCourseSubsystemUI();
    private final DoublyLinkedList<Faculty> facultyList;
    private final DoublyLinkedList<FacultyManagement> fmList;
    private static final Scanner scanner = new Scanner(System.in);

    public ProgrammeCourseSubsystem(DoublyLinkedList<Faculty> facultyList) {
        this.facultyList = facultyList;
        this.fmList = new DoublyLinkedList<>();
        for (int i = 0; i < facultyList.size(); i++) {
            fmList.add(new FacultyManagement(facultyList.get(i), facultyList));
        }
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.programmeCourseSubsystemMenu()) {
                case 1 ->
                    fmList.get(0).run();
                case 2 ->
                    fmList.get(1).run();
                case 3 ->
                    showCourseTakenByAllFaculty();
                case 4 ->
                    searchCourseDetails();
                case 5 ->
                    overallReport();
                case 6 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void showCourseTakenByAllFaculty() {
        UI.nextSlide();
        for (FacultyManagement fm : fmList) {
            Faculty faculty = fm.getFaculty();
            DoublyLinkedList<Course> courseList = faculty.getCourseList();

            if (courseList.isEmpty()) {
                System.out.println("Faculty: " + faculty.getFacultyName());
                System.out.println("No courses available.");
                UI.nextSlide();
                continue;
            }

            System.out.println("Faculty: " + faculty.getFacultyName());
            UI.message("Available Courses");
            System.out.println("No      Course Code         Course Name");
            System.out.println("--------------------------------------------------------");
            for (int i = 0; i < courseList.size(); i++) {
                Course course = courseList.get(i);
                System.out.println((i + 1) + ".      " + course.getCourseCode() + "            " + course.getCourseName());
            }
            System.out.println();
        }
        UI.pressToCont();
    }

    private void searchCourseDetails() {
        boolean isCourseListEmpty = true;
        for (FacultyManagement fm : fmList) {
            if (!fm.getFaculty().getCourseList().isEmpty()) {
                isCourseListEmpty = false;
                break;
            }
        }
        if (isCourseListEmpty) {
            UI.nextSlide();
            UI.message("No courses added.");
            UI.exit();
            return;
        }

        UI.nextSlide();
        System.out.println("Available Course IDs:");
        for (FacultyManagement fm : fmList) {
            for (Course course : fm.getFaculty().getCourseList()) {
                System.out.println("- " + course.getCourseCode());
            }
        }

        System.out.println("Enter the Course ID to view details:");
        String searchCourseID = scanner.nextLine().toUpperCase();

        Faculty courseFaculty = null;
        Course course = null;
        for (FacultyManagement fm : fmList) {
            for (Course c : fm.getFaculty().getCourseList()) {
                if (c.getCourseCode().equals(searchCourseID)) {
                    courseFaculty = fm.getFaculty();
                    course = c;
                    break;
                }
            }
            if (course != null) {
                break;
            }
        }
        if (courseFaculty != null && course != null) {
            UI.nextSlide();
            UI.title("Course Details");
            System.out.println("Course Code  : " + course.getCourseCode());
            System.out.println("Course Name  : " + course.getCourseName());
            System.out.println("Course Type  : " + course.getCourseType());
            System.out.println("Price        : RM" + course.getPrice());
            System.out.println("Credit Hours : " + course.getCreditHour());
            System.out.println("Faculty      : " + courseFaculty.getFacultyID() + " - " + courseFaculty.getFacultyName());
            System.out.println("\nCourse Materials:");
            DoublyLinkedList<String> courseMaterialList = courseFaculty.getCourseMaterialList();
            boolean materialsFound = false;
            for (String material : courseMaterialList) {
                if (material.startsWith(course.getCourseCode() + ":")) {
                    System.out.println("- " + material.substring(material.indexOf(":") + 1));
                    materialsFound = true;
                }
            }
            if (!materialsFound) {
                System.out.println("- No materials found for this course.");
            }
        } else {
            UI.nextSlide();
            UI.message("Course with ID " + searchCourseID + " not found.");
        }
    }

    private void overallReport() {
        UI.nextSlide();
        UI.title("      Overall Summary Report     ");
        System.out.println("------------- Faculty Summary ------------");
        System.out.println(" |    Total number of faculties  : " + facultyList.size() + "    |");
        int totalProgrammes = 0;
        for (Faculty faculty : facultyList) {
            totalProgrammes += faculty.getProgrammeList().size();
        }
        System.out.println(" |    Total number of programmes : " + totalProgrammes + "    |");
        int totalCourses = 0;
        for (Faculty faculty : facultyList) {
            totalCourses += faculty.getCourseList().size();
        }
        System.out.println(" |    Total number of courses    : " + totalCourses + "   |");
        System.out.println("------------------------------------------");
        UI.nextSlide();
        UI.pressToCont();
    }
}
