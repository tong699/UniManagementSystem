/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class Student extends Registrant {

    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private static int numberOfStudent = 0;
    private String studentId;
    private final LocalDate admissionDate;
    private double tuitionFee;
    private boolean tuitionFeeStatus;
    private LocalDate tuitionFeeDueDate = null;
    

    // Constructor
    public Student(String name, String email, String chosenProgramme, LocalDate registrationDate, String gender, double highSchoolResult, boolean regisFeeStatus) {
        super(name, email, chosenProgramme, registrationDate, gender, highSchoolResult, regisFeeStatus);
        this.studentId = generateStudentId();
        this.admissionDate = LocalDate.now();
        this.tuitionFee = 0;
        this.tuitionFeeStatus = true;
    }

    public Student(Registrant registrant) {
        super(registrant.getName(), registrant.getEmail(), registrant.getChosenProgramme(), registrant.getRegistrationDate(), registrant.getGender(), registrant.getHighSchoolResult(), registrant.getRegisFeeStatus());
        this.studentId = generateStudentId();
        this.admissionDate = LocalDate.now();
        this.tuitionFee = 0;
        this.tuitionFeeStatus = true;
    }

    private String generateStudentId() {
        int id = idGenerator.incrementAndGet();
        numberOfStudent++;
        return "S" + String.format("%06d", id);
    }

    // Getters and setters
    public static int getNumberOfStudent() {
        return numberOfStudent;
    }

    public String getStudentId() {
        return studentId;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public boolean getTuitionFeeStatus() {
        return tuitionFeeStatus;
    }

    public LocalDate getTuitionFeeDueDate() {
        return tuitionFeeDueDate;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public void setTuitionFeeStatus(boolean tuitionFeeStatus) {
        this.tuitionFeeStatus = tuitionFeeStatus;
    }

    public void setTuitionFeeDueDate(LocalDate tuitionFeeDueDate) {
        this.tuitionFeeDueDate = tuitionFeeDueDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return Objects.equals(this.studentId, other.studentId);
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-10s | %-10s | %-20s |", this.getName(), studentId, this.getChosenProgramme(), this.getRegistrationDate());
    }

    @Override
    public String details() {
        StringBuilder sb = new StringBuilder();
        sb.append("               [Student Information]\n");
        sb.append("|=================================================\\\n");
        sb.append(String.format("|%-25s: %-22s|\n", " Name", this.getName()));
        sb.append(String.format("|%-25s: %-22s|\n", " Email", UI.truncateString(this.getEmail(), 20)));
        sb.append(String.format("|%-25s: %-22s|\n", " Chosen Program", this.getChosenProgramme()));
        sb.append(String.format("|%-25s: %-22s|\n", " Registration Date", this.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        sb.append(String.format("|%-25s: %-22s|\n", " Gender", this.getGender()));
        sb.append(String.format("|%-25s: %-22s|\n", " High School Result", String.format("%.1f", this.getHighSchoolResult())));
        if (this.getRegisFeeStatus()) {
            sb.append(String.format("|%-25s: %-22s|\n", " Registration Fee Payment", "Paid"));
        } else {
            sb.append(String.format("|%-25s: %-22s|\n", " Registration Fee Payment", "Unpaid"));
        }
        sb.append(String.format("|%-25s: %-22s|\n", " Student ID", studentId));
        sb.append(String.format("|%-25s: $%-21s|\n", " Tuition Fee", String.format("%.2f", tuitionFee)));
        if (tuitionFeeStatus) {
            sb.append(String.format("|%-25s: %-22s|\n", " Tuition Fee Payment", "Paid"));
        } else {
            sb.append(String.format("|%-25s: %-22s|\n", " Tuition Fee Payment", "Unpaid"));
        }
        sb.append("\\=================================================|\n");
        return sb.toString();
    }
}
