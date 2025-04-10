/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Programme;
import utility.Menu;

/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroupManagementReportUI {

    private final Menu menu = new Menu("Tutorial Group Management Reports");

    public TutorialGroupManagementReportUI() {
        menu.add("Generate Tutorial Group Summary Report");
        menu.add("Generate Student Waiting List Report");
        menu.add("Exit");
    }

    public int tutorialGroupManagementReportMenu() {
        return menu.displayAndGetChoice();
    }

    public void showGroupList(Programme programme) {
        programme.getTutorialGroupList().sort();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println(String.format("| %-5s | %-10s | %-30s | %-20s | %-10s", "Group Id", "Capacity", "Group Representative", "Enrolled Students", "Programme"));
        System.out.println("-------------------------------------------------------------------------------------------");
        if (programme.getTutorialGroupList().isEmpty()) {
            System.out.println("                                           No data");
        } else {
            System.out.print(programme.getTutorialGroupList());
        }
        System.out.println("-------------------------------------------------------------------------------------------\n");
    }

}
