package com.rmit;

import com.rmit.controllers.Controller;

/**
 * The main class the program.
 * This class run an infinite loop to run the software
 * until the user choose to quit.
 */
public class Main {
    private static Controller controller = Controller.getInstance();

    /**
     * The main method.
     * @param args  The args gathered from the console flags.
     */
    public static void main(String[] args) {
        controller.importData(args);
        controller.askToLoadEnrolmentsFromSpecificFile();
        while (true) {
            controller.showMainMenu();
            controller.waitForInput();
        }
    }
}
