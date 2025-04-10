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
public class UniSystemUI {
    private final Menu menu = new Menu("University Management System");

    public UniSystemUI() {
        menu.add("Student Registration Management Subsystem");
        menu.add("Course Management Subsystem");
        menu.add("Tutorial Group Management Subsystem");
        menu.add("Teaching Assignment Subsystem");
        menu.add("Exit");
    }
    
    public int uniSystemMenu() {
        return menu.displayAndGetChoice();
    }
    
}
