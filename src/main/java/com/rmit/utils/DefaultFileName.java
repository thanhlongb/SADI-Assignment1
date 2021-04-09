package com.rmit.utils;

/**
 * Enumeration of default data file names.
 */
public enum DefaultFileName {
    STUDENTS("students.csv"),
    COURSES("courses.csv"),
    ENROLMENTS("default.csv");

    public final String value;
    DefaultFileName(String value) {
        this.value = value;
    }
}
