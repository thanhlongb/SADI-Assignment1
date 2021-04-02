package com.rmit.models;

import java.util.Objects;
import java.util.UUID;

public class Course {
    private String id;
    private String name;
    private Integer credits;

    public Course() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.credits = 0;
    }

    public Course(String rawData) {
        String[] data = rawData.split(",");
        this.id = data[0];
        this.name = data[1];
        this.credits = Integer.valueOf(data[2]);
    }

    public Course(String name, Integer credits) {
        this();
        this.name = name;
        this.credits = credits;
    }

    public Course(String id, String name, Integer credits) {
        this(name, credits);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) &&
                name.equals(course.name) &&
                credits.equals(course.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits);
    }
}
