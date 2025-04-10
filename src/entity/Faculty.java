/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.DoublyLinkedList;

/**
 *
 * @author jerry
 */
public class Faculty {

    private String facultyID;
    private String facultyName;
    private final DoublyLinkedList<Course> courseList = new DoublyLinkedList<>();
    private final DoublyLinkedList<String> courseMaterialList = new DoublyLinkedList<>();
    private final DoublyLinkedList<Programme> programmeList = new DoublyLinkedList<>();
    private final DoublyLinkedList<ProgrammeCourseAssociation> programmeCourseList = new DoublyLinkedList<>();
    
    public Faculty(String facultyID, String facultyName){
        this.facultyID = facultyID;
        this.facultyName = facultyName;             
    }

    public String getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(String facultyID) {
        this.facultyID = facultyID;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public DoublyLinkedList<Course> getCourseList() {
        return courseList;
    }

    public DoublyLinkedList<Programme> getProgrammeList() {
        return programmeList;
    }

    public DoublyLinkedList<ProgrammeCourseAssociation> getProgrammeCourseList() {
        return programmeCourseList;
    }

    public DoublyLinkedList<String> getCourseMaterialList() {
        return courseMaterialList;
    }
}