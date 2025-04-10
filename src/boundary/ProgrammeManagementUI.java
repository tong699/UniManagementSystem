package boundary;

import utility.Menu;

/**
 *
 * @author jerry
 */
public class ProgrammeManagementUI {

    private final Menu menu = new Menu(" Programme Management Option");

    public ProgrammeManagementUI() {

        menu.add("Add Programme");
        menu.add("Remove Programme");
        menu.add("Edit Programme Details");
        menu.add("View Programme List");
        menu.add("Add course to programme");
        menu.add("Remove course from programme");
        menu.add("View Programme Course List");
        menu.add("Programme Course Summary Report");
        menu.add("Exit");

    }

    public int programmeOptionMenu() {
        return menu.displayAndGetChoice();
    }
}
