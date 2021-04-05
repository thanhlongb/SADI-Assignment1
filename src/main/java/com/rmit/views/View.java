package com.rmit.views;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

/**
 * The sole View of this program.
 * This class handles the output to console
 * and interact with the user.
 */
public class View {
    private static final View INSTANCE = new View();
    private Scanner scanner;

    /**
     * Creates a View object.
     */
    private View() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a single instance of View class.
     * @return  A singleton View object.
     */
    public static View getInstance() {
        return INSTANCE;
    }

    /**
     * Wait for user to read the output.
     */
    public void standBy() {
        System.out.print(">> Press ENTER to continue...");
        this.scanner.nextLine();
    }

    /**
     * Print the options and ask user to select one.
     * @param options   The array of strings of options.
     * @return  The index of selected option.
     * @throws CancellationException    when user want to cancel the operation.
     */
    public int promptUserOption(String[] options) throws CancellationException {
        return this.promptUserOption(options, null);
    }

    /**
     * Print the options and ask user to select one.
     * @param options   The array of strings of options.
     * @param promptText    The text to ask for user option.
     * @return  The index of selected option.
     * @throws CancellationException    when user want to cancel the operation.
     */
    public int promptUserOption(String[] options, String promptText) throws CancellationException {
        return this.promptUserOption(options, promptText, false);
    }

    /**
     * Print the options and ask user to select one.
     * @param options   The array of strings of options.
     * @param promptText    The text to ask for user option.
     * @param isCancelable  Allow user to cancel the operation or not.
     * @return  The index of selected option.
     * @throws CancellationException    when user want to cancel the operation.
     */
    public int promptUserOption(String[] options, String promptText, boolean isCancelable) throws CancellationException {
        int userOption;
        if (promptText != null) {
            System.out.println(promptText);
        }
        this.printStringArray(options);
        while (true) {
            System.out.printf(">> Enter your option%s: ", (isCancelable) ? " (use '-1' to cancel)" : "");
            try {
                userOption = this.scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You supposed to enter a number.");
                continue;
            } finally {
                this.scanner.nextLine();
            }
            if (isCancelable && userOption == -1) throw new CancellationException("Operation cancelled.");
            if (userOption >= 0 && userOption < options.length) {
                break;
            } else {
                System.out.println("You've entered an invalid option, please try again.");
            }
        }
        return userOption;
    }

    /**
     * Ask user to enter a string.
     * @param promptText    The text to ask for user string.
     * @return  A string of user input.
     */
    public String promptUserString(String promptText) {
        if (promptText != null) {
            System.out.print(promptText + " ");
        }
        return this.scanner.nextLine();
    }

    /**
     * Ask user to answer yes or no.
     * @param promptText    A string represent the question.
     * @return  A boolean represent the answer.
     */
    public boolean promptYesOrNo(String promptText) {
        String result = promptUserString(promptText + " (Y for yes)");
        return result.equalsIgnoreCase("y");
    }

    /**
     * Print a string of array line by line to the console.
     * @param array The array of string to be printed.
     */
    public void printStringArray(String[] array) {
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(String.format("[%s] %s", i, array[i]));
            }
        } else {
            System.out.println("There is no record.");
        }
    }

    /**
     * Print the quit message to the console.
     */
    public void printQuitMessage() {
        String exitMessage = "Bye!";
        System.out.println(exitMessage);
    }

    /**
     * Print a message to the console.
     * @param message   The message to be printed out.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Execute some operations when this object is killed.
     * Close the scanner.
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.scanner.close();
    }
}
