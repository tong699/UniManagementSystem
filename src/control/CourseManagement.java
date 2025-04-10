package control;

import adt.DoublyLinkedList;
import boundary.CourseManagementUI;
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
public class CourseManagement {

    private static final CourseManagementUI ui = new CourseManagementUI();
    private final DoublyLinkedList<Course> courseList;
    private final DoublyLinkedList<String> courseMaterialList;
    private final Faculty faculty;
    private static final Scanner scanner = new Scanner(System.in);

    public CourseManagement(Faculty faculty) {
        this.faculty = faculty;
        this.courseList = faculty.getCourseList();
        this.courseMaterialList = faculty.getCourseMaterialList();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            switch (ui.courseMenu()) {
                case 1 ->
                    addCourse();
                case 2 ->
                    removeCourse();
                case 3 ->
                    courseEdit();
                case 4 ->
                    courseMaterial();
                case 5 ->
                    displayCourseListDetails();
                case 6 -> {
                    displayCourseTakenByProgramme();
                }
                case 7 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    private void addCourse() {
        String courseCode;
        boolean validCourseCodeFormat;

        do {
            UI.nextSlide();
            System.out.println("Enter Course Code (Format: XXXX1234): ");
            courseCode = scanner.nextLine().toUpperCase();
            validCourseCodeFormat = courseCode.matches("[A-Z]{4}\\d{4}");
            if (!validCourseCodeFormat) {
                UI.message("Course Code must be in the format of 4 letters with 4 digits without spaces.");
            }
        } while (!validCourseCodeFormat);

        for (Course course : courseList) {
            if (course.getCourseCode().equals(courseCode)) {
                UI.message("Course Code '" + courseCode + "' already exists.");
                return;
            }
        }

        String courseName;
        do {
            System.out.println("Enter Course Name: ");
            courseName = scanner.nextLine().toUpperCase();
            if (courseName.isEmpty()) {
                UI.message("Course Name cannot be empty.");
            }
        } while (courseName.isEmpty());

        String courseType;
        do {
            System.out.println("Enter Course Type (Main, Elective, Repeat): ");
            courseType = scanner.nextLine().toUpperCase();
            if (!courseType.equals("MAIN") && !courseType.equals("ELECTIVE") && !courseType.equals("REPEAT")) {
                UI.message("Course Type must be either 'Main' or 'Elective'or 'Repeat'.");
            }
        } while (!courseType.equals("MAIN") && !courseType.equals("ELECTIVE") && !courseType.equals("REPEAT"));

        double price;
        while (true) {
            System.out.println("Enter Price(RM): ");
            String priceInput = scanner.nextLine();
            try {
                price = Integer.parseInt(priceInput);
                break;
            } catch (NumberFormatException e) {
                UI.message("Price must be a valid number.");
            }
        }

        int creditHour;
        while (true) {
            System.out.println("Enter Credit Hour(s): ");
            String creditHourInput = scanner.nextLine();
            try {
                creditHour = Integer.parseInt(creditHourInput);
                break;
            } catch (NumberFormatException e) {
                UI.message("Credit Hours must be a valid number.");
            }
        }

        Course newCourse = new Course(courseCode, courseName, courseType, price, creditHour);

        UI.nextSlide();
        System.out.println("Are you sure you want to save this course? (Yes/No)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            courseList.add(newCourse);
            UI.message("Course added to list.");
        } else {
            UI.message("Course not saved.");
        }
    }

    private void removeCourse() {
        if (courseList.isEmpty()) {
            displayCourseList();
            return;
        }

        UI.nextSlide();
        displayCourseList();

        System.out.println("Enter the Course Code to remove:");
        String courseIdToRemove = scanner.nextLine().toUpperCase();

        int courseIndexToRemove = -1;
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseCode().equals(courseIdToRemove)) {
                courseIndexToRemove = i;
                break;
            }
        }

        if (courseIndexToRemove == -1) {
            System.out.println("Message: Course with Code " + courseIdToRemove + " not found!");
            return;
        }

        Course courseToRemove = courseList.get(courseIndexToRemove);
        UI.nextSlide();
        System.out.println("Are you sure you want to remove the course " + courseToRemove.getCourseCode() + " - " + courseToRemove.getCourseName() + "? (Yes/No)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            courseList.remove(courseIndexToRemove);
            for (int i = 0; i < faculty.getProgrammeCourseList().size(); i++) {
                ProgrammeCourseAssociation association = faculty.getProgrammeCourseList().get(i);
                if (association.getCourseCode().equals(courseIdToRemove)) {
                    faculty.getProgrammeCourseList().remove(i);
                    i--;
                }
            }

            UI.message("Course removed successfully.");
        } else {
            UI.message("Removal cancelled.");
        }
    }

    private void courseEdit() {
        if (courseList.isEmpty()) {
            displayCourseList();
            return;
        }

        displayCourseList();

        System.out.print("Enter the Course Code of the course to edit: ");
        String courseCodeToEdit = scanner.nextLine().toUpperCase();

        Course courseToEdit = null;
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseCode().equals(courseCodeToEdit)) {
                courseToEdit = courseList.get(i);
                break;
            }
        }

        if (courseToEdit == null) {
            System.out.println("Message: Course Code with " + courseCodeToEdit + " not found!");
            return;
        }

        System.out.println("Editing course: " + courseToEdit.getCourseCode() + " - " + courseToEdit.getCourseName());

        String newCourseCode;
        boolean validCourseIdFormat;
        do {
            System.out.print("Enter new course code (current: " + courseToEdit.getCourseCode() + "): ");
            newCourseCode = scanner.nextLine().trim().toUpperCase();
            validCourseIdFormat = newCourseCode.matches("[A-Z]{4}\\d{4}");
            if (!validCourseIdFormat) {
                UI.message("Course Code must be in the format of 4 letters followed by 4 digits without spaces. \n");
            } else {
                boolean exists = false;
                for (Course course : courseList) {
                    if (course.getCourseCode().equals(newCourseCode) && !newCourseCode.equals(courseCodeToEdit)) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    UI.message("Course ID '" + newCourseCode + "' already exists.");
                    validCourseIdFormat = false;
                }
            }
        } while (!validCourseIdFormat);

        System.out.print("Enter new course name (current: " + courseToEdit.getCourseName() + "): ");
        String newCourseName;
        do {
            newCourseName = scanner.nextLine().toUpperCase();
            if (newCourseName.isEmpty()) {
                UI.message("Course Name cannot be empty.\n");
            }
        } while (newCourseName.isEmpty());

        String newCourseType;
        do {
            System.out.print("Enter new course type (current: " + courseToEdit.getCourseType() + "): ");
            newCourseType = scanner.nextLine().toUpperCase();
            if (!newCourseType.equals("MAIN") && !newCourseType.equals("ELECTIVE") && !newCourseType.equals("REPEAT")) {
                UI.message("Course Type must be either 'Main' or 'Elective' or 'Repeat'.\n");
            }
        } while (!newCourseType.equals("MAIN") && !newCourseType.equals("ELECTIVE") && !newCourseType.equals("REPEAT"));

        double newPrice;
        while (true) {
            System.out.print("Enter new fee (current: " + courseToEdit.getPrice() + "): ");
            String newPriceInput = scanner.nextLine();
            try {
                newPrice = Double.parseDouble(newPriceInput);
                break;
            } catch (NumberFormatException e) {
                UI.message("Price must be a valid number.\n");
            }
        }

        int newCreditHour;
        while (true) {
            System.out.print("Enter new credit hour (current: " + courseToEdit.getCreditHour() + "): ");
            String newCreditHourInput = scanner.nextLine();
            try {
                newCreditHour = Integer.parseInt(newCreditHourInput);
                break;
            } catch (NumberFormatException e) {
                UI.message("Credit Hours must be a valid number.\n");
            }
        }

        UI.nextSlide();
        System.out.println("Are you sure you want to save changes? (Yes/No)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            courseToEdit.setCourseCode(newCourseCode);
            courseToEdit.setCourseName(newCourseName);
            courseToEdit.setCourseType(newCourseType);
            courseToEdit.setPrice(newPrice);
            courseToEdit.setCreditHour(newCreditHour);
            for (ProgrammeCourseAssociation association : faculty.getProgrammeCourseList()) {
                if (association.getCourseCode().equals(courseCodeToEdit)) {
                    association.setCourseCode(newCourseCode);
                    association.setCourseType(newCourseType);
                }
            }

            UI.message("Course details updated successfully.");
        } else {
            UI.message("Changes discarded.");
        }
    }

    public void courseMaterial() {
        boolean exit = false;
        int[] materialCounts = new int[6];
        while (!exit) {
            switch (ui.courseMaterialMenu()) {
                case 1 ->
                    addCourseMaterial(materialCounts);
                case 2 ->
                    removeCourseMaterial(materialCounts);
                case 3 ->
                    displayCourseListMaterial();
                case 4 ->
                    courseMaterialSummaryReport(materialCounts);

                case 5 ->
                    exit = true;
            }
        }
        UI.exit();
    }

    public void addCourseMaterial(int[] materialCounts) {
        if (courseList.isEmpty()) {
            displayCourseList();
            return;
        }

        displayCourseList();

        System.out.println("Enter the Course Code to add materials:");
        String courseId = scanner.nextLine().toUpperCase();

        Course selectedCourse = null;
        for (Course course : courseList) {
            if (course.getCourseCode().equals(courseId)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            UI.message("Course not found.");
            return;
        }

        String[] materialOptions = {"Lecture Notes", "Lecture Slides", "Tutorial Slides", "Tutorial Questions", "Practical Notes", "Practical Questions"};
        UI.nextSlide();
        System.out.println("Choose materials to add ('done' to finish):");
        for (int i = 0; i < materialOptions.length; i++) {
            System.out.println((i + 1) + ". " + materialOptions[i]);
        }
        DoublyLinkedList<String> addedMaterials = new DoublyLinkedList<>();

        int materialCount = 0;

        while (true) {
            System.out.println("Enter the number of the material to add:");
            String input = scanner.nextLine().toUpperCase();
            if ("DONE".equalsIgnoreCase(input)) {
                break;
            }

            int materialIndex;
            try {
                materialIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                UI.message("Invalid input. Please enter a number or 'done' to finish.\n");
                continue;
            }

            if (materialIndex < 1 || materialIndex > materialOptions.length) {
                UI.message("Invalid input. Please enter a valid number.\n");
                continue;
            }

            String material = materialOptions[materialIndex - 1];

            if (addedMaterials.contains(material)) {
                UI.message("Material '" + material + "' has already been added to the course.\n");
                continue;
            }

            if (materialCount > 6) {
                UI.message("You have reached the maximum limit of materials (6) for this course.\n");
                break;
            }
            addedMaterials.add(material);
            materialCount++;
            courseMaterialList.add(selectedCourse.getCourseCode() + ":" + material);
            UI.message("Material '" + material + "' added to course '" + selectedCourse.getCourseCode() + "'.\n");
            UI.nextSlide();

            switch (material) {
                case "Lecture Notes" ->
                    materialCounts[0]++;
                case "Lecture Slides" ->
                    materialCounts[1]++;
                case "Tutorial Slides" ->
                    materialCounts[2]++;
                case "Tutorial Questions" ->
                    materialCounts[3]++;
                case "Practical Notes" ->
                    materialCounts[4]++;
                case "Practical Questions" ->
                    materialCounts[5]++;
            }
        }
        UI.message("Materials added successfully.");
    }

    public void removeCourseMaterial(int[] materialCounts) {
        if (courseMaterialList.isEmpty()) {
            UI.nextSlide();
            UI.message("No material added to any course yet.");
            UI.exit();
            return;
        }

        displayCourseList();

        System.out.println("Enter the Course ID to remove materials from:");
        String courseId = scanner.nextLine().toUpperCase();
        boolean found = false;

        DoublyLinkedList<Integer> toRemoveIndices = new DoublyLinkedList<>();

        for (int i = 0; i < courseMaterialList.size(); i++) {
            String material = courseMaterialList.get(i);
            if (material.startsWith(courseId + ":")) {
                System.out.println((i + 1) + ". " + material.substring(material.indexOf(":") + 1));
                toRemoveIndices.add(i);
                found = true;
            }
        }
        if (!found) {
            UI.message("No materials found for the given course ID.\n");
            return;
        }

        System.out.println("Enter the number of the material to remove (or type 'cancel' to exit):");
        String input = scanner.nextLine().toUpperCase();
        if ("CANCEL".equalsIgnoreCase(input)) {
            UI.message("Removal cancelled.");
            return;
        }

        int materialIndex;
        try {
            materialIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            UI.message("Invalid input. Please enter a number or 'cancel' to exit.\n");
            return;
        }

        if (materialIndex < 1 || materialIndex > toRemoveIndices.size()) {
            UI.message("Invalid input. Please enter a valid number.\n");
            return;
        }

        int materialToRemoveIndex = toRemoveIndices.get(materialIndex - 1);
        String materialToRemove = courseMaterialList.get(materialToRemoveIndex);

        courseMaterialList.remove(materialToRemoveIndex);

        switch (materialToRemove.substring(materialToRemove.indexOf(":") + 1)) {
            case "LECTURE NOTES" ->
                materialCounts[0]--;
            case "LECTURE SLIDES" ->
                materialCounts[1]--;
            case "TUTORIAL SLIDES" ->
                materialCounts[2]--;
            case "TUTORIAL QUESTIONS" ->
                materialCounts[3]--;
            case "PRACTICAL NOTES" ->
                materialCounts[4]--;
            case "PRACTICAL QUESTIONS" ->
                materialCounts[5]--;
        }

        UI.message("Material removed successfully.");
    }

    private void displayCourseListMaterial() {
        if (courseMaterialList.isEmpty()) {
            UI.nextSlide();
            UI.message("No material added to any course yet.");
            UI.exit();
            return;
        }

        UI.nextSlide();
        UI.title("Course List with Materials");
        for (Course course : courseList) {
            System.out.println("Course ID        : " + course.getCourseCode());
            System.out.println("Course Name      : " + course.getCourseName());
            System.out.println("Course Materials");
            boolean materialsFound = false;
            for (String material : courseMaterialList) {
                if (material.startsWith(course.getCourseCode() + ":")) {
                    System.out.println("- " + material.substring(material.indexOf(":") + 1));
                    materialsFound = true;
                }
            }
            if (!materialsFound) {
                System.out.println("- No materials found for this course ");
            }
            System.out.println("-------------------------------------------------\n");
        }
    }

    private void displayCourseListDetails() {
        if (courseList.isEmpty()) {
            UI.nextSlide();
            UI.message("No course added course material.");
            UI.exit();
            return;
        }

        UI.title("Current semester available courses");
        for (Course course : courseList) {
            System.out.println("Course Code  : " + course.getCourseCode() + "\nName         : " + course.getCourseName()
                    + "\nCourse Type  : " + course.getCourseType() + "\nCourse Fee   : RM" + course.getPrice()
                    + "\nCredit Hours : " + course.getCreditHour() + "\n");
        }
        UI.pressToCont();
    }

    public void displayCourseTakenByProgramme() {
        DoublyLinkedList<Programme> programmes = faculty.getProgrammeList();
        if (programmes.isEmpty()) {
            UI.nextSlide();
            System.out.println("No programmes available.");
            UI.exit();
            return;
        }

        DoublyLinkedList<ProgrammeCourseAssociation> programmeCourseAssociations = faculty.getProgrammeCourseList();
        if (programmeCourseAssociations.isEmpty()) {
            UI.nextSlide();
            System.out.println("Programme hasn't added any course.");
            UI.exit();
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Faculty: " + faculty.getFacultyName());
        System.out.println("------------------------------------------------------------------");
        for (Programme programme : programmes) {
            System.out.println("Programme ID   : " + programme.getProgrammeId()
                    + "\nProgramme Name : " + programme.getProgrammeName());

            boolean programmeHasCourses = false;
            for (ProgrammeCourseAssociation association : programmeCourseAssociations) {
                if (association.getProgrammeId().equals(programme.getProgrammeId())) {
                    String courseCode = association.getCourseCode();
                    boolean courseFound = false;
                    for (Course course : courseList) {
                        if (course.getCourseCode().equals(courseCode)) {
                            if (!programmeHasCourses) {
                                System.out.println(" == Courses for this Programme == ");
                                programmeHasCourses = true;
                            }
                            System.out.println("");
                            System.out.println("    - Course Code : " + course.getCourseCode()
                                    + "\n    - Name        : " + course.getCourseName()
                                    + "\n    - Course Type : " + association.getCourseType());
                            courseFound = true;
                            break;
                        }
                    }
                    if (!courseFound) {
                        System.out.println("    - Course with code " + courseCode + " not found.");
                    }
                }
            }
            if (!programmeHasCourses) {
                System.out.println("  No courses found for this programme.");
            }
            UI.nextSlide();
        }
        UI.pressToCont();
    }

    private void displayCourseList() {
        if (courseList.isEmpty()) {
            UI.nextSlide();
            UI.message("No courses added.");
            UI.exit();
            return;
        }
        UI.nextSlide();
        UI.title("Course List");
        System.out.println("Course Code         Course Name");
        System.out.println("--------------------------------------------------------");
        for (Course course : courseList) {
            System.out.println(course.getCourseCode() + "            " + course.getCourseName());
        }
        UI.nextSlide();
    }

    public void courseMaterialSummaryReport(int[] materialCounts) {
        UI.nextSlide();
        UI.title("Course Material Summary Report");

        int totalMaterials = courseMaterialList.size();
        System.out.println("Total number of course materials assigned: " + totalMaterials);
        System.out.println("-------------------------------------------------");

        UI.title("Course Material Details");
        System.out.println("- Lecture Notes       : " + materialCounts[0]);
        System.out.println("- Lecture Slides      : " + materialCounts[1]);
        System.out.println("- Tutorial Slides     : " + materialCounts[2]);
        System.out.println("- Tutorial Questions  : " + materialCounts[3]);
        System.out.println("- Practical Notes     : " + materialCounts[4]);
        System.out.println("- Practical Questions : " + materialCounts[5]);
        System.out.println("\n");

        int totalCourses = courseList.size();
        int coursesWithMaterials = 0;

        if (courseMaterialList.isEmpty()) {
            UI.message("No course materials added.");
        } else {
            for (Course course : courseList) {
                DoublyLinkedList<String> courseMaterials = new DoublyLinkedList<>();

                for (String material : courseMaterialList) {
                    if (material.contains(course.getCourseCode())) {
                        courseMaterials.add(material.substring(material.indexOf(":") + 1));
                    }
                }

                if (!courseMaterials.isEmpty()) {
                    coursesWithMaterials++;
                }
            }
        }

        if (courseMaterialList.isEmpty()) {
            return;
        }
        UI.title("Course's Course Material Details");
        for (Course course : courseList) {
            System.out.println("Course ID: " + course.getCourseCode());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Materials:");

            DoublyLinkedList<String> courseMaterials = new DoublyLinkedList<>();

            for (String material : courseMaterialList) {
                if (material.contains(course.getCourseCode())) {
                    courseMaterials.add(material.substring(material.indexOf(":") + 1));
                }
            }

            if (courseMaterials.isEmpty()) {
                System.out.println("- No materials have been added to this course yet!");
            } else {
                for (String material : courseMaterials) {
                    System.out.println("- " + material);
                }
            }
            System.out.println("-------------------------------------------------\n");
        }
        System.out.println("\nCourses registered with materials: " + coursesWithMaterials + "/" + totalCourses);

        UI.nextSlide();
        UI.pressToCont();
    }
}
