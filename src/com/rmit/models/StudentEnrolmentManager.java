package com.rmit.models;

public interface StudentEnrolmentManager {
    // required methods: add, update, delete. getOne, getAll.
    Boolean add(StudentEnrolment studentEnrolment);
    Boolean delete(StudentEnrolment studentEnrolment);
    StudentEnrolment[] getOne(String studentId,
                              String courseId,
                              String semester);
    StudentEnrolment[] getAll();
    Boolean update(StudentEnrolment oldStudentEnrolment,
                   StudentEnrolment updatedStudentEnrolment);
    // additional methods
    StudentEnrolment get(int index);
}
