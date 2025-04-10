/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import static adt.DoublyLinkedList.combine;
import boundary.TutorialGroupAdministrationUI;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import utility.UI;

/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroupAdministration {

    public static final TutorialGroupAdministrationUI ui
            = new TutorialGroupAdministrationUI();
    private final DoublyLinkedList<TutorialGroup> tutorialGroupList;
    private final DoublyLinkedList<Programme> programmeList;

    public TutorialGroupAdministration(
            DoublyLinkedList<TutorialGroup> tutorialGroupList,
            DoublyLinkedList<Programme> programmeList) {
        this.tutorialGroupList = tutorialGroupList;
        this.programmeList = programmeList;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.tutorialGroupAdministrationMenu()) {
                case 1 ->
                    handleCreationAndModification();
                case 2 ->
                    assignRepresentative();
                case 3 ->
                    showGroupInformation();
                case 4 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    public void handleCreationAndModification() {
        boolean exit = false;
        while (!exit) {
            switch (ui.creationAndModificationMenu()) {
                case 1 ->
                    createTutorialGroup();
                case 2 ->
                    removeTutorialGroup();
                case 3 ->
                    mergeTutorialGroup();
                case 4 ->
                    customizeCapacity();
                case 5 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    public void createTutorialGroup() {
        UI.nextSlide();
        UI.title("Create Tutorial Group");
        Programme programme = ui.getProgramme(programmeList);

        if (programme != null) {
            int noOfGroupToAdd = ui.inputGroupToAdd();
            DoublyLinkedList<TutorialGroup> newGroups
                    = new DoublyLinkedList<>();
            for (int i = 0; i < noOfGroupToAdd; i++) {
                TutorialGroup group
                        = new TutorialGroup(
                                programme.getProgrammeId());
                newGroups.add(group);
            }
            if (newGroups.isEmpty()) {
                UI.message("invalid new group(s).");
            } else {
                UI.nextSlide();
                if (UI.confirmUI(newGroups.size()
                        + " new tutorial group(s) will be created.")) {
                    for (TutorialGroup newGroup : newGroups) {
                        programme.getTutorialGroupList().add(newGroup);
                        tutorialGroupList.add(newGroup);
                    }
                    System.out.println(newGroups.size()
                            + " Tutorial group(s) created successfully.");
                } else {
                    UI.message("Programme not found.");
                    UI.nextSlide();
                }

            }
        } else {
            UI.message("Programme not found.");
        }
    }

    private void removeTutorialGroup() {
        UI.nextSlide();
        UI.title("Remove Tutorial Group");
        Programme programme = ui.getProgramme(programmeList);
        if (programme != null) {
            DoublyLinkedList<TutorialGroup> groupList
                    = programme.getTutorialGroupList();
            System.out.println("\nGroup List : ");
            ui.showGroups(groupList);
            if (!groupList.isEmpty()) {
                TutorialGroup groupToRemove = ui.getTutorialGroup(
                        programme.getProgrammeId(),
                        groupList);
                if (groupToRemove != null) {
                    UI.nextSlide();
                    if (UI.confirmUI(groupToRemove.details()
                            + "Tutorial Group " + groupToRemove.getGroupId()
                            + " will be removed")) {
                        groupList.remove(
                                groupList.positionOf(groupToRemove));
                        tutorialGroupList.remove(
                                tutorialGroupList.positionOf(
                                        groupToRemove));
                        System.out.println("Tutorial Group "
                                + groupToRemove.getGroupId()
                                + " is removed successfully. ");
                    } else {
                        UI.message("Group removal cancelled.");
                    }
                } else {
                    UI.message("Group not found.");
                }
            } else {
                UI.message("No group in the List.");
            }
        }
    }

    private void mergeTutorialGroup() {

        UI.nextSlide();
        UI.title("Tutorial Group Merger");
        Programme programme = ui.getProgramme(programmeList);
        if (programme != null) {

            DoublyLinkedList<TutorialGroup> groupList
                    = programme.getTutorialGroupList();
            System.out.println("\nGroup List : ");
            ui.showGroups(groupList);
            if (!groupList.isEmpty()) {
                TutorialGroup group1
                        = ui.getTutorialGroup(
                                programme.getProgrammeId(),
                                groupList);
                if (group1 != null) {
                    TutorialGroup group2 = ui.getTutorialGroup(
                            programme.getProgrammeId(),
                            groupList);
                    if (group2 != null && group2 != group1) {
                        int capacity = ui.enterNewCapacityForMergedGroup();
                        if (capacity != 0) {
                            if (UI.confirmUI("Tutorial Groups "
                                    + group1.getGroupId() + " and "
                                    + group2.getGroupId()
                                    + " will be merged. ")) {
                                int totalEnrolledStudents
                                        = group1.getEnrolledStudents().size()
                                        + group2.getEnrolledStudents().size();
                                if (totalEnrolledStudents > capacity) {
                                    UI.message(
                                            "Merge failed: "
                                            + "Total enrolled students "
                                            + "exceed capacity.");
                                } else {
                                    TutorialGroup mergedGroup
                                            = new TutorialGroup(
                                                    programme.
                                                            getProgrammeId());
                                    DoublyLinkedList<Student> mergeStudentList
                                            = combine(
                                                    group1.
                                                            getEnrolledStudents(),
                                                    group2.
                                                            getEnrolledStudents());
                                    mergedGroup.setEnrolledStudents(
                                            mergeStudentList);
                                    mergedGroup.setCapacity(capacity);
                                    programme.getTutorialGroupList().add(
                                            mergedGroup);
                                    tutorialGroupList.add(mergedGroup);
                                    groupList.remove(groupList.positionOf(
                                            group1));
                                    tutorialGroupList.remove(
                                            tutorialGroupList.positionOf(
                                                    group1));
                                    groupList.remove(groupList.positionOf(
                                            group2));
                                    tutorialGroupList.remove(
                                            tutorialGroupList.positionOf(
                                                    group2));
                                    UI.message(" Merge successful.");
                                }
                            } else {
                                UI.message("Merge cancelled. ");
                            }
                        } else {
                            UI.message("Invalid capacity.");
                        }
                    } else {
                        UI.message("Group not found or duplicate group.");
                    }
                } else {
                    UI.message("Group not found.");
                }
            } else {
                UI.message("No group(s) in the list.");
            }
        } else {
            UI.message("Programme not found.");
        }

    }

    private void customizeCapacity() {
        UI.nextSlide();
        UI.title("Set New Capacity");
        Programme programme = ui.getProgramme(programmeList);
        if (programme != null) {
            DoublyLinkedList<TutorialGroup> groupList
                    = programme.getTutorialGroupList();
            System.out.println("\nGroup List : ");
            ui.showGroups(groupList);
            if (!groupList.isEmpty()) {
                TutorialGroup groupToCustomize
                        = ui.getTutorialGroup(programme.getProgrammeId(),
                                groupList);
                if (groupToCustomize != null) {
                    int newCapacity = ui.enterNewCapacity();
                    if (newCapacity != 0) {
                        UI.nextSlide();
                        if (UI.confirmUI(groupToCustomize.details()
                                + "The capacity of tutorial Group "
                                + groupToCustomize.getGroupId()
                                + " will be customized.")) {
                            groupToCustomize.setCapacity(newCapacity);
                            System.out.println("The capacity of tutorial Group "
                                    + groupToCustomize.getGroupId()
                                    + " has been customized to " + newCapacity
                                    + ".");
                        } else {
                            UI.message("Customization cancelled.");
                        }
                    } else {
                        UI.message("Invalid capacity.");
                    }
                } else {
                    UI.message("Group not found.");
                }
            } else {
                UI.message("No group in the List.");
            }
        }
    }

    public void assignRepresentative() {
        boolean exit = false;
        while (!exit) {
            switch (ui.assignRepresentativeMenu()) {
                case 1 ->
                    addRepresentative();
                case 2 ->
                    modifyRepresentative();
                case 3 ->
                    removeRepresentative();
                case 4 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    public void addRepresentative() {
        UI.nextSlide();
        UI.title("Assign Group Representative");
        Programme programme = ui.getProgramme(programmeList);
        if (programme != null) {
            DoublyLinkedList<TutorialGroup> groupList
                    = programme.getTutorialGroupList();
            if (!groupList.isEmpty()) {
                TutorialGroup groupToAddRep = ui.getTutorialGroup(
                        programme.getProgrammeId(),
                        groupList);
                if (groupToAddRep != null) {
                    DoublyLinkedList<Student> groupStudents
                            = groupToAddRep.getEnrolledStudents();
                    ui.showStudentList(groupStudents);
                    Student studentToRep
                            = ui.getStudent(groupStudents);
                    if (studentToRep != null) {
                        UI.nextSlide();
                        if (UI.confirmUI(studentToRep.details()
                                + studentToRep.getName()
                                + " will be assigned as group representative of "
                                + groupToAddRep.getGroupId())) {
                            groupToAddRep.setGroupRep(studentToRep);
                            UI.message("\nGroup representative of "
                                    + groupToAddRep.getGroupId()
                                    + " is assigned successfully. ");
                        } else {
                            UI.message("Group Representative "
                                    + "Assignment cancelled.");
                        }
                    } else {
                        UI.message("Student not found.");
                    }
                } else {
                    UI.message("Group not found.");
                }
            } else {
                UI.message("No group in the List.");
            }
        }
    }

    public void removeRepresentative() {
        UI.nextSlide();
        UI.title("Remove Group Representative");
        Programme programme = ui.getProgramme(programmeList);
        if (programme != null) {
            DoublyLinkedList<TutorialGroup> groupList
                    = programme.getTutorialGroupList();

            if (!groupList.isEmpty()) {
                TutorialGroup groupToRemoveRep
                        = ui.getTutorialGroup(
                                programme.getProgrammeId(),
                                groupList);
                if (groupToRemoveRep != null) {
                    UI.nextSlide();
                    Student groupRep = groupToRemoveRep.getGroupRep();
                    if (groupRep != null) {
                        if (UI.confirmUI(groupRep.details()
                                + " will be removed as group representative of "
                                + groupToRemoveRep.getGroupId())) {
                            groupToRemoveRep.setGroupRep(null);
                            UI.message(" Group representative of "
                                    + groupToRemoveRep.getGroupId()
                                    + " is removed successfully. ");
                        } else {
                            UI.message("Group Representative "
                                    + "removal cancelled.");
                        }
                    } else {
                        UI.message("Group representative not found.");
                    }
                } else {
                    UI.message("Group not found.");
                }
            } else {
                UI.message("No group in the List.");
            }
        }
    }

    public void modifyRepresentative() {
        UI.nextSlide();
        UI.title("Change Group Representative");
        Programme programme = ui.getProgramme(programmeList);
        if (programme != null) {
            DoublyLinkedList<TutorialGroup> groupList
                    = programme.getTutorialGroupList();

            if (!groupList.isEmpty()) {
                TutorialGroup groupToAddRep
                        = ui.getTutorialGroup(
                                programme.getProgrammeId(),
                                groupList);
                if (groupToAddRep != null) {
                    DoublyLinkedList<Student> groupStudents
                            = groupToAddRep.getEnrolledStudents();
                    Student groupRep = groupToAddRep.getGroupRep();
                    if (groupRep != null) {
                        UI.nextSlide();
                        UI.title("Group Representative of "
                                + groupToAddRep.getGroupId());
                        System.out.println(groupRep.details());
                        UI.nextSlide();
                        UI.title("New Representative");
                        ui.showStudentList(groupStudents);
                        Student studentToRep
                                = ui.getStudent(groupStudents);
                        if (studentToRep != null && studentToRep != groupToAddRep.getGroupRep()) {
                            UI.nextSlide();
                            if (UI.confirmUI(studentToRep.details()
                                    + studentToRep.getName()
                                    + " will be assigned as new group "
                                    + "representative of "
                                    + groupToAddRep.getGroupId())) {
                                groupToAddRep.setGroupRep(studentToRep);
                                UI.message(" New group representative of "
                                        + groupToAddRep.getGroupId()
                                        + " is assigned successfully.");
                            } else {
                                UI.message("Group Representative "
                                        + "Assignment cancelled.");
                            }
                        } else {
                            UI.message("Student not found or duplicate student.");
                        }
                    } else {
                        UI.message("Group representative not found.");
                    }

                } else {
                    UI.message("Group not found.");
                }
            } else {
                UI.message("No group in the List.");
            }
        }
    }

    public void showGroupInformation() {
        if (!tutorialGroupList.isEmpty() || !programmeList.isEmpty()) {
            ui.showGroupList(tutorialGroupList, programmeList);
        }
        UI.exit();
    }

}
