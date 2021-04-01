package com.rmit;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class View {
    private static final View INSTANCE = new View();
    private Scanner scanner;

    private View() {
        this.scanner = new Scanner(System.in);
    }

    public static View getInstance() {
        return INSTANCE;
    }

    public void standBy() {
        System.out.print(">> Press ENTER to continue...");
        this.scanner.nextLine();
    }

    public int promptUserOption(String[] options) throws CancellationException {
        return this.promptUserOption(options, null);
    }

    public int promptUserOption(String[] options, String promptText) throws CancellationException {
        return this.promptUserOption(options, promptText, false);
    }

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

    public String promptUserString(String promptText) {
        if (promptText != null) {
            System.out.print(promptText + " ");
        }
        return this.scanner.nextLine();
    }

    public boolean promptYesOrNo(String promptText) {
        String result = promptUserString(promptText + " (Y for yes)");
        return result.toLowerCase().equals("y");
    }


    public void printStringArray(String[] array) {
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(String.format("[%s] %s", i, array[i]));
            }
        } else {
            System.out.println("There is no record.");
        }
    }

    public void printQuitMessage() {
        String exitMessage = "Bye!";
        System.out.println(exitMessage);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.scanner.close();
    }
}
