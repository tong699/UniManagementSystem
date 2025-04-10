/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import entity.ProgrammeCourseAssociation;

/**
 *
 * @author Kong Jia Le
 */
public class FacultyInitializer {

    public DoublyLinkedList<Faculty> initializeFaculty() {
        DoublyLinkedList<Faculty> facultyList = new DoublyLinkedList<>();

        Faculty f1 = new Faculty("FOCS", "FACULTY OF COMPUTING AND INFORMATION TECHNOLOGY");
        Faculty f2 = new Faculty("FOET", "FACULTY OF ENGINEERING AND TECHNOLOGY");

        Programme programme1FOCS = new Programme("RDS", "Data Science");
        Programme programme2FOCS = new Programme("RSW", "Software Engineering");
        Programme programme1FOET = new Programme("REE", "Electrical And Electronics Engineering");
        Programme programme2FOET = new Programme("RME", "Mechanical Engineering");

        Course course1FOCS = new Course("BACS1111", "DATA SCIENCE", "MAIN", 777, 3);
        Course course2FOCS = new Course("BACS2222", "ARTIFICIAL INTELLIGENCE", "ELECTIVE", 777, 4);
        Course course3FOCS = new Course("BACS3333", "STATISTICS FOR DATA SCIENCE", "REPEAT", 777, 3);
        Course course4FOCS = new Course("BACS4444", "DATABASE MANAGEMENT", "MAIN", 1080, 3);
        Course course5FOCS = new Course("BACS5555", "CYBER SECURITY", "ELECTIVE", 777, 4);
        Course course6FOCS = new Course("BACS6666", "NATURAL LANGUAGE PROCESSING", "MAIN", 7777, 3);
        Course course1FOET = new Course("BAET1111", "BASIC ELECTRONIC", "MAIN", 777, 3);
        Course course2FOET = new Course("BAET2222", "ELECTRIC CIRCUITS", "ELECTIVE", 1080, 4);
        Course course3FOET = new Course("BAET3333", "ENGINEERING MATHEMATICS", "MAIN", 777, 3);
        Course course4FOET = new Course("BAET4444", "DIGITAL LOGIC DESIGN", "MAIN", 888, 3);
        Course course5FOET = new Course("BAET5555", "MICROPROCESSOR SYSTEM", "REPEAT", 1080, 3);
        Course course6FOET = new Course("BAET6666", "ELECTRICAL TECHNOLOGY ", "ELECTIVE", 777, 3);

        ProgrammeCourseAssociation association1FOCS = new ProgrammeCourseAssociation("RDS", "BACS1111", "MAIN");
        ProgrammeCourseAssociation association2FOCS = new ProgrammeCourseAssociation("RDS", "BACS2222", "ELECTIVE");
        ProgrammeCourseAssociation association3FOCS = new ProgrammeCourseAssociation("RDS", "BACS4444", "MAIN");
        ProgrammeCourseAssociation association4FOCS = new ProgrammeCourseAssociation("RSW", "BACS3333", "ELECTIVE");
        ProgrammeCourseAssociation association5FOCS = new ProgrammeCourseAssociation("RSW", "BACS5555", "REPEAT");
        ProgrammeCourseAssociation association6FOCS = new ProgrammeCourseAssociation("RSW", "BACS6666", "MAIN");
        ProgrammeCourseAssociation association1FOET = new ProgrammeCourseAssociation("REE", "BAET1111", "MAIN");
        ProgrammeCourseAssociation association2FOET = new ProgrammeCourseAssociation("REE", "BAET3333", "MAIN");
        ProgrammeCourseAssociation association3FOET = new ProgrammeCourseAssociation("REE", "BAET5555", "REPEAT");
        ProgrammeCourseAssociation association4FOET = new ProgrammeCourseAssociation("RME", "BAET2222", "ELECTIVE");
        ProgrammeCourseAssociation association5FOET = new ProgrammeCourseAssociation("RME", "BAET4444", "MAIN");
        ProgrammeCourseAssociation association6FOET = new ProgrammeCourseAssociation("RME", "BAET6666", "ELECTIVE");

        facultyList.add(f1);
        facultyList.add(f2);

        f1.getProgrammeList().add(programme1FOCS);
        f1.getProgrammeList().add(programme2FOCS);
        f2.getProgrammeList().add(programme1FOET);
        f2.getProgrammeList().add(programme2FOET);

        f1.getCourseList().add(course1FOCS);
        f1.getCourseList().add(course2FOCS);
        f1.getCourseList().add(course3FOCS);
        f1.getCourseList().add(course4FOCS);
        f1.getCourseList().add(course5FOCS);
        f1.getCourseList().add(course6FOCS);
        f2.getCourseList().add(course1FOET);
        f2.getCourseList().add(course2FOET);
        f2.getCourseList().add(course3FOET);
        f2.getCourseList().add(course4FOET);
        f2.getCourseList().add(course5FOET);
        f2.getCourseList().add(course6FOET);

        f1.getProgrammeCourseList().add(association1FOCS);
        f1.getProgrammeCourseList().add(association2FOCS);
        f1.getProgrammeCourseList().add(association3FOCS);
        f1.getProgrammeCourseList().add(association4FOCS);
        f1.getProgrammeCourseList().add(association5FOCS);
        f1.getProgrammeCourseList().add(association6FOCS);
        f2.getProgrammeCourseList().add(association1FOET);
        f2.getProgrammeCourseList().add(association2FOET);
        f2.getProgrammeCourseList().add(association3FOET);
        f2.getProgrammeCourseList().add(association4FOET);
        f2.getProgrammeCourseList().add(association5FOET);
        f2.getProgrammeCourseList().add(association6FOET);

        return facultyList;
    }
}
