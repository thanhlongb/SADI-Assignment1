package com.rmit.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Student {
    private String id;
    private String name;
    private Date birthDay;

    public Student() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.birthDay = new Date();
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getBirthDayString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthDay());
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) &&
                name.equals(student.name) &&
                birthDay.equals(student.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDay);
    }
}
