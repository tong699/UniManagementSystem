package boundary;

import utility.Menu;

/**
 *
 * @author jerry
 */
public class ProgrammeCourseSubsystemUI {

    private final Menu menu = new Menu("Faculty's Programme and Course Management | Faculty Option selection");

    public ProgrammeCourseSubsystemUI() {
        menu.add("FOCS");
        menu.add("FOET");
        menu.add("Course Taken By Each Faculty");
        menu.add("Serach for Course Details");
        menu.add("Faculty Overall Report");
        menu.add("Exit");
    }

    public int programmeCourseSubsystemMenu() {
        return menu.displayAndGetChoice();
    }

}
