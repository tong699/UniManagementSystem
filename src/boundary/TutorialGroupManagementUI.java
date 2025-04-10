/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Menu;
/**
 *
 * @author Tong Chun Mun
 */
public class TutorialGroupManagementUI {

    private final Menu menu = new Menu("Tutorial Group Management");
    public TutorialGroupManagementUI() {
        menu.add("Tutorial Group Management");
        menu.add("Tutorial Group Student Management");
        menu.add("Report");
        menu.add("Exit");

    }

    public int tutorialGroupManagementMenu() {
        return menu.displayAndGetChoice();
    }

}
