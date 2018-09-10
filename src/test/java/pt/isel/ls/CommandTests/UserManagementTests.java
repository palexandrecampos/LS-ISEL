package pt.isel.ls.CommandTests;

import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.Path;

import java.sql.SQLException;

/**
 * Created by Rafael on 31/03/2017.
 */
public class UserManagementTests extends InitDatabaseTests{

    @BeforeClass
    public static void init() throws SQLException {
        clearDatabase();
        insertDatabase();
    }

    /* **************** Phase 1 **************** */
    @Test
    public void getUsersTest() throws Exception {
    /*
     * "GET /users" - shows all users.
     */
        PathTest.runRequest(new String[]{"GET", "/users", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getStudentsTest() throws Exception {
    /*
     * "GET /students" - shows all students.
     */
        PathTest.runRequest(new String[]{"GET", "/students", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getStudentWithNumTest() throws Exception {
    /*
     * "GET /students/{num}" - shows the student with the number num.
     */
        PathTest.runRequest(new String[]{"GET", "/students/123", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});

    }

    @Test
    public void getTeachersTest() throws Exception {
    /*
     * "GET /teachers" - shows all teachers.
     */
        PathTest.runRequest(new String[]{"GET", "/teachers", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getTeacherWithNumTest() throws Exception {
    /*
     * "GET /teachers/{num}" - shows the teacher with number num (e.g. GET /teachers/1207).
     */
        PathTest.runRequest(new String[]{"GET", "/teachers/123456", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});

    }

    @Test
    public void postStudentsTest() throws Exception {
    /*
     * "POST /students" - creates a new student, given the following parameters
     * num - student number.
     * name- student name.
     * email - student email.
     * pid - programme acronym.
     */
        PathTest.runRequest(new String[]{"POST", "/students", "num=12345&name=Manuel+Jose&email=mj@isel.pt&pid=LEIC"});
    }


    /* **************** Phase 2 **************** */

    @Test
    public void getJSONUsersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/users", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLUsersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/users", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNUsersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/users", PathTest.TOP_ARG+3});
    }

    @Test
    public void getSkipNUsersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/users", PathTest.SKIP_ARG+6});
    }

    @Test
    public void getJSONStudentsTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/students", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLStudentsTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/students", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNStudentsTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/students", PathTest.TOP_ARG+3});
    }

    @Test
    public void getSkipNStudentsTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/students", PathTest.SKIP_ARG+6});
    }

    @Test
    public void getJSONStudentWithNumTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/students/123", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLStudentWithNumTest() throws Exception {
    /*
     * "GET /students/{num}" - shows the student with the number num.
     */
        PathTest.runRequest(new String[]{"GET", "/students/1234", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getJSONTeachersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLTeachersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNTeachersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers", PathTest.TOP_ARG+3});
    }

    @Test
    public void getSkipNTeachersTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers", PathTest.SKIP_ARG+6});
    }

    @Test
    public void getJSONTeacherWithNumTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers/12345", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});

    }

    @Test
    public void getHTMLTeacherWithNumTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers/12345", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});

    }

    @Test
    public void getJSONAllStudentsOfAClass() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1/students", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLAllStudentsOfAClass() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1/students", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNStudentsOfAClass() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617v/D1/students", PathTest.TOP_ARG+3});
    }

    @Test
    public void getSkipNStudentsOfAClass() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617v/D1/students", PathTest.SKIP_ARG+6});
    }

    @Test
    public void getAllStudentsOfAClassSortedByNumber() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}/students/sorted" - returns a list with all students of the class, ordered by increasing student number.
     */
        PathTest.runRequest(new String[]{"GET", "/courses/E/classes/1617v/D1/students/sorted", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getJSONAllStudentsOfAClassSortedByNumber() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}/students/sorted" - returns a list with all students of the class, ordered by increasing student number.
     */
        PathTest.runRequest(new String[]{"GET", "/courses/E/classes/1617v/D1/students/sorted", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

  @Test
    public void getHTMLAllStudentsOfAClassSortedByNumber() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}/students/sorted" - returns a list with all students of the class, ordered by increasing student number.
     */
       PathTest.runRequest(new String[]{"GET", "/courses/E/classes/1617v/D1/students/sorted", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNStudentsOfAClassSortedByNumber() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}/students/sorted" - returns a list with all students of the class, ordered by increasing student number.
     */
    PathTest.runRequest(new String[]{"GET", "/courses/E/classes/1617v/D1/students/sorted", PathTest.TOP_ARG+3});
    }

    @Test
    public void getSkipNStudentsOfAClassSortedByNumber() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}/students/sorted" - returns a list with all students of the class, ordered by increasing student number.
     */
        PathTest.runRequest(new String[]{"GET", "/courses/E/classes/1617v/D1/students/sorted", PathTest.SKIP_ARG+6});
    }

    @Test
    public void deleteStudentFromClass() throws Exception {
    /*
     * "DELETE /courses/{acr}/classes/{sem}/{num}/students/{numStu}" - removes a student from a class.
     */
        PathTest.runRequest(new String[]{"DELETE", "/courses/E/classes/1617v/D1/students/123"});
    }

    @Test
    public void updateTeacher() throws Exception {
    /*
     * "PUT /teachers/{num}" - updates an existent teacher, given all required parameters.
     */
        PathTest.runRequest(new String[]{"PUT", "/teachers/123", "num=123&name=Hugo+Rodrigues&email=hr@gmail.com"});
    }

    @Test
    public void updateStudent() throws Exception {
    /*
     * "PUT /students/{num}" - updates an existent student, given all required parameters.
     */
        PathTest.runRequest(new String[]{"PUT", "/students/123", "num=1&name=Francisco+Antunes&email=fa@gmail.com&pid=LEIC"});
    }
}
