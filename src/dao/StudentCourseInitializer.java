/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.StudentCourseAssociation;

/**
 *
 * @author Kong Jia Le
 */
public class StudentCourseInitializer {

    public DoublyLinkedList<StudentCourseAssociation> initializeStudentCourse(){
        DoublyLinkedList<StudentCourseAssociation> studentCourseList = new DoublyLinkedList<>();
        studentCourseList.add(new StudentCourseAssociation("S000001", "BACS1111", "main", 777));
        studentCourseList.add(new StudentCourseAssociation("S000001", "BACS2222", "elective", 777));
        studentCourseList.add(new StudentCourseAssociation("S000001", "BACS4444", "main", 1080));
        studentCourseList.add(new StudentCourseAssociation("S000002", "BACS1111", "main", 777));
        studentCourseList.add(new StudentCourseAssociation("S000002", "BACS2222", "elective", 777));
        studentCourseList.add(new StudentCourseAssociation("S000002", "BACS4444", "main", 1080));
        studentCourseList.add(new StudentCourseAssociation("S000003", "BACS4444", "main", 1080));
        studentCourseList.add(new StudentCourseAssociation("S000003", "BACS5555", "elective", 777));
        studentCourseList.add(new StudentCourseAssociation("S000004", "BACS4444", "main", 1080));
        studentCourseList.add(new StudentCourseAssociation("S000004", "BACS5555", "elective", 777));
        
        return studentCourseList;
    }
}
