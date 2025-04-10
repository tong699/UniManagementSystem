/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.DoublyLinkedList;
import entity.Tutor;
import entity.TutorCourseAssociation;
import entity.TutorTutorialGrpAssociation;
import utility.Menu;

/**
 *
 * @author Chan Wei Xin
 */
public class TeachingAssignmentReportUI {

    private final Menu menu = new Menu("Teaching Assignment Management | Teaching Assignment Management Report");

    public TeachingAssignmentReportUI() {
        menu.add("Tutor Course Association Summary Report");
        menu.add("Tutor workload summary report");
        menu.add("Exit");
    }

    public int TeachingAssignmentReportUIMenu() {
        return menu.displayAndGetChoice();
    }

    public void displayTutorCourseAssociationSummary(DoublyLinkedList<Tutor> tutorList, DoublyLinkedList<TutorCourseAssociation> tutorCourseList) {
        System.out.println("\n|--------------- Tutor-Course Association Summary ---------------|\n");

        // List of Tutors and Courses
        System.out.println("List of Tutors and Courses:");
        for (Tutor tutor : tutorList) {
            System.out.println("Tutor ID: " + tutor.getTutorId());
            System.out.println("-----------------------");
            System.out.println("Courses:");
            for (TutorCourseAssociation e : tutorCourseList) {
                if (e.getTutorId().equals(tutor.getTutorId())) {
                    System.out.println("- " + e.getCourseCode() + " (" + e.getTutorType() + ")");
                }
            }
            System.out.println();
        }

        int totalTutors = tutorList.size();
        int totalCourses = tutorCourseList.size();
        System.out.println("\n======================================================");
        System.out.println("--- Tutor Statistics ---");
        System.out.println("Total Tutors: " + totalTutors);
        System.out.println("Total Courses: " + totalCourses);
        

        System.out.println("\nCourse Coverage Analysis:");

        System.out.println("Courses Covered by Tutors:");
        for (int i = 0; i < tutorCourseList.size(); i++) {
            String courseCode = tutorCourseList.get(i).getCourseCode();
            boolean alreadyPrinted = false;
            // Check if the course code has already been printed
            for (int j = 0; j < i; j++) {
                if (tutorCourseList.get(j).getCourseCode().equals(courseCode)) {
                    alreadyPrinted = true;
                    break;
                }
            }

            if (!alreadyPrinted) {
                int tutorCoverage = countCourseCoverage(tutorCourseList, courseCode);
                System.out.println("-> " + courseCode + ", Tutors Assigned: " + tutorCoverage);
            }
        }
        System.out.println("=======================================================");
    }

    private int countCourseCoverage(DoublyLinkedList<TutorCourseAssociation> tutorCourseList, String courseCode) {
        int tutorCoverage = 0;
        for (TutorCourseAssociation e : tutorCourseList) {
            if (e.getCourseCode().equals(courseCode)) {
                tutorCoverage++;
            }
        }
        return tutorCoverage;
    }
    
    
    
    
    


    public void displayTutorPerformanceReport(DoublyLinkedList<Tutor> tutorList, DoublyLinkedList<TutorCourseAssociation> tutorCourseList, DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList) {
        System.out.println("\n|--------------- Tutor Performance Analysis ---------------|\n");


        System.out.println("=========================================================\n");
        for (Tutor tutor : tutorList) {
            String tutorId = tutor.getTutorId();
            System.out.println("Tutor ID: " + tutorId);
            
            System.out.println("Tutor Name: " + tutor.getTutorName());
            int coursesAssigned = countCoursesAssigned(tutorId, tutorCourseList);
            System.out.println("Number of Courses Assigned: " + coursesAssigned);
            int tutorialGroupsAssigned = countTutorialGroupsAssigned(tutorId, tutorialGrpsTutorList);
            System.out.println("Number of Tutorial Groups Assigned: " + tutorialGroupsAssigned);
            System.out.println("\n=========================================================\n"); // Empty line for separation
        }
    }


    private int countCoursesAssigned(String tutorId, DoublyLinkedList<TutorCourseAssociation> tutorCourseList) {
        int count = 0;
        for (TutorCourseAssociation association : tutorCourseList) {
            if (association.getTutorId().equals(tutorId)) {
                count++;
            }
        }
        return count;
    }


    private int countTutorialGroupsAssigned(String tutorId, DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList) {
        int count = 0;
        for (TutorTutorialGrpAssociation association : tutorialGrpsTutorList) {
            if (association.getTutorId().equals(tutorId)) {
                count++;
            }
        }
        return count;
    }
}
