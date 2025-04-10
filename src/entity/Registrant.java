/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import utility.UI;

/**
 *
 * @author Kong Jia Le
 */
public class Registrant implements Comparable<Registrant> {

    private static double defaultRegisFee = 60.00;
    private String name;
    private String email;
    private String chosenProgramme;
    private LocalDate registrationDate;
    private String gender;
    private double highSchoolResult;
    private final double registrationFee;
    private boolean regisFeeStatus;
    private final LocalDate regisFeeDueDate;

    // Constructor
    public Registrant(String name, String email, String chosenProgramme, LocalDate registrationDate, String gender, double highSchoolResult, boolean regisFeeStatus) {
        this.name = name;
        this.email = email;
        this.chosenProgramme = chosenProgramme;
        this.registrationDate = registrationDate;
        this.gender = gender;
        this.highSchoolResult = highSchoolResult;
        this.registrationFee = defaultRegisFee;
        this.regisFeeStatus = regisFeeStatus;
        this.regisFeeDueDate = registrationDate.plusDays(14);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getChosenProgramme() {
        return chosenProgramme;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getGender() {
        return gender;
    }

    public double getHighSchoolResult() {
        return highSchoolResult;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public boolean getRegisFeeStatus() {
        return regisFeeStatus;
    }

    public LocalDate getRegisFeeDueDate() {
        return regisFeeDueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChosenProgram(String chosenProgramme) {
        this.chosenProgramme = chosenProgramme;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHighSchoolResult(double highSchoolResult) {
        this.highSchoolResult = highSchoolResult;
    }

    public void setRegisFeeStatus(boolean regisFeeStatus) {
        this.regisFeeStatus = regisFeeStatus;
    }

    public static void setDefaultRegisFee(double defaultRegisFee) {
        Registrant.defaultRegisFee = defaultRegisFee;
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
        final Registrant other = (Registrant) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int compareTo(Registrant o) {
        return this.registrationDate.compareTo(o.registrationDate);
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-20s | %-10s | %-20s |", name, UI.truncateString(email, 20), chosenProgramme, registrationDate);
    }

    public String details() {
        StringBuilder sb = new StringBuilder();
        sb.append("              [Registrant Information]\n");
        sb.append("|=================================================\\\n");
        sb.append(String.format("|%-25s: %-22s|\n", " Name", name));
        sb.append(String.format("|%-25s: %-22s|\n", " Email", UI.truncateString(email, 20)));
        sb.append(String.format("|%-25s: %-22s|\n", " Chosen Program", chosenProgramme));
        sb.append(String.format("|%-25s: %-22s|\n", " Registration Date", registrationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        sb.append(String.format("|%-25s: %-22s|\n", " Gender", gender));
        sb.append(String.format("|%-25s: %-22s|\n", " High School Result", String.format("%.1f", highSchoolResult)));
        if (regisFeeStatus) {
            sb.append(String.format("|%-25s: %-22s|\n", " Registration Fee Payment", "Paid"));
        } else {
            sb.append(String.format("|%-25s: %-22s|\n", " Registration Fee Payment", "Unpaid"));
        }
        sb.append("\\=================================================|\n");
        return sb.toString();
    }

}
