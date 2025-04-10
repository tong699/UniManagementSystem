/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.StudentRegistrationManagementReportUI;
import entity.Course;
import entity.Registrant;
import entity.Student;
import entity.StudentCourseAssociation;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class StudentRegistrationManagementReport {

    private static final StudentRegistrationManagementReportUI ui = new StudentRegistrationManagementReportUI();
    private final DoublyLinkedList<Student> studentList;
    private final DoublyLinkedList<Registrant> rejectedList;
    private final DoublyLinkedList<Course> courseList;
    private final DoublyLinkedList<StudentCourseAssociation> studentCourseList;

    public StudentRegistrationManagementReport(DoublyLinkedList<Student> studentList, 
            DoublyLinkedList<Registrant> rejectedList, DoublyLinkedList<Course> courseList, 
            DoublyLinkedList<StudentCourseAssociation> studentCourseList) {
        this.studentList = studentList;
        this.rejectedList = rejectedList;
        this.courseList = courseList;
        this.studentCourseList = studentCourseList;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.studentRegistrationManagementReportMenu()) {
                case 1 ->
                    studentRosterReport();
                case 2 ->
                    financialSummaryReport();
                case 3 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void studentRosterReport() {
        int numAdmission = studentList.size();
        int numRejection = rejectedList.size();

        int maleAdmission = 0;
        int femaleAdmission = 0;
        int maleRejection = 0;
        int femaleRejection = 0;

        double totalPaid = 0;
        double totalUnpaid = 0;
        double totalRegistrationFee = 0;

        double totalCGPA = 0;
        double totalAdmissionCGPA = 0;
        double totalRejectionCGPA = 0;

        DoublyLinkedList<String> programCodes = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> programApplications = new DoublyLinkedList<>();

        for (Student e : studentList) {
            totalCGPA += e.getHighSchoolResult();
            totalAdmissionCGPA += e.getHighSchoolResult();
            if (e.getGender().equals("M")) {
                maleAdmission++;
            } else {
                femaleAdmission++;
            }
            if (e.getRegisFeeStatus()) {
                totalPaid += e.getRegistrationFee();
            } else {
                totalUnpaid += e.getRegistrationFee();
            }
            totalRegistrationFee += e.getRegistrationFee();

            String programCode = e.getChosenProgramme();
            int index = programCodes.positionOf(programCode);
            if (index == -1) {
                programCodes.add(programCode);
                programApplications.add(1);
            } else {
                programApplications.set(index, programApplications.get(index) + 1);
            }
        }

        for (Registrant e : rejectedList) {
            totalCGPA += e.getHighSchoolResult();
            totalRejectionCGPA += e.getHighSchoolResult();
            if (e.getGender().equals("M")) {
                maleRejection++;
            } else {
                femaleRejection++;
            }
            String programCode = e.getChosenProgramme();
            int index = programCodes.positionOf(programCode);
            if (index == -1) {
                programCodes.add(programCode);
                programApplications.add(1);
            } else {
                programApplications.set(index, programApplications.get(index) + 1);
            }
        }

        double avgCGPA = totalCGPA / (studentList.size() + rejectedList.size());
        double avgAdmissionCGPA = totalAdmissionCGPA / studentList.size();
        double avgRejectionCGPA = totalRejectionCGPA / rejectedList.size();

        ui.studentRosterReport(numAdmission, numRejection, maleAdmission, maleRejection, 
                femaleAdmission, femaleRejection, totalPaid, totalUnpaid, totalRegistrationFee, 
                avgCGPA, avgAdmissionCGPA, avgRejectionCGPA, programCodes, programApplications);
    }

    private void financialSummaryReport() {
        // Example data
        int numCourses = courseList.size();
        int numStudents = studentList.size();
        int numRegisteredStudents = 0;
        for (Student e : studentList) {
            if (e.getTuitionFee() != 0) {
                numRegisteredStudents++;
            }
        }
        int numUnregisteredStudents = numStudents - numRegisteredStudents;
        DoublyLinkedList<String> courseCodes = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> numRegistrations = new DoublyLinkedList<>();
        DoublyLinkedList<Double> coursePrices = new DoublyLinkedList<>();
        for (StudentCourseAssociation e : studentCourseList) {
            int index = courseCodes.positionOf(e.getCourseCode());
            if(index == -1){
                courseCodes.add(e.getCourseCode());
                numRegistrations.add(1);
                coursePrices.add(e.getPrice());
            } else {
                numRegistrations.set(index, numRegistrations.get(index)+1);
            }
        }
        
        double paidAmount = 0;
        for (Student e : studentList) {
            if (e.getTuitionFeeStatus()) {
                paidAmount += e.getTuitionFee();
            }
        }

        ui.generateRegistrationReport(numCourses, numStudents, numRegisteredStudents, numUnregisteredStudents, 
                courseCodes, numRegistrations, coursePrices, paidAmount);
    }

}
