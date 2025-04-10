/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.CourseTutorManagementUI;
import entity.Course;
import entity.Tutor;
import entity.TutorCourseAssociation;
import entity.TutorialGroup;
import entity.TutorialGrpCourseAssociation;
import utility.UI;

/**
 *
 * @author Chan Wei Xin
 */
public class CourseTutorManagement {

    private static final CourseTutorManagementUI ui = new CourseTutorManagementUI();
    private final DoublyLinkedList<Tutor> tutorList;
    private final DoublyLinkedList<TutorCourseAssociation> tutorCourseList;
    private final DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList;
    private final DoublyLinkedList<TutorialGroup> tutorialGroupList;
    private final DoublyLinkedList<Course> courseList;

    public CourseTutorManagement(DoublyLinkedList<Tutor> tutorList, 
            DoublyLinkedList<TutorCourseAssociation> tutorCourseList, 
            DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpCourseList, 
            DoublyLinkedList<TutorialGroup> tutorialGroupList, 
            DoublyLinkedList<Course> courseList) {
        this.tutorList = tutorList;
        this.tutorCourseList = tutorCourseList;
        this.tutorialGrpCourseList = tutorialGrpCourseList;
        this.tutorialGroupList = tutorialGroupList;
        this.courseList = courseList;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.CourseTutorManagementMenu()) {
                case 1 ->
                    AssignTutorToCourse();
                case 2 ->
                    AssignTutorToTutGrp();
                case 3 ->
                    SearchTutorCourse();
                case 4 ->
                    ListTutorsAndTutorialGrp();
                case 5 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void AssignTutorToCourse() {
        if (!tutorList.isEmpty() && !courseList.isEmpty()) {
            UI.nextSlide();
            UI.title("Tutor List Contents:");
            for (Tutor tutor : tutorList) {
                
                System.out.println(tutor.getTutorId());
            }

            Tutor checkTutor = ui.getTutorId(tutorList);
            if (checkTutor != null) {
                // Print out course list contents
                UI.nextSlide();
                UI.title("Course List Contents:");
                for (Course course : courseList) {
                    System.out.println(course.getCourseCode());
                }

                Course checkCourse = ui.getCourseId(courseList);
                if (checkCourse != null) {
                    boolean alreadyAssigned = false;
                    for (TutorCourseAssociation e : tutorCourseList) {
                        if (e.getCourseCode().equals(checkCourse.getCourseCode())) {
                            if (e.getTutorId().equals(checkTutor.getTutorId())) {
                                alreadyAssigned = true;
                                break;
                            }
                        }
                    }
                    if (alreadyAssigned) {
                        UI.nextSlide();
                        UI.message("This tutor is already assigned to this course.");
                    } else {
                        TutorCourseAssociation addTutorCourse = 
                                ui.inputTutorCourse(checkTutor.getTutorId(), 
                                        checkCourse.getCourseCode());
                        if (addTutorCourse == null) {
                            UI.nextSlide();
                            UI.message("Invalid assign tutor."); 
                        } else {
                            UI.nextSlide();
                            if (UI.confirmUI(addTutorCourse.details())) {
                                tutorCourseList.add(addTutorCourse);
                            } else {
                                UI.nextSlide();
                                UI.message("Assign tutor cancelled.");
                            }
                        }
                    }
                } else {
                    UI.nextSlide();
                    UI.message("This course does not exist.");
                }
            } else {
                UI.nextSlide();
                UI.message("Tutor not exist.");
            }
        } else {
            UI.nextSlide();
            UI.message("No tutor or course in the system.");
        }
    }

    private void AssignTutorToTutGrp() {
        DoublyLinkedList<TutorialGrpCourseAssociation> tutorialGrpWithoutTutor;
        if (!tutorList.isEmpty()) {
            if (!tutorialGrpCourseList.isEmpty()) {
                UI.nextSlide();
                ui.displayTutorialGrpCourseList(tutorialGrpCourseList);
                TutorialGroup selectedTutorialGroup = ui.getTutorialGroup(tutorialGroupList);


                if (selectedTutorialGroup != null) {
                    UI.nextSlide();
                    UI.title("List tutor course Contents:");
                    System.out.println("Tutor id         Course code");
                    for (TutorCourseAssociation tutorCourse : tutorCourseList) {
                        System.out.println(tutorCourse.getTutorId() + "               " + 
                                tutorCourse.getCourseCode());
                    }
                    // check if tutorial group already has a tutor assigned
                    tutorialGrpWithoutTutor = ui.getTutorialGrpTutor(tutorialGrpCourseList, 
                            selectedTutorialGroup.getGroupId());
                    if (!tutorialGrpWithoutTutor.isEmpty()) {
                        // input course code and tutor type 
                        TutorialGrpCourseAssociation addTutorToTutGrp =
                                ui.inputTutorsTutorialGrp(selectedTutorialGroup.getGroupId());
                        boolean validTutor = false;
                        for (TutorCourseAssociation e : tutorCourseList) {
                            if (e.getCourseCode().equals(addTutorToTutGrp.getCourseCode()) 
                                    && e.getTutorId().equals(addTutorToTutGrp.getTutorid())) {
                                validTutor = true;
                                break;
                            }
                        }
                        if (validTutor) {
                            if (addTutorToTutGrp == null) {
                                UI.nextSlide();
                                UI.message("Invalid asign tutor.");
                            } else {
                                boolean addTutor = false;
                                for (TutorialGrpCourseAssociation e : tutorialGrpWithoutTutor) {
                                    if (e.getCourseCode()
                                            .equals(addTutorToTutGrp.getCourseCode()) 
                                            && e.getGroupId()
                                                    .equals(addTutorToTutGrp.getGroupId())) {
                                        UI.nextSlide();
                                        if (UI.confirmUI(addTutorToTutGrp.details())) {
                                            tutorialGrpCourseList
                                                    .add(addTutorToTutGrp);
                                            tutorialGrpCourseList
                                                    .remove(tutorialGrpCourseList.positionOf(e));
                                        } else {
                                            UI.message("Assign tutor cancelled.");
                                        }
                                        addTutor = true;
                                        break;
                                    }

                                }
                                if (!addTutor) {
                                    UI.nextSlide();
                                    UI.message("Invalid tutor to tutorial group assignment.");
                                }
                            }
                        } else {
                            UI.nextSlide();
                            UI.message("This tutor is invalid for this course.");
                        }

                    } else {
                        UI.nextSlide();
                        UI.message("Invalid tutor to tutorial group assignment.");
                    }
                } else {
                    UI.nextSlide();
                    UI.message("Tutorial group not found.");
                }
            } else {
                UI.nextSlide();
                UI.message("No tutorial group in the list.");
            }
        } else {
            UI.nextSlide();
            UI.message("No tutors in the list.");
        }
    }

    private void SearchTutorCourse() {
        boolean exit = false;
        while (!exit) {
            switch (ui.SearchTutorCourseMenu()) {
                case 1 -> {
                    UI.title("List course code contents:");
                    for (int i = 0; i < tutorCourseList.size(); i++) {
                        String currentCourseCode = tutorCourseList.get(i).getCourseCode();
                        boolean alreadyPrinted = false;
                        // check if the course code has already been printed
                        for (int j = 0; j < i; j++) {
                            if (tutorCourseList.get(j).getCourseCode()
                                    .equals(currentCourseCode)) {
                                alreadyPrinted = true;
                                break;
                            }
                        }
                        if (!alreadyPrinted) {
                            System.out.println(currentCourseCode);
                        }
                    }

                    DoublyLinkedList<TutorCourseAssociation> tutorCourse = 
                            ui.getTutorsFromCourse(tutorCourseList);
                    UI.nextSlide();
                    if (!tutorCourse.isEmpty()) {
                        for (TutorCourseAssociation tutor : tutorCourse) {
                            // display course details including tutor ID and tutor type
                            System.out.println("Tutor Id: " + tutor.getTutorId());
                            System.out.println("Tutor Type: " + tutor.getTutorType());
                            System.out.println();
                        }
                    } else {
                        System.out.println("No courses found for the specified tutor.");
                    }
                }
                case 2 -> {
                    UI.nextSlide();
                    ui.displayTutorCourseList(tutorCourseList);
                }
                case 3 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void ListTutorsAndTutorialGrp() {
        UI.nextSlide();
        ui.displayTutorialGrpCourseList(tutorialGrpCourseList);
    }
}
