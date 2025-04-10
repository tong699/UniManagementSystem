package control;

import adt.DoublyLinkedList;
import boundary.FacultyManagementUI;
import entity.Faculty;
import utility.UI;

/**
 *
 * @author jerry
 */
public class FacultyManagement {

    private static final FacultyManagementUI ui = new FacultyManagementUI();
    private final Faculty faculty;
    private final ProgrammeManagement pm;
    private final CourseManagement cm;

    public FacultyManagement(Faculty faculty, DoublyLinkedList<Faculty> facultyList) {
        this.faculty = faculty;
        this.pm = new ProgrammeManagement(faculty, facultyList);
        this.cm = new CourseManagement(faculty);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.facutlyMenu()) {
                case 1 ->
                    pm.run();
                case 2 ->
                    cm.run();
                case 3 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    public Faculty getFaculty() {
        return faculty;
    }

}
