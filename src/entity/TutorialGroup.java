/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.DoublyLinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroup implements Comparable<TutorialGroup> {

    private static final AtomicInteger noGenerator = new AtomicInteger(0);
    private String groupId;
    private int capacity = 24;
    private Student groupRep;
    private DoublyLinkedList<Student> enrolledStudents;
    private String programmeId;

    public TutorialGroup(String programmeId) {
        this.programmeId = programmeId;
        this.groupId = generateGroupId(programmeId);
        this.enrolledStudents = new DoublyLinkedList<>();
        this.groupRep = null;
    }

    public TutorialGroup(String groupId, String programmeId) {
        this.groupId = groupId;
        this.groupRep = null;
        this.enrolledStudents = new DoublyLinkedList<>();
        this.programmeId = programmeId;
    }

    public String generateGroupId(String programmeId) {
        int no = noGenerator.incrementAndGet();
        return "G" + String.format("%04d",no);
    }

    public String getGroupId() {
        return groupId;
    }

    public int getCapacity() {
        return capacity;
    }

    public Student getGroupRep() {
        return groupRep;
    }

    public DoublyLinkedList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getProgrammeID() {
        return programmeId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setGroupRep(Student groupRep) {
        this.groupRep = groupRep;
    }

    public void setEnrolledStudents(DoublyLinkedList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    @Override
    public String toString() {
        String repName = (groupRep != null) ? groupRep.getName() : "No Group Representative";
        return String.format("| %-8s | %-10s | %-30s | %-20s | %-10s |", groupId, this.getCapacity(), repName, enrolledStudents.size(), programmeId);
    }

    @Override
    public int compareTo(TutorialGroup other) {
        return this.groupId.compareTo(other.groupId);
    }

    public String details() {
        StringBuilder sb = new StringBuilder();
        sb.append("             Tutorial Group Information\n");
        sb.append("/----------------------------------------------------\\\n");
        sb.append(String.format("|%-25s: %-25s|\n", " Group ID", groupId));
        sb.append(String.format("|%-25s: %-25s|\n", " Capacity", capacity));
        sb.append(String.format("|%-25s: %-25s|\n", " Group Representative", (groupRep != null) ? groupRep.getName() : "No Group Representative"));
        sb.append(String.format("|%-25s: %-25s|\n", " Students Enrolled", enrolledStudents.size()));
        sb.append(String.format("|%-25s: %-25s|\n", " Program", programmeId));
        sb.append("\\----------------------------------------------------/\n");
        return sb.toString();
    }

}
