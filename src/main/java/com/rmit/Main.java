package com.rmit;

import com.rmit.controllers.Controller;
import com.rmit.views.View;

public class Main {
    private static Controller controller = Controller.getInstance();

    public static void main(String[] args) {
        controller.importData(args);
        while (true) {
            controller.showMainMenu();
            controller.waitForInput();
        }
    }
}
