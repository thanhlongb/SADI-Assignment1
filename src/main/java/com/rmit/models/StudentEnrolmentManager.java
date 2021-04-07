package com.rmit.models;

/**
 * Interface for the StudentEnrolment list.
 */
public interface StudentEnrolmentManager {
    // required methods: add, update, delete. getOne, getAll.

    /**
     * Add an enrolment.
     * @param studentEnrolment  The enrolment to be added.
     * @return  A boolean represents if the operation is successful or not.
     */
    Boolean add(StudentEnrolment studentEnrolment);

    /**
     * Delete an enrolment.
     * @param studentEnrolment  The enrolment to be deleted.
     * @return  A boolean represents if the operation is successful or not.
     */
    Boolean delete(StudentEnrolment studentEnrolment);

    /**
     * Returns one enrolment information.
     * @param studentId Id of the student.
     * @param courseId  Id of the course.
     * @param semester  Name of the semester.
     * @return  A StudentEnrolment object.
     */
    StudentEnrolment getOne(String studentId, String courseId, String semester);

    /**
     * Returns all of the enrolments.
     * @return  Array of StudentEnrolment.
     */
    StudentEnrolment[] getAll();

    /**
     * Updates an student enrolment.
     * @param oldStudentEnrolment   The enrolment to be updated.
     * @param updatedStudentEnrolment   The new enrolment information.
     * @return  A boolean represents if the operation is successful or not.
     */
    Boolean update(StudentEnrolment oldStudentEnrolment,
                   StudentEnrolment updatedStudentEnrolment);

    /**
     * Returns an enrolment based on index.
     * @param index The index of the enrolment.
     * @return  An StudentEnrolment object.
     */
    StudentEnrolment get(int index);

    /**
     * Returns enrolments information.
     * @param studentId Id of the student.
     * @param courseId  Id of the course.
     * @param semester  Name of the semester.
     * @return  Array list of StudentEnrolment object.
     */
    StudentEnrolment[] find(String studentId, String courseId, String semester);
}
