package com.rmit.models;

import java.util.ArrayList;

/**
 * A list for StudentEnrolment objects.
 */
public class StudentEnrolmentList implements StudentEnrolmentManager {
    private ArrayList<StudentEnrolment> enrolments = new ArrayList<>();

    /**
     * Add an enrolment into the list.
     * @param studentEnrolment  An enrolment to be added.
     * @return  A boolean represents if the operation is successful or not.
     */
    @Override
    public Boolean add(StudentEnrolment studentEnrolment) {
        if (!isEnrolmentExist(studentEnrolment)) {
            this.enrolments.add(studentEnrolment);
            return true;
        }
        return false;
    }

    /**
     * Delete an enrolment from the list.
     * @param studentEnrolment  An enrolment to be deleted.
     * @return  A boolean represents if the operation is successful or not.
     */
    @Override
    public Boolean delete(StudentEnrolment studentEnrolment) {
        int enrolmentIndex = findEnrolmentIndex(studentEnrolment);
        if (enrolmentIndex != -1) {
            this.enrolments.remove(enrolmentIndex);
            return true;
        }
        return false;
    }

    /**
     * Returns a string array of student enrolments information.
     * @param studentId The index of the student.
     * @param courseId  The index of the course.
     * @param semester  The semester name.
     * @return  A string array of enrolment information.
     */
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

    /**
     * Returns all the enrolments information.
     * @return  A string array of student enrolments.
     */
    @Override
    public StudentEnrolment[] getAll() {
        return this.enrolments.toArray(new StudentEnrolment[this.enrolments.size()]);
    }

    /**
     * Updates an enrolment.
     * @param oldStudentEnrolment   The enrolment to be updated.
     * @param updatedStudentEnrolment   The updated enrolment.
     * @return  A boolean represents if the operation is successful or not.
     */
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

    /**
     * Returns an enrolment based on index.
     * @param index The index of the enrolment.
     * @return  An StudentEnrolment instance.
     */
    @Override
    public StudentEnrolment get(int index) {
        return this.enrolments.get(index);
    }

    /**
     * Check if an enrolment is already exists.
     * @param studentEnrolment  The enrolment to be checked.
     * @return  A boolean represents if the enrolment exists.
     */
    private Boolean isEnrolmentExist(StudentEnrolment studentEnrolment) {
        return findEnrolmentIndex(studentEnrolment) != -1;
    }

    /**
     * Returns index of an enrolment.
     * @param studentEnrolment  The enrolment information.
     * @return  The index of the enrolment.
     */
    private int findEnrolmentIndex(StudentEnrolment studentEnrolment) {
        for (int i = 0; i < this.enrolments.size(); i++) {
            if (this.enrolments.get(i).equals(studentEnrolment)) {
                return i;
            }
        }
        return -1;
    }
}
