/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author Kong Jia Le
 */
public class StudentCourseAssociation implements Comparable<StudentCourseAssociation> {

    private String studentId;
    private String courseCode;
    private String courseType;
    private double price;
    private final LocalDate registerDate;
    private LocalDate removedDate = null;

    public StudentCourseAssociation(String studentId, String courseCode, String courseType, double price) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.courseType = courseType;
        this.price = price;
        this.registerDate = LocalDate.now();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public LocalDate getRemovedDate() {
        return removedDate;
    }

    public void setRemovedDate(LocalDate removedDate) {
        this.removedDate = removedDate;
    }

    @Override
    public int compareTo(StudentCourseAssociation o) {
        return this.registerDate.compareTo(o.registerDate);
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-15s | %-10s | %-10s | %-15s | %-15s |", studentId, courseCode, courseType, price, registerDate, removedDate);
    }

}
