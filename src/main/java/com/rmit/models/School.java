package com.rmit.models;

import com.rmit.utils.DefaultFileName;
import com.rmit.utils.FileManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represent a school.
 */
public class School {
    private StudentEnrolmentManager enrolments;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    /**
     * Creates a school.
     */
    public School() {
        this.enrolments = new StudentEnrolmentList();
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    /**
     * Returns students in this school.
     * @return A string array of students information.
     */
    public String[] getStudents() {
        String[] studentArray = new String[this.students.size()];
        for (int i = 0; i < this.students.size(); i++) {
            Student student = this.students.get(i);
            studentArray[i] = String.format("%s, %s",
                    student.getName(),
                    student.getBirthDayString());
        }
        return studentArray;
    }

    /**
     * Returns courses in this school.
     * @return  A string array of courses information.
     */
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

    /**
     * Returns enrolments in this school.
     * @return  A string array of enrolments information.
     */
    public String[] getAllEnrolments() {
        StudentEnrolment[] allEnrolments = this.enrolments.getAll();
        return convertEnrolmentArrayToStringArray(allEnrolments);
    }

    /**
     * Returns enrolments based on criteria.
     * @param studentIndex  The index of the student.
     * @param courseIndex   The index of the course.
     * @param semester  The semester name.
     * @return  A string array of enrolments that matches the criteria.
     */
    public String[] findEnrolments(Integer studentIndex,
                                   Integer courseIndex,
                                   String semester) {
        String studentName = (studentIndex == null) ? null : this.students.get(studentIndex).getId();
        String courseName = (courseIndex == null) ? null : this.courses.get(courseIndex).getId();
        StudentEnrolment[] enrolments = this.enrolments.find(studentName, courseName, semester);
        return convertEnrolmentArrayToStringArray(enrolments);
    }

    /**
     * Returns a string array of enrolments information
     * from a list of StudentEnrolments.
     * @param enrolments    The array of StudentEnrolment.
     * @return  A string array of enrolments information.
     */
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

    /**
     * Returns a StudentEnrolment object.
     * @param studentIndex  The index of the student.
     * @param courseIndex   The index of the course.
     * @param semester  The semester name.
     * @return  A StudentEnrolment object.
     * @throws Exception When student or course is not exist.
     */
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

    /**
     * Updates an enrolment.
     * @param oldEnrolmentIndex The index of the old enrolment.
     * @param updatedStudentIndex   The index of the new student value.
     * @param updatedCourseIndex    The index of the new course value.
     * @param updatedSemester   The name of the new semester value.
     */
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

    /**
     * Deletes an enrolment.
     * @param enrolmentIndex    The index of the enrolment to be deleted.
     */
    public void deleteEnrolment(int enrolmentIndex) {
        StudentEnrolment enrolment = this.enrolments.get(enrolmentIndex);
        this.enrolments.delete(enrolment);
    }

    /**
     * Enrolls a student into a course in a semester.
     * @param studentIndex  The index of the student.
     * @param courseIndex   The index of the course.
     * @param semester  The semester name.
     * @throws Exception    When unable to enroll.
     */
    public void enroll(int studentIndex, int courseIndex, String semester) throws Exception {
        StudentEnrolment newEnrolment = this.constructNewEnrolment(studentIndex, courseIndex, semester);
        this.enrolments.add(newEnrolment);
    }

    /**
     * Imports students data from csv file.
     * @return  The number of imported students.
     */
    public int importStudents() {
        return this.importStudents(DefaultFileName.STUDENTS.value);
    }

    /**
     * Imports the students data from a specific csv file.
     * @param fileName  The specific students' csv file name.
     * @return  The number of imported student.
     */
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

    /**
     * Imports the courses data from a specific csv file.
     * @return  The number of imported courses.
     */
    public int importCourses() {
        return this.importCourses(DefaultFileName.COURSES.value);
    }

    /**
     * Import the courses fata from a specific csv file.
     * @param fileName  The specific courses' csv file name.
     * @return  The number of imported courses.
     */
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

    public int importEnrolments() {
        return this.importEnrolments(DefaultFileName.ENROLMENTS.value);
    }

    public int importEnrolments(String fileName) {
        try {
            String[] lines = FileManager.readFile(fileName);
            for (String line:lines) {
                String[] data = line.split(",");
                Student student = findStudentById(data[0]);
                Course course = findCourseById(data[1]);
                String semester = data[2];
                this.enrolments.add(new StudentEnrolment(student, course, semester));
            }
            return lines.length;
        } catch (FileNotFoundException e) {
            return 0;
        }
    }

    private Student findStudentById(String studentId) {
        return this.students.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    private Course findCourseById(String courseId) {
        return this.courses.stream()
                .filter(course -> course.getId().equals(courseId))
                .findFirst()
                .orElse(null);
    }
}
