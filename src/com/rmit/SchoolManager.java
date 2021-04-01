package com.rmit;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SchoolManager {
    public static final String DEFAULT_STUDENTS_FILENAME = "students.csv";
    public static final String DEFAULT_COURSES_FILENAME = "courses.csv";
    public static final String DEFAULT_ENROLMENTS_FILENAME = "enrolments.csv";
    private static final SchoolManager INSTANCE = new SchoolManager();
    private StudentEnrolmentManager enrolments;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    private SchoolManager() {
        this.enrolments = new StudentEnrolmentList();
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public static SchoolManager getInstance() {
        return INSTANCE;
    }

    public String[] getStudents() {
        String[] studentArray = new String[this.students.size()];
        for (int i = 0; i < this.students.size(); i++) {
            Student student = this.students.get(i);
            studentArray[i] = String.format("%s, %s",
                    student.getName(),
                    new SimpleDateFormat("dd/MM/yyyy").format(student.getBirthDay()));
        }
        return studentArray;
    }

    public String[] getCourses() {
        String[] courseArray = new String[this.courses.size()];
        for (int i = 0; i < this.courses.size(); i++) {
            Course course = this.courses.get(i);
            courseArray[i] = String.format("%s, %d",
                    course.getName(),
                    course.getCredits());
        }
        return courseArray;
    }

    public String[] getAllEnrolments() {
        StudentEnrolment[] allEnrolments = this.enrolments.getAll();
        return convertEnrolmentArrayToStringArray(allEnrolments);
    }

    public String[] getOneEnrolments(Integer studentIndex,
                                     Integer courseIndex,
                                     String semester) {
        String studentName = (studentIndex == null) ? null : this.students.get(studentIndex).getName();
        String courseName = (courseIndex == null) ? null : this.courses.get(courseIndex).getName();
        StudentEnrolment[] enrolments = this.enrolments.getOne(studentName, courseName, semester);
        return convertEnrolmentArrayToStringArray(enrolments);
    }

    private String[] convertEnrolmentArrayToStringArray(StudentEnrolment[] enrolments) {
        String[] enrolmentArray = new String[enrolments.length];
        for (int i = 0; i < enrolments.length; i++) {
            StudentEnrolment enrolment = enrolments[i];
            enrolmentArray[i] = String.format("%s, %s, %s",
                    enrolment.getCourse().getName(),
                    enrolment.getStudent().getName(),
                    enrolment.getSemester());
        }
        return enrolmentArray;
    }

    private StudentEnrolment constructNewEnrolment(int studentIndex,
                                                   int courseIndex,
                                                   String semester) throws Exception {
        Student student = this.students.get(studentIndex);
        Course course = this.courses.get(courseIndex);
        if (student != null && course != null) {
            this.enrolments.add(new StudentEnrolment(student, course, semester));
        } else if (student == null) {
            throw new Exception("Student not found.");
        } else if (course == null) {
            throw new Exception("Course not found.");
        }
        return new StudentEnrolment(student, course, semester);
    }

    public void updateEnrolment(int oldEnrolmentIndex,
                                Integer updatedStudentIndex,
                                Integer updatedCourseIndex,
                                String updatedSemester) {
        StudentEnrolment oldEnrolment = this.enrolments.get(oldEnrolmentIndex);
        StudentEnrolment updatedEnrolment = new StudentEnrolment(
                (updatedStudentIndex == null) ? oldEnrolment.getStudent() : this.students.get(updatedStudentIndex),
                (updatedCourseIndex == null) ? oldEnrolment.getCourse() : this.courses.get(updatedCourseIndex),
                (updatedSemester == null) ? oldEnrolment.getSemester() : updatedSemester);
        this.enrolments.update(oldEnrolment, updatedEnrolment);
    }

    public void deleteEnrolment(int enrolmentIndex) {
        StudentEnrolment enrolment = this.enrolments.get(enrolmentIndex);
        this.enrolments.delete(enrolment);
    }

    public void enroll(int studentIndex, int courseIndex, String semester) throws Exception {
        StudentEnrolment newEnrolment = this.constructNewEnrolment(studentIndex, courseIndex, semester);
        this.enrolments.add(newEnrolment);
    }

    public int importStudents() {
        return this.importStudents(SchoolManager.DEFAULT_STUDENTS_FILENAME);
    }

    public int importStudents(String fileName) {
        try {
            String[] lines = FileManager.readFile(fileName);
            for (String data:lines) {
                this.students.add(new Student(data));
            }
            return lines.length;
        } catch (FileNotFoundException e) {
            return 0;
        }
    }

    public int importCourses() {
        return this.importCourses(SchoolManager.DEFAULT_COURSES_FILENAME);
    }

    public int importCourses(String fileName) {
        try {
            String[] lines = FileManager.readFile(fileName);
            for (String data:lines) {
                this.courses.add(new Course(data));
            }
            return lines.length;
        } catch (FileNotFoundException e) {
            return 0;
        }
    }
}
