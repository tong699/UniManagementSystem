package boundary;

import utility.Menu;

/**
 *
 * @author jerry
 */
public class CourseManagementUI {

    private final Menu menu = new Menu("Course Management Option List");
    private final Menu menu1 = new Menu("Course Material Option");

    public CourseManagementUI() {
        menu.add("Add New Course");
        menu.add("Remove Course");
        menu.add("Edit Course Details");
        menu.add("Course Material");
        menu.add("View Course List Details");
        menu.add("View Course Taken By Each Programme");
        menu.add("Exit");

        menu1.add("Add course material to course");
        menu1.add("Remove course material from course");
        menu1.add("View course's course material");
        menu1.add("Course Material Summary Report");
        menu1.add("Exit");
    }

    public int courseMenu() {
        return menu.displayAndGetChoice();
    }

    public int courseMaterialMenu() {
        return menu1.displayAndGetChoice();
    }

}
