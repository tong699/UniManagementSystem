/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Menu;

/**
 *
 * @author jerry
 */
public class FacultyManagementUI {

    private final Menu menu = new Menu("Faculty's Programme and Course Management | Option Selection");

    public FacultyManagementUI() {
        menu.add("Programme Management");
        menu.add("Course Management");
        menu.add("Exit");
    }

    public int facutlyMenu() {
        return menu.displayAndGetChoice();
    }


}
