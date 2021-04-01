package com.rmit.models;

import java.util.Objects;

public class StudentEnrolment {
    //  student , course, semester (2021A, 2021B etc. This is a string)
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment() {
        this.student = new Student();
        this.course = new Course();
        this.semester = "";
    }

    public StudentEnrolment(Student student, Course course, String semester) {
        this();
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s",
                this.student.getName(),
                this.course.getName(),
                this.semester);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrolment that = (StudentEnrolment) o;
        return student.equals(that.student) &&
                course.equals(that.course) &&
                semester.equals(that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, semester);
    }
}
