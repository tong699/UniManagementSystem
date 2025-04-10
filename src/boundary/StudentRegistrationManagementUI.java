/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Menu;

/**
 *
 * @author Kong Jia Le
 */
public class StudentRegistrationManagementUI {

    private final Menu menu = new Menu("Student Registration Management");

    public StudentRegistrationManagementUI() {
        menu.add("Student Management");
        menu.add("Student Courses Management");
        menu.add("Report");
        menu.add("Exit");
    }

    public int studentRegistrationManagementMenu() {
        return menu.displayAndGetChoice();
    }
}
