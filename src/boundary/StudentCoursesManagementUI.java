/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.DoublyLinkedList;
import entity.Filter;
import entity.Student;
import entity.StudentCourseAssociation;
import java.util.Scanner;
import utility.Menu;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class StudentCoursesManagementUI {

    private final Menu menu = new Menu("Student Registration Management | Student Courses Management");
    Scanner scanner;

    public StudentCoursesManagementUI() {
        scanner = new Scanner(System.in);
        menu.add("Course(s) Application"); // select student show course list under thier programme and 
        menu.add("Remove Student Course(s)");
        menu.add("Search Student Registered Course(s)");
        menu.add("List Student for Course(s)");
        menu.add("Course Registration History");
        menu.add("Exit");
    }

    public Student getStudent(DoublyLinkedList<Student> studentList) {
        scanner = new Scanner(System.in);
        Student s = null;
        // search student by id
        System.out.print("Enter student ID: ");
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

    public void showApplicableCourses(Student student, DoublyLinkedList<String> mainCourses, DoublyLinkedList<String> electiveCourses, double fee) {
        System.out.println("= Applicable Courses =");
        System.out.println("Programme   : " + student.getChosenProgramme());
        System.out.println("Student ID  : " + student.getStudentId());

        System.out.println("\nMain Courses:");
        if (mainCourses.isEmpty()) {
            System.out.println("No main courses available.");
        } else {
            for (String mainCourse : mainCourses) {
                System.out.println("- " + mainCourse);
            }
        }
        System.out.println("Tuition Fee : $" + String.format("%.2f", fee));

        System.out.println("\nElective Courses:");
        if (electiveCourses.isEmpty()) {
            System.out.println("No elective courses available.");
        } else {
            for (String electiveCourse : electiveCourses) {
                System.out.println("- " + electiveCourse);
            }
        }
    }

    public int getNumberOfECourses(DoublyLinkedList<String> electiveCourses) {
        System.out.print("Enter the number of elective courses you want to select: ");
        while (!scanner.hasNextInt()) {
            UI.message("Please enter a valid number.");
            scanner.next();
        }
        int numCourses = scanner.nextInt();
        if (numCourses <= 0 || numCourses > electiveCourses.size()) {
            System.out.println("Please enter a number between 1 and " + electiveCourses.size() + ".");
        }
        return numCourses;
    }

    public void selectECourses(DoublyLinkedList<String> electiveCourses, DoublyLinkedList<String> selectedCourses) {
        System.out.print("Enter course code: ");
        String courseCode = scanner.next().trim().toUpperCase();
        while (!electiveCourses.contains(courseCode) || selectedCourses.contains(courseCode + " elective")) {
            if (!electiveCourses.contains(courseCode)) {
                System.out.println("Invalid course code. Please select from the available elective courses.");
            } else {
                System.out.println("You have already selected this course. Please select another one.");
            }
            System.out.print("Enter course code: ");
            courseCode = scanner.next().trim().toUpperCase();
        }
        selectedCourses.add(courseCode + " elective");
    }

    public boolean showSelectedCourses(Student student, DoublyLinkedList<String> selectedCourses, double fee) {
        StringBuilder coursesFormatted = new StringBuilder();
        for (String course : selectedCourses) {
            coursesFormatted.append("- ").append(course).append("\n");
        }

        String programInfo = String.format("Programme        : %s\n", student.getChosenProgramme());
        String studentInfo = String.format("Student ID       : %s\n", student.getStudentId());
        String feeInfo = String.format("Total Tuition Fee: $%.2f\n", fee);
        String selectedCoursesInfo = "Selected Courses :\n" + coursesFormatted;

        String message = "\n[Student Enrollment Summary]\n"
                + programInfo
                + studentInfo
                + feeInfo
                + selectedCoursesInfo;

        return UI.confirmUI(message);
    }

    public int studentCoursesManagementMenu() {
        return menu.displayAndGetChoice();
    }

    public void displayEnrolledCourses(DoublyLinkedList<StudentCourseAssociation> enrolledCourses) {
        System.out.println("= Enrolled Courses =");
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (int i = 0; i < enrolledCourses.size(); i++) {
                StudentCourseAssociation course = enrolledCourses.get(i);
                String courseInfo = String.format("- %s %s", course.getCourseCode(), course.getCourseType());
                System.out.println(courseInfo);
            }
        }
    }

    public String selectCoursesToRemove(DoublyLinkedList<StudentCourseAssociation> enrolledCourses) {
        System.out.print("Enter the course code to remove (0 to remove all): ");
        String option = scanner.nextLine().trim().toUpperCase();
        for (StudentCourseAssociation e : enrolledCourses) {
            if (e.getCourseCode().equals(option)) {
                return option;
            } else if(option.equals("0")){
                return "0";
            }
        }
        return null;
    }

    public void displayCoursesToRemove(String studentId, DoublyLinkedList<String> coursesToRemove) {
        System.out.println("Removing Courses for Student ID: " + studentId);
        for (String course : coursesToRemove) {
            System.out.println("- " + course);
        }
    }

    public boolean confirmRemoval() {
        System.out.print("Confirm removal? (Y/Else): ");
        String input = scanner.next().trim().toUpperCase();
        return input.equals("Y");
    }

    public void displayRegisteredCourses(Student student, DoublyLinkedList<StudentCourseAssociation> registeredCourses) {
        System.out.println("Registered Courses for Student ID: " + student.getStudentId());
        for (StudentCourseAssociation e : registeredCourses) {
            System.out.println("- Course Code: " + e.getCourseCode());
            System.out.println("  Course Type: " + e.getCourseType());
        }
    }

    public Filter getFilter() {
        DoublyLinkedList<String> programmeFilter = new DoublyLinkedList<>();
        double minFeeFilter;
        double maxFeeFilter;
        String paymentStatusFilter;
        DoublyLinkedList<String> courseFilter = new DoublyLinkedList<>();
        scanner = new Scanner(System.in);

        System.out.println("Set Filters or Criteria: ");
        while (true) {
            System.out.print("Enter programme code (or 0 to finish): ");
            String programmeCode = scanner.next().trim().toUpperCase();
            if (programmeCode.equals("0")) {
                break;
            }
            programmeFilter.add(programmeCode);
        }

        while (true) {
            System.out.print("Enter minimum fee (or 0/- for no minimum): ");
            if (scanner.hasNextDouble()) {
                minFeeFilter = scanner.nextDouble();
                break;
            } else {
                UI.message("Invalid input, please enter a valid number for minimum fee.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Enter maximum fee (or 0/- for no maximum): ");
            if (scanner.hasNextDouble()) {
                maxFeeFilter = scanner.nextDouble();
                break;
            } else {
                UI.message("Invalid input, please enter a valid number for maximum fee.");
                scanner.next();
            }
        }
        
        scanner = new Scanner(System.in);
        System.out.print("Enter payment status ([1]paid, [2]unpaid, [Else]no filter): ");
        paymentStatusFilter = scanner.next().toLowerCase().trim();

        System.out.print("Enter course codes (separated by commas, no spaces) to filter: ");
        String[] courses = scanner.next().trim().toUpperCase().split(",");
        for (String s : courses) {
            courseFilter.add(s);
        }

        return new Filter(programmeFilter, minFeeFilter, maxFeeFilter, paymentStatusFilter, courseFilter);
    }

    public void displayFilterStudent(DoublyLinkedList<StudentCourseAssociation> filterStudentCourse, DoublyLinkedList<Student> filterStudents, DoublyLinkedList<String> filterCourse) {
        for (String s : filterCourse) {
            if (getCurrentPrice(filterStudentCourse, s) != -1) {
                System.out.println("Course " + s + "($" + String.format("%.2f", getCurrentPrice(filterStudentCourse, s)) + ") :");
                System.out.println("=============================================================================");
                System.out.println("| Student ID | Name                      | Programme | Tuition Fee | Status |");
                System.out.println("=============================================================================");
                for (Student student : filterStudents) {
                    if (studentHasCourse(filterStudentCourse, student, s)) {
                        String studentId = student.getStudentId();
                        String name = student.getName();
                        String programme = student.getChosenProgramme();
                        double tuitionFee = student.getTuitionFee();
                        boolean paymentStatus = student.getTuitionFeeStatus();

                        String tuitionFeeStr = String.format("%.2f", tuitionFee);
                        String paymentStatusStr = paymentStatus ? "Paid" : "Unpaid";

                        StringBuilder output = new StringBuilder();
                        
                        output.append(String.format("| %-10s | %-25s | %-9s | %-11s | %-6s |", studentId, name, programme, tuitionFeeStr, paymentStatusStr));
                        System.out.println(output);
                    }
                }
                System.out.println("=============================================================================");
            } else {
                System.out.println("");
                UI.message("No record in course " + s + ".\n\n");
            }
        }
    }

    private double getCurrentPrice(DoublyLinkedList<StudentCourseAssociation> filterStudentCourse, String courseCode) {
        for (StudentCourseAssociation e : filterStudentCourse) {
            if (e.getCourseCode().equals(courseCode)) {
                return e.getPrice();
            }
        }
        return -1;
    }

    private boolean studentHasCourse(DoublyLinkedList<StudentCourseAssociation> filterStudentCourse, Student student, String courseCode) {
        for (StudentCourseAssociation e : filterStudentCourse) {
            if (e.getStudentId().equals(student.getStudentId()) && e.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public void showStudentCourseHistory(DoublyLinkedList<StudentCourseAssociation> studentCourseHistory) {
        UI.nextSlide();
        studentCourseHistory.sort();
        System.out.println("==============================================================================================");
        System.out.println(String.format("| %-10s | %-15s | %-10s | %-10s | %-15s | %-15s |", "Student ID", "Course Code", "Type", "Price", "Register Date", "Removed Date"));
        System.out.println("==============================================================================================");
        System.out.print(studentCourseHistory);
        System.out.println("==============================================================================================");
        UI.pressToCont();
    }
}
