/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Menu;

/**
 *
 * @author Chan Wei Xin
 */
public class TeachingAssignmentUI {
    private final Menu menu = new Menu("Teaching Assignment Management");
    
    public TeachingAssignmentUI(){
        menu.add("Tutor Management");
        menu.add("Course Tutor Management");
        menu.add("Report");
        menu.add("Exit");
    }
    
    public int TeachingAssignmentMenu() {
        return menu.displayAndGetChoice();
    }
}
