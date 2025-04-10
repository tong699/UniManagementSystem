/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import adt.LinkedQueue;
import entity.Registrant;
import entity.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kong Jia Le
 */
public class StudentInitializer {

    public DoublyLinkedList<Student> initializeStudent(LinkedQueue<Registrant> registrantQueue) {
        DoublyLinkedList<Student> studentList = new DoublyLinkedList<>();
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        studentList.add(new Student(registrantQueue.dequeue()));
        
        studentList.get(0).setTuitionFee(2299.7);
        studentList.get(0).setTuitionFeeStatus(false);
        studentList.get(0).setTuitionFeeDueDate(LocalDate.parse("31-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        studentList.get(1).setTuitionFee(2299.7);
        studentList.get(1).setTuitionFeeStatus(false);
        studentList.get(1).setTuitionFeeDueDate(LocalDate.parse("28-04-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        studentList.get(2).setTuitionFee(1499.98);
        studentList.get(2).setTuitionFeeStatus(false);
        studentList.get(2).setTuitionFeeDueDate(LocalDate.parse("12-05-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        studentList.get(3).setTuitionFee(1499.98);
        studentList.get(3).setTuitionFeeStatus(false);
        studentList.get(3).setTuitionFeeDueDate(LocalDate.parse("23-05-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return studentList;
    }
}
