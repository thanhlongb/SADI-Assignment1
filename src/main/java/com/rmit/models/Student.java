package com.rmit.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a student.
 */
public class Student {
    private String id;
    private String name;
    private Date birthDay;

    /**
     * Creates a student with randomized id.
     */
    public Student() {
        this.id = "";
        this.name = "";
        this.birthDay = new Date();
    }

    /**
     * Creates a students using a data string read from a csv file.
     * @param rawData Data string from a csv file.
     */
    public Student(String rawData) {
        String[] data = rawData.split(",");
        this.id = data[0];
        this.name = data[1];
        try {
            this.birthDay = new SimpleDateFormat("dd/MM/yyyy").parse(data[2]);
        } catch (ParseException ignored) {
            this.birthDay = new Date();
        }
    }

    /**
     * Returns the student's id.
     * @return A string representing the student's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the student's name.
     * @return A string representing the student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student's birthday.
     * @return A Date object representing the student's birthday.
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * Returns the student's birthday.
     * @return A string representing the student's birthday.
     */
    public String getBirthDayString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthDay());
    }

    /**
     * Compare if this student and another student are equals.
     * @param o The object that should be a Student object.
     * @return A boolean that is the result of the comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) &&
                name.equals(student.name) &&
                birthDay.equals(student.birthDay);
    }

    /**
     * Returns the hash code of this Student object.
     * @return A int representing the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDay);
    }
}
