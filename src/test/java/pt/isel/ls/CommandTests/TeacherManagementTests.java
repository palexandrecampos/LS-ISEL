package pt.isel.ls.CommandTests;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Rafael on 31/03/2017.
 */
public class TeacherManagementTests extends InitDatabaseTests{

    @BeforeClass
    public static void init() throws SQLException {
        clearDatabase();
        insertDatabase();
    }

    /* **************** Phase 1 **************** */

    @Test
    public void getAllTeachersForAClassTest() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}/teachers" - shows all teachers for a class.
     */
        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1/teachers", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getAllClassesForTeacherWithNumTest() throws Exception {
    /*
     * "GET /teachers/{num}/classes" - shows all classes for the teacher with num number.
     */
        PathTest.runRequest(new String[]{"GET", "/teachers/123/classes", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    /* **************** Phase 2 **************** */

    @Test
    public void getHTMLAllTeachersForAClassTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1/teachers", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNAllTeachersForAClassTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1/teachers", PathTest.TOP_ARG+3});
    }

    @Test
    public void getSkipNAllTeachersForAClassTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1/teachers", PathTest.SKIP_ARG+6});
    }

    @Test
    public void getJSONAllClassesForTeacherWithNumTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers/123/classes", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLAllClassesForTeacherWithNumTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers/123/classes", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNAllClassesForTeacherWithNumTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/teachers/123/classes", PathTest.TOP_ARG+3});
    }

}
