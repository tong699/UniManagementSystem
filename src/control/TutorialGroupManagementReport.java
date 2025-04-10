/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.DoublyLinkedList;
import boundary.TutorialGroupManagementReportUI;
import entity.Programme;
import entity.TutorialGroup;
import utility.UI;

/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroupManagementReport {

    public static final TutorialGroupManagementReportUI ui
            = new TutorialGroupManagementReportUI();
    private final DoublyLinkedList<TutorialGroup> tutorialGroupList;
    private final DoublyLinkedList<Programme> programmeList;

    public TutorialGroupManagementReport(
            DoublyLinkedList<TutorialGroup> tutorialGroupList,
            DoublyLinkedList<Programme> programmeList) {
        this.tutorialGroupList = tutorialGroupList;
        this.programmeList = programmeList;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.tutorialGroupManagementReportMenu()) {
                case 1 ->
                    generateTutorialGroupSummaryReport();
                case 2 ->
                    generateStudentWaitingListReport();
                case 3 ->
                    exit = true;
            }
            // Add more report generation methods as needed
        }
        UI.exit();
    }

    private void generateTutorialGroupSummaryReport() {
        UI.nextSlide();
        System.out.println(
                "+------------------------- Tutorial Group Summary Report "
                + "-------------------------+");
        int grandTotalGroups = tutorialGroupList.size();
        int grantTotalCapacity = 0;
        int grantTotalEnrolledStudents = 0;
        // Iterate through each programme

        System.out.println("\nTotal Tutorial Groups : " + grandTotalGroups);
        System.out.println("\nTutorial Groups By Programme : ");
        for (Programme programme : programmeList) {
            int totalGroups = 0;
            int totalCapacity = 0;
            int totalEnrolledStudents = 0;
            System.out.println(String.format("\n%-10s %-10s ",
                    "Programme ", programme.getProgrammeId()));

            // Get tutorial group list for the current programme
            System.out.println("===============");
            DoublyLinkedList<TutorialGroup> tutorialGroups
                    = programme.getTutorialGroupList();

            for (TutorialGroup tutorialGroup : tutorialGroups) {
                totalGroups++;
                totalCapacity += tutorialGroup.getCapacity();
                totalEnrolledStudents
                        += tutorialGroup.getEnrolledStudents().size();
            }

            System.out.println(String.format(" %-35s %-3d ",
                    "Total Number of Tutorial Groups  : ",
                    totalGroups));
            System.out.println(String.format(" %-35s %-3d ",
                    "Total Capacity of Tutorial Groups: ",
                    totalCapacity));
            System.out.println(String.format(" %-35s %-3d ",
                    "Total Number of Enrolled Students: ",
                    totalEnrolledStudents));
            System.out.println(
                    "-------------------------------------------\n");

            grantTotalCapacity += totalCapacity;
            grantTotalEnrolledStudents += totalEnrolledStudents;
        }
        System.out.println(String.format("%-20s", 
                " Overall Summary"));
        System.out.println(
                "------------------------------------------------");
        System.out.println(String.format(
                " %-35s %-3d ", 
                "Total Number of All Tutorial Groups   : ",
                grandTotalGroups));
        System.out.println(String.format(" %-35s %-3d ", 
                "Total Capacity of All Tutorial Groups : ", 
                grantTotalCapacity));
        System.out.println(String.format(" %-35s %-3d ", 
                "Total Number of All Enrolled Students : ", 
                grantTotalEnrolledStudents));
        System.out.println(
                "------------------------------------------------\n");

        System.out.println("+--------------------------------- End Of Report "
                + "---------------------------------+");
        UI.pressToCont();
    }

    private void generateStudentWaitingListReport() {
        UI.nextSlide();
        System.out.println("+------------------------- Tutorial Group Summary"
                + " Report -------------------------+");
        int totalWaitingListQueue = 0;
        System.out.println("\nTotal Of Students In Waiting List Queue :\n");
        for (Programme programme : programmeList) {
            int waitingListSize = programme.getWaitingListQueue().size();
            System.out.println(String.format("%-10s %-10s %3d", 
                    "Programme ", programme.getProgrammeId(), 
                    waitingListSize));
            totalWaitingListQueue += waitingListSize;
        }

        System.out.println("---------------------------------------------");
        System.out.println(String.format(
                "%-21s %3d\n", "Overall       ", 
                totalWaitingListQueue));

        System.out.println("+--------------------------------- End Of Report "
                + "---------------------------------+");
        UI.pressToCont();
    }

}
