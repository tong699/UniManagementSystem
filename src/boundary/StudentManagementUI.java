/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ArrayList;
import adt.DoublyLinkedList;
import entity.Programme;
import entity.Registrant;
import entity.Student;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.*;
import utility.Menu;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class StudentManagementUI {

    private final Menu menu = new Menu("Student Registration Management | Student Management");
    Scanner scanner;
    private final Menu subMenu1 = new Menu("Student Registration Management | Student Management | Registration");
    private final Menu subMenu2 = new Menu("Student Registration Management | Student Management | About Student");
    private final Menu subMenu3 = new Menu("Student Registration Management | Student Management | Blacklist");

    public StudentManagementUI() {
        this.scanner = new Scanner(System.in);
        menu.add("Registration");
        menu.add("About Student");
        menu.add("Blacklist");
        menu.add("Exit");

        subMenu1.add("Add New Registrant");
        subMenu1.add("Add New Student From Registrant Queue");
        subMenu1.add("Add New Student Manually");
        subMenu1.add("Rejected Registrant");
        subMenu1.add("Set Registration Status");
        subMenu1.add("Exit");

        subMenu2.add("Show Student List");
        subMenu2.add("Amend Student Details");
        subMenu2.add("Exit");

        subMenu3.add("Show Blacklist");
        subMenu3.add("Add Student to Blacklist");
        subMenu3.add("Remove Student from Blacklist");
        subMenu3.add("Remove Student");
        subMenu3.add("Exit");
    }

    public int studentManagementMenu() {
        return menu.displayAndGetChoice();
    }

    public int registrationMenu() {
        return subMenu1.displayAndGetChoice();
    }

    public Registrant inputRegistrantDetails(DoublyLinkedList<Programme> programmeList) {
        this.scanner = new Scanner(System.in);
        String name, email, chosenProgram, gender;
        double highSchoolResult;
        
        System.out.println("= Registrant Details =");
        while (true) {
            System.out.print("Registrant Name  : ");
            name = scanner.nextLine().toUpperCase();
            if (name.matches("^[a-zA-Z ]+$") && name.length() < 25) {
                break;
            } else {
                UI.message("Invalid name format.");
                System.out.print("Press 'R' to retry or ELSE to quit: ");
                String decision = scanner.nextLine().trim().toUpperCase();
                if (!decision.equals("R")) {
                    return null;
                }
            }
        }

        while (true) {
            System.out.print("Registrant Email : ");
            email = scanner.nextLine().trim();
            if (isValidEmail(email)) {
                break;
            } else {
                UI.message("Invalid email format.");
                System.out.print("Press 'R' to retry or ELSE to quit: ");
                String decision = scanner.nextLine().trim().toUpperCase();
                if (!decision.equals("R")) {
                    return null;
                }
            }
        }

        System.out.println("\nProgramme List   :");
        for (Programme e : programmeList) {
            System.out.println("- " + e.getProgrammeId() + ": " + e.getProgrammeName());
        }
        while (true) {
            System.out.print("Chosen Program ID: ");
            chosenProgram = scanner.nextLine().trim().toUpperCase();
            boolean validProgramme = false;
            for (Programme e : programmeList) {
                if (e.getProgrammeId().equals(chosenProgram)) {
                    validProgramme = true;
                    break;
                }
            }
            if (validProgramme) {
                break;
            } else {
                UI.message("Invalid program.");
                System.out.print("Press 'R' to retry or ELSE to quit: ");
                String decision = scanner.nextLine().trim().toUpperCase();
                if (!decision.equals("R")) {
                    return null;
                }
            }
        }

        while (true) {
            System.out.print("\nGender (M/F)     : ");
            gender = scanner.nextLine().trim().toUpperCase();
            if (gender.equals("M") || gender.equals("F")) {
                break;
            } else {
                UI.message("Invalid gender.");
                System.out.print("Press 'R' to retry or ELSE to quit: ");
                String decision = scanner.nextLine().trim().toUpperCase();
                if (!decision.equals("R")) {
                    return null;
                }
            }
        }

        while (true) {
            System.out.print("High School CGPA : ");
            try {
                highSchoolResult = Double.parseDouble(scanner.nextLine());
                if (highSchoolResult >= 0 && highSchoolResult <= 4.0) {
                    break;
                } else {
                    System.out.println("Invalid CGPA.");
                    System.out.print("Press 'R' to retry or ELSE to quit: ");
                    String decision = scanner.nextLine().trim().toUpperCase();
                    if (!decision.equals("R")) {
                        return null;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("Press 'R' to retry or ELSE to quit: ");
                String decision = scanner.nextLine().trim().toUpperCase();
                if (!decision.equals("R")) {
                    return null;
                }
            }
        }

        LocalDate registrationDate = LocalDate.now();

        System.out.print("Registration Fee Paid  (Y/Else): ");
        String yes = scanner.nextLine().trim().toUpperCase();
        boolean regisFeeStatus = yes.equals("Y");

        return new Registrant(name, email, chosenProgram, registrationDate, gender, highSchoolResult, regisFeeStatus);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Student approveRegistrant(Registrant commingRegistrant) {
        Student newStudent = null;
        UI.nextSlide();
        System.out.println(commingRegistrant.details());
        System.out.print("Appove registrant? (Y/Else): ");
        String confirm = scanner.nextLine().trim().toUpperCase();
        UI.nextSlide();
        if (confirm.equals("Y")) {
            newStudent = new Student(commingRegistrant);
            System.out.println(newStudent.details());
            UI.message("Registrant Approved.");
            return newStudent;
        } else {
            UI.message("Registrant Rejected.");
            return newStudent;
        }
    }

    public boolean nextStudent() {
        System.out.print("Continue? (Y/Else): ");
        scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim().toUpperCase();
        return confirm.equals("Y");
    }

    public void showStudentList(DoublyLinkedList<Student> studentList) {
        ArrayList<Student> sortedStudentList = new ArrayList<>();
        for(int i = 0; i < studentList.size(); i++){
            sortedStudentList.add(studentList.get(i));
        }
        boolean exit = false;
        while (!exit) {
            sortedStudentList.sort();
            System.out.println("*Sorted by registered date*");
            System.out.println("[1]All\n[2]Recent Ten(10)\n[3]Oldest Ten(10)\n[4]Fail Registration Fee Payment\n[5]Fail Tuition Fee Payment\n[Else]Exit");
            System.out.print("Enter your selection: ");
            String select = scanner.nextLine();
            switch (select) {
                case "1" -> {
                    UI.nextSlide();
                    sortedStudentList.sort();
                    System.out.println("All Students:");
                    System.out.println("=========================================================================");
                    System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
                    System.out.println("=========================================================================");
                    System.out.print(sortedStudentList);
                    System.out.println("=========================================================================");
                }
                case "2" -> {
                    UI.nextSlide();
                    sortedStudentList.reverse();
                    DoublyLinkedList<Student> topTenStudentList = new DoublyLinkedList<>();
                    for (int i = 0; i < 10; i++) {
                        topTenStudentList.add(sortedStudentList.get(i));
                    }
                    System.out.println("Recent Ten Students:");
                    System.out.println("=========================================================================");
                    System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
                    System.out.println("=========================================================================");
                    System.out.print(topTenStudentList);
                    System.out.println("=========================================================================");
                }
                case "3" -> {
                    UI.nextSlide();
                    sortedStudentList.sort();
                    DoublyLinkedList<Student> bottomTenStudentList = new DoublyLinkedList<>();
                    for (int i = 0; i < 10; i++) {
                        bottomTenStudentList.add(sortedStudentList.get(i));
                    }
                    System.out.println("Oldest Ten Students:");
                    System.out.println("=========================================================================");
                    System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
                    System.out.println("=========================================================================");
                    System.out.print(bottomTenStudentList);
                    System.out.println("=========================================================================");
                }
                case "4" -> {
                    UI.nextSlide();
                    sortedStudentList.sort();
                    DoublyLinkedList<Student> unPaidStudentList = new DoublyLinkedList<>();
                    for (Student e : studentList) {
                        if (!(e.getRegisFeeStatus())) {
                            unPaidStudentList.add(e);
                        }
                    }
                    if (!unPaidStudentList.isEmpty()) {
                        System.out.println("Students who Fail Registration Fee Payment:");
                        System.out.println("=========================================================================");
                        System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
                        System.out.println("=========================================================================");
                        System.out.print(unPaidStudentList);
                        System.out.println("=========================================================================");
                    } else {
                        UI.message("Nothing.");
                        System.out.println("");
                    }

                }
                case "5" -> {
                    UI.nextSlide();
                    sortedStudentList.sort();
                    DoublyLinkedList<Student> unPaidStudentList = new DoublyLinkedList<>();
                    for (Student e : sortedStudentList) {
                        if (!(e.getTuitionFeeStatus())) {
                            unPaidStudentList.add(e);
                        }
                    }
                    if (!unPaidStudentList.isEmpty()) {
                        System.out.println("Students who Fail Tuition Fee Payment:");
                        System.out.println("=========================================================================");
                        System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
                        System.out.println("=========================================================================");
                        System.out.print(unPaidStudentList);
                        System.out.println("=========================================================================");
                    } else {
                        UI.message("Nothing.");
                        System.out.println("");
                    }
                }
                default ->
                    exit = true;
            }
        }
    }

    public boolean setToReject(boolean regisStatus) {
        scanner = new Scanner(System.in);
        showRegisStatus(regisStatus);
        System.out.print("Switch registration status? (Y/Else): ");
        String newStatus = scanner.nextLine().trim().toUpperCase();
        if (newStatus.equals("Y")) {
            return !regisStatus;
        } else {
            return regisStatus;
        }
    }

    public void showRegisStatus(boolean regisStatus) {
        if (regisStatus) {
            UI.message("Registration now is open.");
        } else {
            UI.message("Registration now is close.");
        }
    }

    public int aboutStudentMenu() {
        return subMenu2.displayAndGetChoice();
    }

    public Student getStudent(DoublyLinkedList<Student> studentList) {
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

    public boolean amendStudentDetails(Student s) {
        boolean edited = false;
        boolean exit = false;
        while (!exit) {
            UI.nextSlide();
            System.out.println(s.details());
            System.out.println("*Select edit section*");
            System.out.println("[1]Personal Information\n[2]Fee Payment Status\n[Else]Exit");
            System.out.print("Enter your selection: ");
            String select1 = scanner.nextLine();
            UI.nextSlide();
            switch (select1) {
                case "1" -> {
                    System.out.println("[1]Name\n[2]Email\n[Else]Back");
                    System.out.print("Enter your selection: ");
                    String select2 = scanner.nextLine();
                    switch (select2) {
                        case "1" -> {
                            UI.nextSlide();
                            System.out.println("Original Name: " + s.getName());
                            String newName;
                            while (true) {
                                System.out.print("Enter new name for student: ");
                                newName = scanner.nextLine().toUpperCase();
                                if (newName.matches("^[a-zA-Z ]+$") && newName.length() < 25) {
                                    break;
                                } else {
                                    UI.message("Invalid name format.");
                                    System.out.print("Press 'R' to retry or ELSE to quit: ");
                                    String decision = scanner.nextLine().trim().toUpperCase();
                                    if (!decision.equals("R")) {
                                        return false;
                                    }
                                }
                            }
                            UI.nextSlide();
                            System.out.println("Original: " + s.getName());
                            System.out.println("New     : " + newName);
                            System.out.print("Confirm modification? (Y/Else): ");
                            String confirm = scanner.nextLine().trim().toUpperCase();
                            if (confirm.equals("Y")) {
                                s.setName(newName);
                                edited = true;
                            }
                            exit = true;
                        }
                        case "2" -> {
                            UI.nextSlide();
                            System.out.println("Original Email: " + s.getEmail());
                            String newEmail;
                            while (true) {
                                System.out.print("Enter new email for student: ");
                                newEmail = scanner.nextLine().trim();
                                if (isValidEmail(newEmail)) {
                                    break;
                                } else {
                                    UI.message("Invalid email format.");
                                    System.out.print("Press 'R' to retry or ELSE to quit: ");
                                    String decision = scanner.nextLine().trim().toUpperCase();
                                    if (!decision.equals("R")) {
                                        return false;
                                    }
                                }
                            }
                            UI.nextSlide();
                            System.out.println("Original: " + s.getEmail());
                            System.out.println("New     : " + newEmail);
                            System.out.print("Confirm modification? (Y/Else): ");
                            String confirm = scanner.nextLine().trim().toUpperCase();
                            if (confirm.equals("Y")) {
                                s.setEmail(newEmail);
                                edited = true;
                            }
                            exit = true;
                        }
                    }
                }

                case "2" -> {
                    String paid;
                    System.out.println("[1]Registration Fee Payment Status\n[2]Tuition Fee Payment Status\n[Else]Back");
                    System.out.print("Enter your selection: ");
                    String select2 = scanner.nextLine();
                    switch (select2) {
                        case "1" -> {
                            UI.nextSlide();
                            if (s.getRegisFeeStatus()) {
                                paid = "Paid";
                            } else {
                                paid = "Unpaid";
                            }
                            System.out.println("Registration Fee Payment Status: " + paid);
                            System.out.print("Switch this payment status? (Y/Else): ");
                            String yes = scanner.nextLine().trim().toUpperCase();
                            if (yes.equals("Y")) {
                                s.setRegisFeeStatus(!s.getRegisFeeStatus());
                                edited = true;
                            }
                            exit = true;
                        }
                        case "2" -> {
                            UI.nextSlide();
                            if (s.getTuitionFeeStatus()) {
                                paid = "Paid";
                            } else {
                                paid = "Unpaid";
                            }
                            System.out.println("Tuition Fee Payment Status: " + paid);
                            System.out.print("Switch this payment status? (Y/Else): ");
                            String yes = scanner.nextLine().trim().toUpperCase();
                            if (yes.equals("Y")) {
                                s.setTuitionFeeStatus(!s.getTuitionFeeStatus());
                                edited = true;
                            }
                            exit = true;
                        }
                    }
                }

                default -> {
                    exit = true;
                }
            }
        }

        return edited;
    }

    public int blackList() {
        return subMenu3.displayAndGetChoice();
    }

    public void showBlackList(DoublyLinkedList<Student> blackList) {
        UI.nextSlide();
        blackList.sort();
        System.out.println("=========================================================================");
        System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
        System.out.print(blackList);
        System.out.println("=========================================================================");
        UI.pressToCont();
    }

    public Student addToBlackList(DoublyLinkedList<Student> studentList) {
        Student blackListStudent = null;
        studentList.sort();
        boolean potentialBlackList = false;
        DoublyLinkedList<Student> failToPayStudentList1 = new DoublyLinkedList<>();
        for (Student e : studentList) {
            if (!e.getRegisFeeStatus()) {
                if (e.getRegisFeeDueDate().isBefore(LocalDate.now())) {
                    failToPayStudentList1.add(e);
                }
            }
        }
        if (!failToPayStudentList1.isEmpty()) {
            System.out.println("Fail to pay registration fee: ");
            System.out.println("=========================================================================");
            System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
            System.out.println("=========================================================================");
            System.out.print(failToPayStudentList1);
            System.out.println("=========================================================================");
            potentialBlackList = true;
        } else {
            UI.message("No student fail to pay registration fee.");
        }
        DoublyLinkedList<Student> failToPayStudentList2 = new DoublyLinkedList<>();
        for (Student e : studentList) {
            if (!e.getTuitionFeeStatus()) {
                if (e.getTuitionFeeDueDate().isBefore(LocalDate.now())) {
                    failToPayStudentList2.add(e);
                }
            }
        }
        if (!failToPayStudentList2.isEmpty()) {
            System.out.println("Fail to pay tuition fee: ");
            System.out.println("=========================================================================");
            System.out.println(String.format("| %-20s | %-10s | %-10s | %-20s |", "Student Name", "Student ID", "Programme", "Registration Date"));
            System.out.println("=========================================================================");
            System.out.print(failToPayStudentList2);
            System.out.println("=========================================================================");
            potentialBlackList = true;
        } else {
            UI.message("No student fail to pay tuition fee.");
        }

        if (potentialBlackList) {
            DoublyLinkedList<Student> failToPayStudentList = DoublyLinkedList.combine(failToPayStudentList1, failToPayStudentList2);
            System.out.println("\n*Search for adding to blacklist*");
            blackListStudent = getStudent(failToPayStudentList);

        } else {
            UI.pressToCont();
        }
        return blackListStudent;
    }

    public void showRejectedRegistrant(DoublyLinkedList<Registrant> rejectedList) {
        UI.nextSlide();
        rejectedList.sort();
        System.out.println("===================================================================================");
        System.out.println(String.format("| %-20s | %-20s | %-10s | %-20s |", "Registrant Name", "Email", "Programme", "Registration Date"));
        System.out.println("===================================================================================");
        System.out.print(rejectedList);
        System.out.println("===================================================================================");
        UI.pressToCont();
    }
}
