package com.rmit.models;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a course
 */
public class Course {
    private String id;
    private String name;
    private Integer credits;

    /**
     * Creates a course with randomized id.
     */
    public Course() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.credits = 0;
    }

    /**
     * Creates a course using a data string read from a csv file.
     * @param rawData Data string from a csv file.
     */
    public Course(String rawData) {
        String[] data = rawData.split(",");
        this.id = data[0];
        this.name = data[1];
        this.credits = Integer.valueOf(data[2]);
    }

    /**
     * Returns the course's id.
     * @return A string representing the course's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Return the course's name.
     * @return A string representing the course's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the course's credit points.
     * @return An Integer object representing the course's credit points.
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     * Compare if this course and another course are equals.
     * @param o The object that should be a Course object.
     * @return A boolean that is the result of the comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) &&
                name.equals(course.name) &&
                credits.equals(course.credits);
    }

    /**
     * Returns the hash code of this Course object.
     * @return A int representing the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits);
    }
}
