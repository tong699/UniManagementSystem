/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-defau
lt.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.TutorManagementUI;
import entity.Tutor;
import entity.TutorCourseAssociation;
import entity.TutorTutorialGrpAssociation;
import entity.TutorialGroup;
import utility.UI;

/**
 *
 * @author Chan Wei Xin
 */
public class TutorManagement {

    private static final TutorManagementUI ui = new TutorManagementUI();
    private final DoublyLinkedList<Tutor> tutorList;
    private final DoublyLinkedList<TutorialGroup> tutorialGroupList;
    private final DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList;
    private final DoublyLinkedList<TutorCourseAssociation> tutorCourseList;

    public TutorManagement(DoublyLinkedList<Tutor> tutorList, 
            DoublyLinkedList<TutorialGroup> tutorialGroupList,
            DoublyLinkedList<TutorTutorialGrpAssociation> tutorialGrpsTutorList,
            DoublyLinkedList<TutorCourseAssociation> tutorCourseList) {
        this.tutorList = tutorList;
        this.tutorialGroupList = tutorialGroupList;
        this.tutorialGrpsTutorList = tutorialGrpsTutorList;
        this.tutorCourseList = tutorCourseList;

    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.TutorManagementMenu()) {
                case 1 ->
                    AssignTutorialGrpsTutor();

                case 2 ->
                    ListTutorialGrpsTutor();

                case 3 ->
                    SearchCourses();

                case 4 ->
                    FilterTutor();

                case 5 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void AssignTutorialGrpsTutor() {
        UI.nextSlide();
        System.out.println("\nTutor List Contents:");
        for (Tutor tutor : tutorList) {
            System.out.println(tutor.getTutorId());
        }
        Tutor checkTutor = ui.getTutor(tutorList);
        
        if (checkTutor != null) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nList tutorial group Contents:");
                for (TutorialGroup tutorialGroup : tutorialGroupList) {
                    System.out.println(tutorialGroup.getGroupId());
                }
                TutorialGroup checkTutorialGrp = ui.getTutorialGroup(tutorialGroupList);
                if (checkTutorialGrp != null) {
                    boolean alreadyAssigned = false;
                    for (TutorTutorialGrpAssociation association : tutorialGrpsTutorList) {
                        if (association.getTutorId().equals(checkTutor.getTutorId())
                                && association.getGroupId().equals(checkTutorialGrp.getGroupId())) {
                            alreadyAssigned = true;
                            break;
                        }
                    }
                    if (alreadyAssigned) {
                        UI.nextSlide();
                        UI.message("This tutor is already assigned to this tutorial group.");
                        UI.nextSlide();
                    } else {
                        TutorTutorialGrpAssociation addTutorialGrpsToTutor = 
                                ui.inputTutorialGrpsTutor(checkTutorialGrp.getGroupId(), 
                                        checkTutor.getTutorId());
                        if (addTutorialGrpsToTutor == null) {
                            UI.nextSlide();
                            UI.message("Invalid add tutor.");
                            UI.nextSlide();
                        } else {
                            UI.nextSlide();
                            if (UI.confirmUI(addTutorialGrpsToTutor.details())) {
                                tutorialGrpsTutorList.add(addTutorialGrpsToTutor);
                                exit = !UI.confirmUI("Do you want to assign more"
                                        + " tutorial groups to this tutor?");
                            } else {
                                UI.message("Assign tutor cancelled.");
                                exit = true;
                            }
                        }
                    }
                } else {
                    UI.nextSlide();
                    UI.message("This Tutorial Group does not exist.");
                    exit = true;
                }
            }
        } else {
            UI.nextSlide();
            
            UI.message("This tutor does not exist.");
        }
    }

    private void ListTutorialGrpsTutor() {
        UI.nextSlide();
        ui.displayTutorialGrpTutorList(tutorialGrpsTutorList);
    }

    private void SearchCourses() {
        boolean exit = false;
        while (!exit) {
            switch (ui.SearchCoursesMenu()) {
                case 1 -> {
                    UI.nextSlide();
                    System.out.println("\nTutor List Contents:");
                    for (Tutor tutor : tutorList) {
                        System.out.println(tutor.getTutorId());
                    }
                    DoublyLinkedList<TutorCourseAssociation> courses = 
                            ui.getCourses(tutorCourseList);
                    if (!courses.isEmpty()) {
                        for (TutorCourseAssociation course : courses) {
                            System.out.println("\nCourse Code: " + course.getCourseCode());
                            System.out.println("Tutor Type: " + course.getTutorType());
                            System.out.println();

                        }
                    } else {
                        UI.nextSlide();
                        UI.message("No courses found for the specified tutor.");
                    }
                }
                case 2 -> {
                    UI.nextSlide();
                    ui.listCoursesEachTutor(tutorCourseList);
                }
                case 3 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void FilterTutor() {
        boolean exit = false;
        while (!exit) {
            switch (ui.FilterTutorMenu()) {
                case 1 ->
                    ui.displayFilterTutor(tutorList);
                case 2 -> {
                    // filter by tutor id
                    DoublyLinkedList<Tutor> filterTutorId = ui.getFilterTutorId(tutorList);
                    UI.nextSlide();
                    ui.displayFilterTutor(filterTutorId);
                }
                case 3 -> {
                    // filter by tutor name
                    DoublyLinkedList<Tutor> filterTutorName = ui.getFilterTutorName(tutorList);
                    UI.nextSlide();
                    ui.displayFilterTutor(filterTutorName);
                }
                case 4 -> {
                    // filter by tutor age
                    DoublyLinkedList<Tutor> filterTutorAge = ui.getFilterTutorAge(tutorList);
                    UI.nextSlide();
                    ui.displayFilterTutor(filterTutorAge);
                }
                case 5 -> {
                    // filter by tutor gender
                    DoublyLinkedList<Tutor> filterTutorGender = 
                            ui.getFilterTutorGender(tutorList);
                    UI.nextSlide();
                    ui.displayFilterTutor(filterTutorGender);
                }
                case 6 -> {
                    // filter by tutor education level
                    DoublyLinkedList<Tutor> filterTutorEducationLevel =
                            ui.getFilterTutorEducationLevel(tutorList);
                    UI.nextSlide();
                    ui.displayFilterTutor(filterTutorEducationLevel);
                }
                case 7 ->
                    exit = true;
            }
        }
        UI.exit();
    }
}
