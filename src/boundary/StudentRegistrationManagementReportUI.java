/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.DoublyLinkedList;
import utility.Menu;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class StudentRegistrationManagementReportUI {

    private final Menu menu = new Menu("Student Registration Management | Student Registration Management Report");

    public StudentRegistrationManagementReportUI() {
        menu.add("Student Roster Report");
        menu.add("Courses Registration Report");
        menu.add("Exit");
    }

    public int studentRegistrationManagementReportMenu() {
        return menu.displayAndGetChoice();
    }

    public void studentRosterReport(int numAdmission, int numRejection, int maleAdmission, int maleRejection, int femaleAdmission, int femaleRejection, double totalPaid, double totalUnpaid, double totalRegistrationFee, double avgCGPA, double avgAdmissionCGPA, double avgRejectionCGPA, DoublyLinkedList<String> programCodes, DoublyLinkedList<Integer> programApplications) {
        UI.nextSlide();
        int totalNumberOfRegistrant = numAdmission + numRejection;
        System.out.println("[========================= Student Roster Report =========================]\n");

        // Number of Admission and Rejection
        String admissionStr = padString("Number of Admission =>", 23) + numAdmission + " (" + (numAdmission * 100 / (numAdmission + numRejection)) + "%)";
        String rejectionStr = padString("Number of Rejection =>", 23) + numRejection + " (" + (numRejection * 100 / (numAdmission + numRejection)) + "%)";
        System.out.println(admissionStr + padString("", 40 - admissionStr.length()) + rejectionStr);

        // Gender Distribution  
        if (numRejection == 0) {
            numRejection = 1;
        }
        String maleADistStr = padString("Male Admission      =>", 23) + maleAdmission + " (" + (maleAdmission * 100 / numAdmission) + "%)";
        String maleRDistStr = padString("Male Rejection      =>", 23) + maleRejection + " (" + (maleRejection * 100 / numRejection) + "%)";
        String femaleADistStr = padString("Female Admission    =>", 23) + femaleAdmission + " (" + (femaleAdmission * 100 / numAdmission) + "%)";
        String femaleRDistStr = padString("Female Rejection    =>", 23) + femaleRejection + " (" + (femaleRejection * 100 / numRejection) + "%)";
        System.out.println("\nGender Distribution:");
        System.out.println(maleADistStr + padString("", 40 - maleADistStr.length()) + maleRDistStr);
        System.out.println(femaleADistStr + padString("", 40 - femaleADistStr.length()) + femaleRDistStr);

        // Registrant Highschool CGPA
        String cgpaStr = "\nRegistrant Highschool CGPA: Average = " + String.format("%.2f", avgCGPA);
        System.out.println(cgpaStr);
        String avgCGPAStr = "Avg Admission CGPA  => " + String.format("%.2f", avgAdmissionCGPA) + "             Avg Rejection CGPA  => " + String.format("%.2f", avgRejectionCGPA);
        System.out.println(avgCGPAStr);

        // Programme Summary
        System.out.println("\nProgramme Summary:");
        System.out.println("--------------------------------------");
        for (int i = 0; i < programCodes.size(); i++) {
            System.out.println("  " + padString(programCodes.get(i), 10) + " | " + programApplications.get(i) + " (" + (programApplications.get(i) * 100 / totalNumberOfRegistrant) + "%)");
        }
        System.out.println("--------------------------------------");

        // Registration Fee Payment Summary
        String paidStr = padString("  Amount of Paid         : ", 25) + String.format("%.2f", totalPaid);
        String unpaidStr = padString("  Amount of Unpaid       : ", 25) + String.format("%.2f", totalUnpaid);
        String totalFeeStr = padString("  Total Registration Fee : ", 25) + String.format("%.2f", totalRegistrationFee);
        System.out.println("\nRegistration Fee Payment Summary:");
        System.out.println("--------------------------------------\n" + paidStr + "\n" + unpaidStr + "\n--------------------------------------" + "\n" + totalFeeStr);

        // End of Report
        System.out.println("\n[============================= END of Report =============================]");
    }

    public void generateRegistrationReport(int numCourses, int numStudents, int numRegisteredStudents, int numUnregisteredStudents,
            DoublyLinkedList<String> courseCodes, DoublyLinkedList<Integer> numRegistrations, DoublyLinkedList<Double> coursePrices, double paidAmount) {
        UI.nextSlide();

        double totalTuitionFee = 0;
        for (int i = 0; i < courseCodes.size(); i++) {
            totalTuitionFee += numRegistrations.get(i) * coursePrices.get(i);
        }

        double averageTuitionFeePerStudent = totalTuitionFee / numRegisteredStudents;

        // Print report header
        System.out.println("[===================== Courses Registration Report =====================]\n");
        System.out.println("Number of Courses               => " + numCourses);
        System.out.println("Number of Student               => " + numStudents);
        System.out.println("Number of Student Registered    => " + numRegisteredStudents + " (" + (numRegisteredStudents * 100 / numStudents) + "%)");
        System.out.println("Number of Student No Registered => " + numUnregisteredStudents + " (" + (numUnregisteredStudents * 100 / numStudents) + "%)");
        System.out.println();

        // Print course apply summary
        System.out.println("Course Apply Summary:");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-12s | %-18s | %-12s | %-8s%n", "Course Code", "Number of Register", "Course Price", "Subtotal");
        System.out.println("-------------------------------------------------------------------------");
        double total = 0;
        for (int i = 0; i < courseCodes.size(); i++) {
            double subtotal = numRegistrations.get(i) * coursePrices.get(i);
            System.out.printf("%-12s | %-18d | %-12.2f | %-8.2f%n", courseCodes.get(i), numRegistrations.get(i), coursePrices.get(i), subtotal);
            total += subtotal;
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%50s %.2f%n", "Total:", total);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();

        // Print tuition fee summary
        System.out.println("Tuition Fee Summary:");
        System.out.printf("Average Tuition Fee per Student => $%.2f%n", averageTuitionFeePerStudent);
        System.out.println("----------------------------------------------");
        System.out.println("  Amount of Paid         : $" + String.format("%.2f", paidAmount));
        System.out.println("  Amount of Unpaid       : $" + String.format("%.2f", (totalTuitionFee - paidAmount)));
        System.out.println("----------------------------------------------");
        System.out.println("  Total Registration Fee : $" + String.format("%.2f", totalTuitionFee));
        System.out.println();

        // Print end of report
        System.out.println("[============================= END of Report =============================]");
    }

    private String padString(String input, int length) {
        StringBuilder paddedString = new StringBuilder(input);
        for (int i = input.length(); i < length; i++) {
            paddedString.append(" ");
        }
        return paddedString.toString();
    }
}
