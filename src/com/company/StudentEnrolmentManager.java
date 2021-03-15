package com.company;

public interface StudentEnrolmentManager {
    // methods: add, update, delete. getOne, getAll.
    Boolean add();
    Student getOne(String studentId);
    Student[] getAll();
    Boolean delete();
    Student update(Student student);
}
