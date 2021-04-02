package com.rmit.controllers;

import com.rmit.models.School;
import com.rmit.utils.FileManager;
import com.rmit.views.View;

import java.util.concurrent.CancellationException;

public class Controller {
    private School school = new School();
    private View view = View.getInstance();

    public static Controller getInstance() {
        return INSTANCE;
    }
    private static final Controller INSTANCE = new Controller();

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

    public void showAllStudents() {
        view.printStringArray(school.getStudents());
    }

    public void showAllCourses() {
        view.printStringArray(school.getCourses());
    }

    public void showAllEnrolments() {
        view.printStringArray(school.getAllEnrolments());
    }

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

    private void promptExportEnrolment(String[] enrolments) {
        if (view.promptYesOrNo("Do you want to export this list?")) {
            for (String enrolment:enrolments) {
                FileManager.writeFile(enrolment);
            }
        }
    }

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

    public void quitProgram() {
        view.printQuitMessage();
        System.exit(0);
    }
}
