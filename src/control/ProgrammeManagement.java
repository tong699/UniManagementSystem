package control;

import adt.ArrayList;
import adt.DoublyLinkedList;
import boundary.ProgrammeManagementUI;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import entity.ProgrammeCourseAssociation;
import java.util.Scanner;
import utility.UI;

/**
 *
 * @author jerry
 */
public class ProgrammeManagement {

    private final ProgrammeManagementUI ui = new ProgrammeManagementUI();
    private final Faculty faculty;
    private final DoublyLinkedList<Faculty> facultyList;
    private static final Scanner scanner = new Scanner(System.in);

    public ProgrammeManagement(Faculty faculty,
            DoublyLinkedList<Faculty> facultyList) {
        this.faculty = faculty;
        this.facultyList = facultyList;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            int choice = ui.programmeOptionMenu();
            switch (choice) {
                case 1 ->
                    addProgramme();
                case 2 ->
                    removeProgramme();
                case 3 ->
                    editProgrammeDetails();
                case 4 ->
                    displayProgrammeList();
                case 5 ->
                    addCourseToProgramme();
                case 6 ->
                    removeCourseFromProgramme();
                case 7 ->
                    displayProgrammeCourseList();
                case 8 ->
                    programmeCourseSummaryReport();
                case 9 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void addProgramme() {
        String programmeID;
        boolean validProgrammeIDFormat;

        do {
            UI.nextSlide();
            System.out.print("Enter Programme ID (Format: XXX): ");
            programmeID = scanner.nextLine().toUpperCase();
            validProgrammeIDFormat = programmeID.matches("[A-Z]{3}");
            if (!validProgrammeIDFormat) {
                UI.message("Programme ID must be in the format " + "of 3 letters without spaces.\n");
            }
        } while (!validProgrammeIDFormat);

        for (Faculty otherFaculty : facultyList) {
            for (Programme programme : otherFaculty.getProgrammeList()) {
                if (programme.getProgrammeId().equals(programmeID)) {
                    UI.message("A programme with this ID '" + programmeID + "' already exists in another faculty.\n");
                    return;
                }
            }
        }

        for (Programme programme : faculty.getProgrammeList()) {
            if (programme.getProgrammeId().equals(programmeID)) {
                UI.message(" A programme with this ID '" + programmeID + "' already exists.\n");
                return;
            }
        }

        System.out.print("Enter Programme Name: ");
        String name = scanner.nextLine().toUpperCase();
        if (name.isEmpty()) {
            UI.message("Programme Name cannot be empty.");
            return;
        }
        Programme newProgramme = new Programme(programmeID, name);

        UI.nextSlide();
        System.out.println("Are you sure you want to add this programme? (Yes/No)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            faculty.getProgrammeList().add(newProgramme);
            UI.message("Programme added successfully.");
        } else {
            UI.message("Programme addition cancelled.");
        }
    }

    private void removeProgramme() {
        if (faculty.getProgrammeList().isEmpty()) {
            displayProgrammeList();
            return;
        }

        displayProgrammeList();

        System.out.print("Enter Programme ID to remove: ");
        String idToRemove = scanner.nextLine().toUpperCase();

        boolean programmeFound = false;
        int indexToRemove = -1;
        for (int i = 0; i < faculty.getProgrammeList().size(); i++) {
            Programme programme = faculty.getProgrammeList().get(i);
            if (programme.getProgrammeId().equals(idToRemove)) {
                indexToRemove = i;
                programmeFound = true;
                break;
            }
        }

        if (!programmeFound) {
            UI.message("Programme with ID '" + idToRemove + "' not found.\n");
            UI.nextSlide();
            return;
        }

        ArrayList<ProgrammeCourseAssociation> associationsToRemove = new ArrayList<>();
        for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
            if (association.getProgrammeId().equals(idToRemove)) {
                associationsToRemove.add(association);
            }
        }

        for (ProgrammeCourseAssociation association : associationsToRemove) {
            int index = faculty.getProgrammeCourseList().positionOf(association);
            if (index != -1) {
                faculty.getProgrammeCourseList().remove(index);
            }
        }

        UI.nextSlide();
        System.out.println("Are you sure you want to remove this programme? (Yes/No)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            faculty.getProgrammeList().remove(indexToRemove);
            UI.message("Programme removed successfully.");
        } else {
            UI.message("Programme removal cancelled.");
        }
    }

    private void editProgrammeDetails() {
        if (faculty.getProgrammeList().isEmpty()) {
            displayProgrammeList();
            return;
        }

        displayProgrammeList();

        System.out.print("Enter Programme ID to edit: ");
        String oldProgrammeID = scanner.nextLine().toUpperCase();

        Programme programmeToEdit = null;
        for (Programme programme : faculty.getProgrammeList()) {
            if (programme.getProgrammeId().equals(oldProgrammeID)) {
                programmeToEdit = programme;
                break;
            }
        }

        if (programmeToEdit == null) {
            UI.message("Programme with ID '" + oldProgrammeID + "' not found.");
            return;
        }

        String newProgrammeID;
        boolean validProgrammeIDFormat;
        do {
            System.out.print("Enter new Programme ID (Format: XXX, Current: " + programmeToEdit.getProgrammeId() + "): ");
            newProgrammeID = scanner.nextLine().toUpperCase();
            validProgrammeIDFormat = newProgrammeID.matches("[A-Z]{3}");
            if (!validProgrammeIDFormat) {
                UI.message("Programme ID must be in the format of 3 letters without spaces.\n");
            }
        } while (!validProgrammeIDFormat);

        System.out.print("Enter new Programme Name (Current: " + programmeToEdit.getProgrammeName() + "): ");
        String newProgrammeName = scanner.nextLine().toUpperCase();
        if (newProgrammeName.isEmpty()) {
            UI.message("Programme Name cannot be empty.\n");
            return;
        }

        UI.nextSlide();
        System.out.print("Are you sure you want to update the programme details? (Yes/No): ");
        String confirmUpdate = scanner.nextLine();
        if (confirmUpdate.equalsIgnoreCase("yes")) {
            programmeToEdit.setProgrammeId(newProgrammeID);
            programmeToEdit.setProgrammeName(newProgrammeName);

            for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
                if (association.getProgrammeId().equals(oldProgrammeID)) {
                    association.setProgrammeId(newProgrammeID);
                }
            }

            UI.message("Programme details updated successfully.");
        } else {
            UI.message("Programme details update cancelled.");
        }
    }

    private void addCourseToProgramme() {
        DoublyLinkedList<Course> courseList = faculty.getCourseList();

        if (courseList.isEmpty()) {
            UI.nextSlide();
            UI.message("Course List is Empty.");
            UI.exit();
            return;
        }

        displayProgrammeList();

        System.out.print("Enter Programme ID to add course: ");
        String programmeID = scanner.nextLine().toUpperCase();

        Programme selectedProgramme = null;
        for (Programme programme : faculty.getProgrammeList()) {
            if (programme.getProgrammeId().equals(programmeID)) {
                selectedProgramme = programme;
                break;
            }
        }

        if (selectedProgramme == null) {
            UI.message("Programme with ID '" + programmeID + "' not found.\n");
            return;
        }

        UI.nextSlide();
        UI.title("List of Courses");
        System.out.println("Course Code          Course Type     ");
        System.out.println("-----------------------------------------------");
        for (Course course : courseList) {
            System.out.println(course.getCourseCode() + "              " + course.getCourseType());
        }
        UI.nextSlide();

        while (true) {
            System.out.print("Enter course code to add (or 'done' to finish): ");
            String courseCode = scanner.nextLine().toUpperCase();
            if ("DONE".equalsIgnoreCase(courseCode)) {
                break;
            }

            boolean courseExists = false;
            Course selectedCourse = null;
            for (Course course : courseList) {
                if (course.getCourseCode().equals(courseCode)) {
                    courseExists = true;
                    selectedCourse = course;
                    break;
                }
            }

            if (!courseExists) {
                UI.message("Course with code '" + courseCode + "' not found.\n");
                continue;
            }

            boolean courseAlreadyAdded = false;
            for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
                if (association.getProgrammeId().equals(programmeID) && association.getCourseCode().equals(courseCode)) {
                    UI.message("Course with Code '" + courseCode + "' is already existed in the Programme.\n");
                    courseAlreadyAdded = true;
                    break;
                }
            }
            if (courseAlreadyAdded) {
                continue;
            }

            if (selectedCourse != null) {
                String courseType = selectedCourse.getCourseType();
                ProgrammeCourseAssociation association = new ProgrammeCourseAssociation(programmeID, courseCode, courseType);
                faculty.getProgrammeCourseList().add(association);
                UI.message("Course '" + courseCode + "' added to programme successfully.\n");
            } else {
                UI.message(
                        "Failed to retrieve course information. Please try again.\n");
            }
        }
        UI.nextSlide();
        UI.exit();
    }

    private void removeCourseFromProgramme() {
        if (faculty.getProgrammeList().isEmpty()) {
            UI.message("Course List is Empty.");
            UI.exit();
            return;
        }

        displayProgrammeList();

        System.out.print("Enter Programme ID to remove course: ");
        String programmeID = scanner.nextLine().toUpperCase();

        Programme selectedProgramme = null;
        for (Programme programme : faculty.getProgrammeList()) {
            if (programme.getProgrammeId().equals(programmeID)) {
                selectedProgramme = programme;
                break;
            }
        }
        if (selectedProgramme == null) {
            UI.message("Programme with ID '" + programmeID + "' not found.\n");
            return;
        }

        boolean hasCourses = false;
        for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
            if (association.getProgrammeId().equals(programmeID)) {
                hasCourses = true;
                break;
            }
        }

        if (!hasCourses) {
            UI.message("Programme with ID '" + programmeID + "' has no courses added.\n");
            return;
        }

        UI.title("Programme - " + selectedProgramme.getProgrammeName());
        System.out.println("Course Code          Course Type");
        System.out.println("-----------------------------------------------");
        for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
            if (association.getProgrammeId().equals(programmeID)) {
                System.out.println(association.getCourseCode() + "             " + association.getCourseType());
            }
        }
        UI.nextSlide();

        System.out.print("Enter Course Code to remove: ");
        String courseCode = scanner.nextLine().toUpperCase();
        ProgrammeCourseAssociation associationToRemove = null;
        for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
            if (association.getProgrammeId().equals(programmeID) && association.getCourseCode().equals(courseCode)) {
                associationToRemove = association;
                break;
            }
        }

        if (associationToRemove == null) {
            UI.message("Course with code '" + courseCode + "' not found in programme with ID '" + programmeID + "'.\n");
            return;
        }

        UI.nextSlide();
        System.out.print("Are you sure you want to "
                + "remove this course from the programme? (Yes/No): ");
        String confirmDelete = scanner.nextLine();
        if (confirmDelete.equalsIgnoreCase("yes")) {
            int indexToRemove = -1;
            for (int i = 0; i < faculty.getProgrammeCourseList().size(); i++) {
                ProgrammeCourseAssociation association = faculty.getProgrammeCourseList().get(i);
                if (association.equals(associationToRemove)) {
                    indexToRemove = i;
                    break;
                }
            }

            if (indexToRemove == -1) {
                UI.message("Course with code '" + courseCode + "' not found in programme with ID '" + programmeID + "'.\n");
                return;
            }

            faculty.getProgrammeCourseList().remove(indexToRemove);
            UI.message("Course removed successfully from the programme.");
        } else {
            UI.message("Course removal cancelled.");
        }
    }

    private void displayProgrammeList() {
        if (faculty.getProgrammeList().isEmpty()) {
            UI.nextSlide();
            System.out.println("No programmes added.");
            UI.exit();
        } else {
            UI.nextSlide();
            UI.title("List of Programmes");
            System.out.println("Programme ID          Programme Name");
            System.out.println("-----------------------------------------------");
            for (Programme programme : faculty.getProgrammeList()) {
                System.out.println(programme.getProgrammeId() + "                   " + programme.getProgrammeName());
            }
        }

    }

    private void displayProgrammeCourseList() {
        if (faculty.getProgrammeCourseList().isEmpty()) {
            UI.nextSlide();
            System.out.println("No programme has been added any course(s).");
            UI.exit();
            return;
        }

        UI.nextSlide();
        UI.title("Programme Course List");
        System.out.println("Programme ID        Course ID          Course Type");
        System.out.println("------------------------------------------------------------");
        for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
            System.out.println(association.getProgrammeId() + "                 " + association.getCourseCode() + "           " + association.getCourseType());
        }
    }

    private void programmeCourseSummaryReport() {
        UI.nextSlide();
        UI.title("Programme-Course Association Summary Report");
        System.out.println("--  List of programmes Details  --");
        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("Total number of programmes: " + faculty.getProgrammeList().size());
        for (Programme programme : faculty.getProgrammeList()) {
            int numCourses = 0;
            System.out.println("Programme ID   : " + programme.getProgrammeId() + "\nProgramme Name : " + programme.getProgrammeName());
            for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
                if (association.getProgrammeId().equals(programme.getProgrammeId())) {
                    numCourses++;
                }
            }
            System.out.println("Number of courses " + "associated with this programme: " + numCourses);
            System.out.println("\n------------------------------------------------------------\n");
        }
        UI.title("List of associations with programme Details");
        System.out.println("------------------------------------------------------------");
        System.out.println("    Total number of associations: " + faculty.getProgrammeCourseList().size() + "                       ");
        System.out.println("------------------------------------------------------------");
        System.out.println("    Programme ID    |    " + "Course Code    |    Course Type    ");
        System.out.println("--------------------|-------------------|-------------------");
        for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
            System.out.println("    " + association.getProgrammeId() + "             |    " + association.getCourseCode() + "       |    " + association.getCourseType());
            System.out.println("--------------------|-------------------|-------------------");
        }
        UI.nextSlide();
        UI.pressToCont();
    }

}
