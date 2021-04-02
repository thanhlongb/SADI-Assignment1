package com.rmit.utils;

public enum DefaultFileName {
    STUDENTS("students.csv"),
    COURSES("courses.csv"),
    ENROLMENTS("enrolments.csv");

    public final String value;
    DefaultFileName(String value) {
        this.value = value;
    }
}
