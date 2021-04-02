package com.rmit;

import com.rmit.controllers.Controller;
import com.rmit.views.View;

public class Main {
    private static Controller controller = Controller.getInstance();
    private static View view = View.getInstance();

    public static void main(String[] args) {
        controller.importData(args);
        while (true) {
            showMainMenu();
            waitForInput();
        }
    }

    private static void showMainMenu() {
        String[] options = {
                "Print all students",
                "Print all courses",
                "Print all enrolments",
                "Print all courses of 1 student in 1 semester",
                "Print all students of 1 course in 1 semester",
                "Print all courses offered in 1 semester",
                "Enroll a student into a course",
                "Update an enrolment",
                "Delete an enrolment",
                "Quit"
        };
        int userOption = view.promptUserOption(options, "Select the operation you want to execute:");
        switch (userOption) {
            case 0: controller.showAllStudents(); break;
            case 1: controller.showAllCourses(); break;
            case 2: controller.showAllEnrolments(); break;
            case 3: controller.printAllCourseOfStudentInSemester(); break;
            case 4: controller.printAllStudentOfCourseInSemester(); break;
            case 5: controller.printAllCourseInSemester(); break;
            case 6: controller.enrollStudentIntoCourse(); break;
            case 7: controller.updateEnrolment(); break;
            case 8: controller.deleteEnrolment(); break;
            case 9: controller.quitProgram(); break;
            default: break;
        }
    }

    private static void waitForInput() {
        view.standBy();
    }

}
