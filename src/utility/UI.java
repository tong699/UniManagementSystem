/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author Kong Jia Le
 */
public class UI {

    public static void title(String title) {
        System.out.println(":: " + title + " ::\n");
    }

    public static void nextSlide() {
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------\n\n");
    }

    public static void exit() {
        message("Exit...\n");
    }

    public static String truncateString(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength - 3) + "...";
        }
        return str;
    }

    public static boolean confirmUI(Object o) {
        System.out.println(o);
        System.out.print("Please confirm your action. Proceed? (Y/Else): ");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim().toUpperCase();
        if (confirm.equals("Y")) {
            message("Action Confirmed.\n");
            return true;
        } else {
            return false;
        }
    }

    public static boolean confirmUI(String s) {
        System.out.println(s);
        System.out.print("Please confirm your action. Proceed? (Y/Else): ");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim().toUpperCase();
        if (confirm.equals("Y")) {
            message("Action Confirmed.\n");
            return true;
        } else {
            return false;
        }
    }

    public static void message(String s) {
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.print(ANSI_BLUE + "Message: " + s + ANSI_RESET);
    }

    public static void pressToCont() {
        message("Press any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
