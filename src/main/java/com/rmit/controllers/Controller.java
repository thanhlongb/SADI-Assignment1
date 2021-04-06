package com.rmit.controllers;

import com.rmit.models.School;
import com.rmit.utils.DefaultFileName;
import com.rmit.utils.FileManager;
import com.rmit.views.View;

import java.util.concurrent.CancellationException;

/**
 * The sole controller for this program.
 */
public class Controller {
    private School school = new School();
    private View view = View.getInstance();

    /**
     * Get the single instance of this class.
     * @return A controller instance.
     */
    public static Controller getInstance() {
        return INSTANCE;
    }
    private static final Controller INSTANCE = new Controller();

    /**
     * Import the courses and students data from csv files.
     * If the arguments are not passed in, default file names will be used.
     * @param args Arguments from the console command.
     *             The first argument is the course's file name.
     *             The second argument is the student's file name.
     */
    public void importData(String[] args) {
        int importedCoursesCount, importedStudentsCount;
        if (args.length > 0) {
            importedCoursesCount = school.importCourses(args[0]);
        } else {
            importedCoursesCount = school.importCourses();
        }
        view.printMessage(String.format("Found %s courses.", importedCoursesCount));
        if (args.length > 1) {
            importedStudentsCount = school.importStudents(args[1]);
        } else {
            importedStudentsCount = school.importStudents();
        }
        view.printMessage(String.format("Found %s students.", importedStudentsCount));
    }

    /**
     * Print all the students to the console.
     */
    public void showAllStudents() {
        view.printStringArray(school.getStudents());
    }

    /**
     * Print all the courses to the console.
     */
    public void showAllCourses() {
        view.printStringArray(school.getCourses());
    }

    /**
     * Print all the enrolments to the console.
     */
    public void showAllEnrolments() {
        view.printStringArray(school.getAllEnrolments());
    }

    /**
     * Print all the courses of a student in a semester.
     */
    public void printAllCourseOfStudentInSemester() {
        String semester;
        int studentIndex;
        String[] students = school.getStudents();
        try {
            studentIndex = view.promptUserOption(students, "Select a student:", true);
            semester = view.promptUserString("Enter the semester:");
            String[] enrolments = school.getOneEnrolments(studentIndex, null, semester);
            if (enrolments.length > 0) {
                view.printStringArray(enrolments);
                this.promptExportEnrolment(enrolments);
            } else {
                view.printMessage("No course found!");
            }
        } catch (CancellationException e) {
            view.printMessage("Operation cancelled.");
        }
    }

    /**
     * Ask if the user want to export the enrolments list to a csv file.
     * If yes then export them.
     * @param enrolments The enrolments to be exported.
     */
    private void promptExportEnrolment(String[] enrolments) {
        if (view.promptYesOrNo("Do you want to export this list?")) {
            for (String enrolment:enrolments) {
                FileManager.writeFile(DefaultFileName.ENROLMENTS.value, enrolment);
            }
        }
    }

    /**
     * Print all the students of a course in a semester.
     */
    public void printAllStudentOfCourseInSemester() {
        String semester;
        int courseIndex;
        String[] courses = school.getCourses();
        try {
            courseIndex = view.promptUserOption(courses, "Select a course:", true);
            semester = view.promptUserString("Enter the semester:");
            String[] enrolments = school.getOneEnrolments(null, courseIndex, semester);
            if (enrolments.length > 0) {
                view.printStringArray(enrolments);
                this.promptExportEnrolment(enrolments);
            } else {
                view.printMessage("No student found!");
            }
        } catch (CancellationException e) {
            view.printMessage("Operation cancelled.");
        }
    }

    /**
     * Print all courses in a semester.
     */
    public void printAllCourseInSemester() {
        String semester = view.promptUserString("Enter the semester:");
        String[] enrolments = school.getOneEnrolments(null, null, semester);
        if (enrolments.length > 0) {
            view.printStringArray(enrolments);
            this.promptExportEnrolment(enrolments);
        } else {
            view.printMessage("No enrolment found!");
        }
    }

    /**
     * Enroll a student into a course.
     */
    public void enrollStudentIntoCourse() {
        int studentIndex, courseIndex;
        String semester = "";
        String[] students = school.getStudents();
        String[] courses = school.getCourses();
        try {
            studentIndex = view.promptUserOption(students, "Select a student:", true);
            courseIndex = view.promptUserOption(courses, "Select a course:", true);
            semester = view.promptUserString("Enter the semester:");
            school.enroll(studentIndex, courseIndex, semester);
            view.printMessage("Enrolment added.");
        } catch (CancellationException e) {
            view.printMessage("Enrolment process cancelled.");
        } catch (Exception e) {
            view.printMessage("Unable to enroll student.");
        }
    }

    /**
     * Update an enrolment.
     */
    public void updateEnrolment() {
        int enrolmentIndex;
        Integer studentIndex = null;
        Integer courseIndex = null;
        String semester = null;
        String[] enrolments = school.getAllEnrolments();
        String[] students = school.getStudents();
        String[] courses = school.getCourses();
        if (enrolments.length == 0) {
            view.printMessage("Enrolment is empty, please add some and try this again.");
            return;
        }
        try {
            enrolmentIndex = view.promptUserOption(enrolments, "Select an enrolment to update:", true);
            if (view.promptYesOrNo("Do you want to update the student?")) {
                studentIndex = view.promptUserOption(students, "Select a student:", true);
            }
            if (view.promptYesOrNo("Do you want to update the course?")) {
                courseIndex = view.promptUserOption(courses, "Select a course:", true);
            }
            if (view.promptYesOrNo("Do you want to update the semester?")) {
                semester = view.promptUserString("Enter new semester name:");
            }
            school.updateEnrolment(enrolmentIndex, studentIndex, courseIndex, semester);
            view.printMessage("Enrolment updated.");
        } catch (CancellationException e) {
            view.printMessage("Enrolment update cancelled.");
        }
    }

    /**
     * Delete an enrolment.
     */
    public void deleteEnrolment() {
        int enrolmentIndex;
        String[] enrolments = school.getAllEnrolments();
        if (enrolments.length == 0) {
            view.printMessage("There is no enrolment to delete.");
            return;
        }
        try {
            enrolmentIndex = view.promptUserOption(enrolments, "Select a enrolment:", true);
            school.deleteEnrolment(enrolmentIndex);
        } catch (Exception e) {
            view.printMessage("Enrolment deletion process cancelled.");
        }
    }

    /**
     * Exit the program.
     */
    public void quitProgram() {
        view.printQuitMessage();
        System.exit(0);
    }

    /**
     * Show the list of features of this program and allow the user to choose one.
     */
    public void showMainMenu() {
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
            case 0: this.showAllStudents(); break;
            case 1: this.showAllCourses(); break;
            case 2: this.showAllEnrolments(); break;
            case 3: this.printAllCourseOfStudentInSemester(); break;
            case 4: this.printAllStudentOfCourseInSemester(); break;
            case 5: this.printAllCourseInSemester(); break;
            case 6: this.enrollStudentIntoCourse(); break;
            case 7: this.updateEnrolment(); break;
            case 8: this.deleteEnrolment(); break;
            case 9: this.quitProgram(); break;
            default: break;
        }
    }

    /**
     * Wait for the user to read the output of this program.
     */
    public void waitForInput() {
        view.standBy();
    }

}
