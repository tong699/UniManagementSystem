/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.StudentCoursesManagementUI;
import entity.Course;
import entity.Filter;
import entity.ProgrammeCourseAssociation;
import entity.Student;
import entity.StudentCourseAssociation;
import java.time.LocalDate;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class StudentCoursesManagement {

    private static final StudentCoursesManagementUI ui = new StudentCoursesManagementUI();
    private final DoublyLinkedList<Student> studentList;
    private final DoublyLinkedList<Course> courseList;
    private final DoublyLinkedList<StudentCourseAssociation> studentCourseList;
    private final DoublyLinkedList<StudentCourseAssociation> studentCourseHistory;
    private final DoublyLinkedList<ProgrammeCourseAssociation> programmeCourseList;

    public StudentCoursesManagement(DoublyLinkedList<Student> studentList,
            DoublyLinkedList<Course> courseList,
            DoublyLinkedList<StudentCourseAssociation> studentCourseList,
            DoublyLinkedList<StudentCourseAssociation> studentCourseHistory,
            DoublyLinkedList<ProgrammeCourseAssociation> programmeCourseList) {
        this.studentList = studentList;
        this.courseList = courseList;
        this.studentCourseList = studentCourseList;
        this.studentCourseHistory = studentCourseHistory;
        this.programmeCourseList = programmeCourseList;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.studentCoursesManagementMenu()) {
                case 1 ->
                    courseApplication();
                case 2 ->
                    removeStudentCourse();
                case 3 ->
                    searchStudentCourse();
                case 4 ->
                    listStudentCourse();
                case 5 ->
                    courseRegistrationHistory();
                case 6 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void courseApplication() {
        Student selectedStudent = ui.getStudent(studentList);
        UI.nextSlide();
        if (selectedStudent != null) {
            if (selectedStudent.getTuitionFee() == 0) {
                DoublyLinkedList<String> couseApplied = selectCourse(selectedStudent);
                if (couseApplied != null) {
                    for (String s : couseApplied) {
                        String[] parts = s.split("\\ ");
                        studentCourseList.add(
                                new StudentCourseAssociation(
                                        selectedStudent.getStudentId(), parts[0],
                                        parts[1], getCurrentPrice(parts[0])));
                    }
                } else {
                    UI.message("Student course registration cancelled.");
                }
            } else {
                UI.message("Student already registered for courses, any addition are not allowed.");
            }
        } else {
            UI.message("Student not found.");
        }
    }

    private double getCurrentPrice(String courseCode) {
        double currentPrice = 0;
        for (Course c : courseList) {
            if (c.getCourseCode().equals(courseCode)) {
                currentPrice = c.getPrice();
            }
        }
        return currentPrice;
    }

    private DoublyLinkedList<String> selectCourse(Student student) {
        double fee = 0;
        DoublyLinkedList<String> mainCourses = new DoublyLinkedList<>();
        DoublyLinkedList<String> electiveCourses = new DoublyLinkedList<>();
        DoublyLinkedList<String> selectedCourses = new DoublyLinkedList<>();
        for (ProgrammeCourseAssociation e : programmeCourseList) {
            if (e.getProgrammeId().equals(student.getChosenProgramme())) {
                for (Course c : courseList) {
                    if (c.getCourseCode().equals(e.getCourseCode())) {
                        if (c.getCourseType().trim().toLowerCase().equals("main")) {
                            mainCourses.add(e.getCourseCode());
                            selectedCourses.add(e.getCourseCode() + " main");
                            fee += c.getPrice();
                        } else if (c.getCourseType().trim().toLowerCase().equals("elective")) {
                            electiveCourses.add(e.getCourseCode());
                        }
                    }

                }
            }
        }

        ui.showApplicableCourses(student, mainCourses, electiveCourses, fee);

        int numCourses;
        if (electiveCourses.size() > 0) {
            do {
                numCourses = ui.getNumberOfECourses(electiveCourses);
            } while (numCourses <= 0 || numCourses > electiveCourses.size());

            for (int i = 0; i < numCourses; i++) {
                ui.selectECourses(electiveCourses, selectedCourses);
            }
            for (ProgrammeCourseAssociation e : programmeCourseList) {
                for (Course c : courseList) {
                    if (c.getCourseCode().equals(e.getCourseCode())) {
                        if (c.getCourseType().trim().toLowerCase().equals("elective") 
                                && e.getProgrammeId().equals(student.getChosenProgramme())) {
                            for (String s : selectedCourses) {
                                String[] parts = s.split("\\ ");
                                if (e.getCourseCode().trim().toLowerCase().equals(
                                        parts[0].trim().toLowerCase())) {
                                    fee += c.getPrice();
                                }
                            }
                        }
                    }
                }
            }
        }

        UI.nextSlide();
        if (fee == 0) {
            return null;
        }
        if (ui.showSelectedCourses(student, selectedCourses, fee)) {
            student.setTuitionFee(fee);
            student.setTuitionFeeStatus(false);
            student.setTuitionFeeDueDate(LocalDate.now().plusWeeks(2));
            return selectedCourses;
        }
        return null;
    }

    private void removeStudentCourse() {
        Student student = ui.getStudent(studentList);
        UI.nextSlide();

        if (student != null) {
            if (!student.getTuitionFeeStatus()) {
                DoublyLinkedList<StudentCourseAssociation> enrolledCourses = 
                        getEnrolledCourses(student.getStudentId());
                if (!enrolledCourses.isEmpty()) {
                    ui.displayEnrolledCourses(enrolledCourses);
                    String option = ui.selectCoursesToRemove(enrolledCourses);
                    UI.nextSlide();
                    DoublyLinkedList<String> coursesToRemove = new DoublyLinkedList<>();
                    if ("0".equals(option)) {
                        for (StudentCourseAssociation course : enrolledCourses) {
                            coursesToRemove.add(course.getCourseCode());
                        }
                    } else if (option != null) {
                        for (StudentCourseAssociation e : enrolledCourses) {
                            if (e.getCourseCode().equals(option)) {
                                coursesToRemove.add(e.getCourseCode());
                                break;
                            }
                        }
                    } else {
                        UI.message("Invalid option, no courses removed.");
                        return;
                    }
                    ui.displayCoursesToRemove(student.getStudentId(), coursesToRemove);
                    if (ui.confirmRemoval()) {
                        removeCoursesFromStudent(student, coursesToRemove);
                        UI.message("Courses removed successfully.");
                    } else {
                        UI.message("Removal cancelled. No courses removed.");
                    }
                } else {
                    UI.message("No courses to remove.");
                }
            } else {
                UI.message("Student paid tuition fee any modification are not allowed.");
            }
        } else {
            UI.message("Student not found.");
        }
    }

    private DoublyLinkedList<StudentCourseAssociation> getEnrolledCourses(String studentId) {
        DoublyLinkedList<StudentCourseAssociation> enrolledCourses = new DoublyLinkedList<>();
        for (StudentCourseAssociation e : studentCourseList) {
            if (e.getStudentId().equals(studentId)) {
                enrolledCourses.add(e);
            }
        }
        return enrolledCourses;
    }

    private void removeCoursesFromStudent(Student student, DoublyLinkedList<String> coursesToRemove) {
        double fee = student.getTuitionFee();
        LocalDate dueDate = LocalDate.now().plusWeeks(2);

        for (StudentCourseAssociation e : studentCourseList) {
            if (e.getStudentId().equals(student.getStudentId())) {
                if (coursesToRemove.contains(e.getCourseCode())) {
                    fee -= e.getPrice();
                    e.setRemovedDate(LocalDate.now());
                    studentCourseHistory.add(e);
                }
            }
        }

        for (int i = 0; i < coursesToRemove.size(); i++) {
            for (StudentCourseAssociation e : studentCourseList) {
                if (e.getRemovedDate() != null) {
                    studentCourseList.remove(studentCourseList.positionOf(e));
                    break;
                }
            }
        }

        if (student.getTuitionFee() == 0) {
            dueDate = null;
            student.setTuitionFeeStatus(true);
        }

        student.setTuitionFee(fee);
        student.setTuitionFeeDueDate(dueDate);
    }

    private void searchStudentCourse() {
        Student student = ui.getStudent(studentList);
        UI.nextSlide();
        if (student != null) {
            DoublyLinkedList<StudentCourseAssociation> registeredCourses = 
                    getRegisteredCourses(student.getStudentId());
            if (!registeredCourses.isEmpty()) {
                ui.displayRegisteredCourses(student, registeredCourses);
                UI.pressToCont();
            } else {
                UI.message("No registered courses found for student ID: " + student.getStudentId());
            }
        } else {
            UI.message("Student not found.");
        }
    }

    private DoublyLinkedList<StudentCourseAssociation> getRegisteredCourses(String studentId) {
        DoublyLinkedList<StudentCourseAssociation> registeredCourses = new DoublyLinkedList<>();
        for (StudentCourseAssociation course : studentCourseList) {
            if (course.getStudentId().equals(studentId)) {
                registeredCourses.add(course);
            }
        }
        return registeredCourses;
    }

    private void listStudentCourse() {
        UI.nextSlide();
        DoublyLinkedList<Student> filterStudent;
        DoublyLinkedList<String> filterCourse;
        DoublyLinkedList<StudentCourseAssociation> filterStudentCourse;

        // let user input filter by 
        Filter filter = ui.getFilter();

        // programme (select one or many programme, or null then no filter on programme), 
        filterStudent = passProgrammeFilter(filter.getProgrammeFilter());

        // fee(larger than, smaller than, in between, or no filter on student tuition fee), 
        filterStudent = passTuitionFeeFilter(
                filterStudent, filter.getMinFeeFilter(), filter.getMaxFeeFilter());

        // payment status (paid, unpaid, no filter on payment status), 
        filterStudent = passPaymentStatusFilter(
                filterStudent, filter.getPaymentStatusFilter());

        // course (must choose one or many course to filter, cannot be not filter on course), 
        filterCourse = passCourseFilter(filter.getCourseFilter());
        filterStudentCourse = generateFilter(filterStudent, filterCourse);

        // then based on these criteria i display the result(student) to user
        UI.nextSlide();
        if (!filterStudentCourse.isEmpty()) {
            ui.displayFilterStudent(filterStudentCourse, filterStudent, filterCourse);
        } else {
            UI.message("Nothing filter out.");
        }

    }

    private DoublyLinkedList<Student> passProgrammeFilter(DoublyLinkedList<String> programmeFilter) {
        DoublyLinkedList<Student> filterStudent = new DoublyLinkedList<>();

        if (!programmeFilter.isEmpty()) {
            for (String p : programmeFilter) {
                for (Student s : studentList) {
                    if (s.getChosenProgramme().equals(p)) {
                        filterStudent.add(s);
                    }
                }
            }
        } else {
            for (Student s : studentList) {
                filterStudent.add(s);
            }
        }

        return filterStudent;
    }

    private DoublyLinkedList<Student> passTuitionFeeFilter(DoublyLinkedList<Student> filterStudent, 
            double minFeeFilter, double maxFeeFilter) {
        if (maxFeeFilter > 0) {
            for (int i = 0; i < filterStudent.size(); i++) {
                for (Student s : filterStudent) {
                    if (s.getTuitionFee() < minFeeFilter) {
                        filterStudent.remove(filterStudent.positionOf(s));
                    }
                }
            }
        }

        if (maxFeeFilter > 0) {
            for (int i = 0; i < filterStudent.size(); i++) {
                for (Student s : filterStudent) {
                    if (s.getTuitionFee() > maxFeeFilter) {
                        filterStudent.remove(filterStudent.positionOf(s));
                    }
                }
            }
        }
        return filterStudent;
    }

    private DoublyLinkedList<Student> passPaymentStatusFilter(DoublyLinkedList<Student> filterStudent, 
            String paymentStatusFilter) {
        if (paymentStatusFilter.equals("1")) {
            for (int i = 0; i < filterStudent.size(); i++) {
                for (Student s : filterStudent) {
                    if (!s.getTuitionFeeStatus()) {
                        filterStudent.remove(filterStudent.positionOf(s));
                        break;
                    }
                }
            }
        } else if (paymentStatusFilter.equals("2")) {
            for (int i = 0; i < filterStudent.size(); i++) {
                for (Student s : filterStudent) {
                    if (s.getTuitionFeeStatus()) {
                        filterStudent.remove(filterStudent.positionOf(s));
                    }
                }
            }
        }
        return filterStudent;
    }

    private DoublyLinkedList<String> passCourseFilter(DoublyLinkedList<String> courseFilter) {
        DoublyLinkedList<String> filterCourse = new DoublyLinkedList<>();
        if (courseFilter != null) {
            for (String f : courseFilter) {
                for (Course c : courseList) {
                    if (f.toUpperCase().equals(c.getCourseCode().toUpperCase())) {
                        filterCourse.add(c.getCourseCode());
                    }
                }
            }
        } else {
            for (Course c : courseList) {
                filterCourse.add(c.getCourseCode());
            }
        }
        return filterCourse;
    }

    private DoublyLinkedList<StudentCourseAssociation> generateFilter(DoublyLinkedList<Student> filterStudent, 
            DoublyLinkedList<String> filterCourse) {
        DoublyLinkedList<StudentCourseAssociation> filterStudentCourse = new DoublyLinkedList<>();
        for (StudentCourseAssociation e : studentCourseList) {
            for (int i = 0; i < filterStudent.size(); i++) {
                if (filterCourse.contains(e.getCourseCode())) {
                    if (e.getStudentId().equals(filterStudent.get(i).getStudentId())) {
                        filterStudentCourse.add(e);
                    }
                }
            }
        }
        return filterStudentCourse;
    }

    private void courseRegistrationHistory() {
        if (!studentCourseHistory.isEmpty()) {
            ui.showStudentCourseHistory(studentCourseHistory);
        } else {
            UI.message("No student course history in the system.");
        }
    }
}
