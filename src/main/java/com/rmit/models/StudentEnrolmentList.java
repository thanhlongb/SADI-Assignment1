package com.rmit.models;

import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<>();

    @Override
    public Boolean add(StudentEnrolment studentEnrolment) {
        if (!isEnrolmentExist(studentEnrolment)) {
            this.enrolments.add(studentEnrolment);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(StudentEnrolment studentEnrolment) {
        int enrolmentIndex = findEnrolmentIndex(studentEnrolment);
        if (enrolmentIndex != -1) {
            this.enrolments.remove(enrolmentIndex);
            return true;
        }
        return false;
    }

    @Override
    public StudentEnrolment[] getOne(String studentId,
                                     String courseId,
                                     String semester) {
        ArrayList<StudentEnrolment> returnEnrolments = new ArrayList<>();
        for (StudentEnrolment enrolment: this.enrolments) {
            if (studentId != null && !enrolment.getStudent().getId().equals(studentId)) {
                continue;
            }
            if (courseId != null && !enrolment.getCourse().getId().equals(courseId)) {
                continue;
            }
            if (semester != null && !enrolment.getSemester().equals(semester)) {
                continue;
            }
            returnEnrolments.add(enrolment);
        }
        return returnEnrolments.toArray(new StudentEnrolment[returnEnrolments.size()]);
    }

    @Override
    public StudentEnrolment[] getAll() {
        return this.enrolments.toArray(new StudentEnrolment[this.enrolments.size()]);
    }

    @Override
    public Boolean update(StudentEnrolment oldStudentEnrolment,
                          StudentEnrolment updatedStudentEnrolment) {
        int oldEnrolment = findEnrolmentIndex(oldStudentEnrolment);
        if (oldEnrolment != -1) {
            this.enrolments.set(oldEnrolment, updatedStudentEnrolment);
            return true;
        }
        return false;
    }

    @Override
    public StudentEnrolment get(int index) {
        return this.enrolments.get(index);
    }

    private Boolean isEnrolmentExist(StudentEnrolment studentEnrolment) {
        return findEnrolmentIndex(studentEnrolment) != -1;
    }

    private int findEnrolmentIndex(StudentEnrolment studentEnrolment) {
        for (int i = 0; i < this.enrolments.size(); i++) {
            if (this.enrolments.get(i).equals(studentEnrolment)) {
                return i;
            }
        }
        return -1;
    }
}
