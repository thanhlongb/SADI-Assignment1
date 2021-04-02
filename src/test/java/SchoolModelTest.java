import com.rmit.models.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SchoolModelTest {
    @Test
    void importStudentsMethodTest() {
        School school = new School();
        school.importStudents();
        Assertions.assertEquals(school.getStudents().length, 3);
    }

    @Test
    void importStudentsMethodWithCustomFileNameTest() {
        School school = new School();
        school.importStudents("custom.csv");
        Assertions.assertEquals(school.getStudents().length, 0);
    }

    @Test
    void importCoursesMethodTest() {
        School school = new School();
        school.importCourses();
        Assertions.assertEquals(school.getCourses().length, 3);
    }

    @Test
    void importCoursesMethodWithCustomFileNameTest() {
        School school = new School();
        school.importCourses("custom.csv");
        Assertions.assertEquals(school.getStudents().length, 0);
    }

    @Test
    void getStudentsMethodTest() {
        School school = new School();
        school.importStudents();
        String[] students = school.getStudents();
        Assertions.assertEquals(students.length, 3);
    }

    @Test
    void getOneEnrolmentsMethodTest() {
        School school = new School();
        school.importStudents();
        school.importCourses();
        try {
            school.enroll(1,1,"A");
            school.enroll(2,1,"A");
            school.enroll(3,1,"A");
        } catch (Exception ignored) { }
        String[] enrolments = school.getOneEnrolments(1, 1, "A");
        Assertions.assertEquals(enrolments.length, 1);
    }

    @Test
    void getCoursesMethodTest() {
        School school = new School();
        school.importCourses();
        String[] courses = school.getCourses();
        Assertions.assertEquals(courses.length, 3);
    }

    @Test
    void enrollMethodTest() {
        School school = new School();
        school.importStudents();
        school.importCourses();
        try {
            school.enroll(1,1,"A");
        } catch (Exception ignored) { }
        Assertions.assertEquals(school.getAllEnrolments().length, 1);
    }

    @Test
    void deleteEnrolmentMethodTest() {
        School school = new School();
        school.importStudents();
        school.importCourses();
        try {
            school.enroll(1,1,"A");
            school.enroll(1,1,"B");
            school.deleteEnrolment(1);
        } catch (Exception ignored) { }
        Assertions.assertEquals(school.getAllEnrolments().length, 1);
    }
}
