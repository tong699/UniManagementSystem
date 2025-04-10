/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kong Jia Le
 */
public class Menu {
    private final String title;
    private final ArrayList<String> options;

    public Menu(String title) {
        this.title = title;
        this.options = new ArrayList<>();
    }

    public void add(String option) {
        options.add(option);
    }

    public int displayAndGetChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            UI.nextSlide();
            UI.title(title);
            StringBuilder leftColumn = new StringBuilder();
            StringBuilder rightColumn = new StringBuilder();

            // Populate left and right columns
            for (int i = 0; i < options.size(); i++) {
                String formattedOption = String.format("%2d. %s", (i + 1), options.get(i));
                if (i % 2 == 0) {
                    leftColumn.append(formattedOption).append("\n");
                } else {
                    rightColumn.append(formattedOption).append("\n");
                }
            }

            // Combine left and right columns
            String[] leftLines = leftColumn.toString().split("\n");
            String[] rightLines = rightColumn.toString().split("\n");

            for (int i = 0; i < Math.max(leftLines.length, rightLines.length); i++) {
                String left = (i < leftLines.length) ? leftLines[i] : "";
                String right = (i < rightLines.length) ? rightLines[i] : "";
                System.out.printf("%-50s%s\n", left, right);
            }

            System.out.print("\nEnter your option: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= options.size()) {
                    validInput = true;
                } else {
                    UI.message("Invalid option, please enter a number between 1 and " + options.size() + ".");
                }
            } else {
                UI.message("Invalid input, please enter a number.");
                scanner.next();
            }
        }

        return choice;
    }

//    public static void main(String[] args) {
//        Menu menu = new Menu("Title");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//        menu.add("Option Option Option");
//
//        int choice = menu.displayAndGetChoice();
//        System.out.println("You chose option " + choice);
//    }
}
