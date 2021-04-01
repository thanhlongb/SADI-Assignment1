package com.rmit;

import java.io.*;
import java.util.concurrent.CancellationException;

public class Main {
    private static SchoolManager schoolManager = SchoolManager.getInstance();
    private static View view = View.getInstance();
    public static void main(String[] args) {
        importData(args);
        while (true) {
            showMainMenu();
            view.waitForUserToReadOutput();
        }
    }

    private static void importData(String[] args) {
        int importedCoursesCount, importedStudentsCount;
        if (args.length > 0) {
            importedCoursesCount = schoolManager.importCourses(args[0]);
        } else {
            importedCoursesCount = schoolManager.importCourses();
        }
        view.printMessage(String.format("Found %s courses.", importedCoursesCount));
        if (args.length > 1) {
            importedStudentsCount = schoolManager.importStudents(args[1]);
        } else {
            importedStudentsCount = schoolManager.importStudents();
        }
        view.printMessage(String.format("Found %s students.", importedStudentsCount));
    }

    private static void showMainMenu() {
        String[] options = {
                "Print all students",
                "Print all courses",
                "Print all enrolments",
                "Print all courses for 1 student in 1 semester",
                "Print all students of 1 course in 1 semester",
                "Prints all courses offered in 1 semester",
                "Enroll a student into a course",
                "Update an enrolment",
                "Delete an enrolment",
                "Quit"
        };
        int userOption = view.promptUserOption(options, "Select the operation you want to execute:");
        switch (userOption) {
            case 0: showAllStudents(); break;
            case 1: showAllCourses(); break;
            case 2: showAllEnrolments(); break;
            case 3: printAllCourseOfStudentInSemester(); break;
            case 4: printAllStudentOfCourseInSemester(); break;
            case 5: printAllCourseInSemester(); break;
            case 6: enrollStudentIntoCourse(); break;
            case 7: updateEnrolment(); break;
            case 8: deleteEnrolment(); break;
            case 9: quitProgram(); break;
            default: break;
        }
    }

    private static void showAllStudents() {
        view.printStringArray(schoolManager.getStudents());
    }

    private static void showAllCourses() {
        view.printStringArray(schoolManager.getCourses());
    }

    private static void showAllEnrolments() {
        view.printStringArray(schoolManager.getAllEnrolments());
    }

    private static void printAllCourseOfStudentInSemester() {
        String studentName, semester;
        studentName = view.promptUserString("Enter student name:");
        semester = view.promptUserString("Enter the semester:");
        String[] enrolments = schoolManager.getOneEnrolments(studentName, null, semester);
        if (enrolments.length > 0) {
            view.printStringArray(enrolments);
            askIfUserWantToExportEnrolment(enrolments);
        } else {
            System.out.println("No course found!");
        }
    }

    private static void printAllStudentOfCourseInSemester() {
        String courseName, semester;
        courseName = view.promptUserString("Enter course name:");
        semester = view.promptUserString("Enter the semester:");
        String[] enrolments = schoolManager.getOneEnrolments(null, courseName, semester);
        if (enrolments.length > 0) {
            view.printStringArray(enrolments);
            askIfUserWantToExportEnrolment(enrolments);
        } else {
            System.out.println("No student found!");
        }
    }

    private static void printAllCourseInSemester() {
        String semester;
        semester = view.promptUserString("Enter the semester:");
        String[] enrolments = schoolManager.getOneEnrolments(null, null, semester);
        if (enrolments.length > 0) {
            view.printStringArray(enrolments);
            askIfUserWantToExportEnrolment(enrolments);
        } else {
            System.out.println("No enrolment found!");
        }
    }

    private static void askIfUserWantToExportEnrolment(String[] enrolments) {
        if (view.promptYesOrNo("Do you want to export this list?")) {
            for (String enrolment:enrolments) {
                FileManager.writeFile(enrolment);
            }
        }
    }

    private static void enrollStudentIntoCourse() {
        int studentIndex, courseIndex;
        String semester = "";
        String[] students = schoolManager.getStudents();
        String[] courses = schoolManager.getCourses();
        try {
            studentIndex = view.promptUserOption(students, "Select a student:", true);
            courseIndex = view.promptUserOption(courses, "Select a course:", true);
            semester = view.promptUserString("Enter the semester:");
            schoolManager.enroll(studentIndex, courseIndex, semester);
            System.out.println("Enrolment added.");
        } catch (CancellationException e) {
            System.out.println("Enrolment process cancelled.");
        } catch (Exception e) {
            System.out.println("Unable to enroll student.");
        }
    }

    private static void updateEnrolment() {
        int enrolmentIndex;
        Integer studentIndex = null;
        Integer courseIndex = null;
        String semester = null;
        String[] enrolments = schoolManager.getAllEnrolments();
        String[] students = schoolManager.getStudents();
        String[] courses = schoolManager.getCourses();
        try {
            enrolmentIndex = view.promptUserOption(enrolments, "Select an enrolment to update:", true);
            if (view.promptYesOrNo("Do you want to update student?")) {
                studentIndex = view.promptUserOption(students, "Select a student:", true);
            }
            if (view.promptYesOrNo("Do you want to update course?")) {
                courseIndex = view.promptUserOption(courses, "Select a course:", true);
            }
            if (view.promptYesOrNo("Do you want to update semester?")) {
                semester = view.promptUserString("Updated semester name:");
            }
            schoolManager.updateEnrolment(enrolmentIndex, studentIndex, courseIndex, semester);
            System.out.println("Enrolment updated.");
        } catch (CancellationException e) {
            System.out.println("Enrolment update cancelled.");
        }
    }

    private static void deleteEnrolment() {
        int enrolmentIndex;
        String[] enrolments = schoolManager.getAllEnrolments();
        if (enrolments.length == 0) {
            System.out.println("There is no enrolment to delete.");
            return;
        }
        try {
            enrolmentIndex = view.promptUserOption(enrolments, "Select a enrolment:", true);
            schoolManager.deleteEnrolment(enrolmentIndex);
        } catch (Exception e) {
            System.out.println("Enrolment deletion process cancelled.");
        }
    }

    private static void quitProgram() {
        view.printQuitMessage();
        System.exit(0);
    }
}
