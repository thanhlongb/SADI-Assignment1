package com.rmit.models;

import java.util.Objects;

/**
 * Represents a student enrolment.
 */
public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    /**
     * Creates a student enrolment.
     */
    public StudentEnrolment() {
        this.student = new Student();
        this.course = new Course();
        this.semester = "";
    }

    /**
     * Creates a student enrolment with specific information.
     * @param student   The student of this enrolment.
     * @param course    The course of this enrolment.
     * @param semester  The semester of this enrolment.
     */
    public StudentEnrolment(Student student, Course course, String semester) {
        this();
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    /**
     * Returns the student of this enrolment.
     * @return  A Student object.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Set the student for this enrolment.
     * @param student   The student to be set.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Returns the course of this enrolment.
     * @return  A Course object.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Set the course for this enrolment.
     * @param course    The course to be set.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Returns the semester of this enrolment.
     * @return  A string representing the semester's name.
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Set the semester for this enrolment.
     * @param semester  The semester name to be set.
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Returns a string representing the information of this enrolment.
     * @return  A string representing this enrolment's information.
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s",
                this.student.getName(),
                this.course.getName(),
                this.semester);
    }

    /**
     * Compare if this enrolment and another enrolment are equals.
     * @param o The object that should be a StudentEnrolment object.
     * @return A boolean that is the result of the comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrolment that = (StudentEnrolment) o;
        return student.equals(that.student) &&
                course.equals(that.course) &&
                semester.equals(that.semester);
    }

    /**
     * Returns the hash code of this StudentEnrolment object.
     * @return A int representing the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(student, course, semester);
    }
}
