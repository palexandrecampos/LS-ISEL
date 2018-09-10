package pt.isel.ls.CommandTests;

import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.Path;

import java.sql.SQLException;

/**
 * Created by Rafael on 31/03/2017.
 */
public class ClassManagementTests extends InitDatabaseTests {

    @BeforeClass
    public static void init() throws SQLException {
        clearDatabase();
        insertDatabase();
    }

    /* **************** Phase 1 **************** */
    @Test
    public void getClassesFromCourseTest() throws Exception {
    /*
     * "GET /courses/{acr}/classes" - shows all classes for a course.
     */
        PathTest.runRequest(new String[]{"GET", "/courses/ls/classes", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getClassesFromCourseSemesterTest() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}" - shows all classes of the acr course on the sem semester.
     */
       PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617v", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getClassesFromCourseSemesterNumberTest() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}" - shows the classes of the acr course on the sem semester and with num number.
     */
        PathTest.runRequest(new String[]{"GET", "/courses/E/classes/1617i/D1", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void postClassTest() throws Exception {
    /*
     * "POST /courses/{acr}/classes" - creates a new class on the course with acr acronym, given the following parameters
     * sem - semester identifier (e.g. 1415v).
     * num - class number - (e.g. D1).
     */
        PathTest.runRequest(new String[]{"POST", "/courses/LS/classes", "sem=1617i&num=D1"});
    }

    /* **************** Phase 2 **************** */

    @Test
    public void getJSONClassesFromCourseTest() throws Exception {
        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLClassesFromCourseTest() throws Exception {
        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNClassesFromCourseTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes", PathTest.TOP_ARG+3});
    }

    @Test
    public void getJSONClassesFromCourseSemesterTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }


    @Test
    public void getHTMLClassesFromCourseSemesterTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNClassesFromCourseSemesterTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i", PathTest.TOP_ARG+3});
    }


    @Test
    public void getJSONClassesFromCourseSemesterNumberTest() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}" - shows the classes of the acr course on the sem semester and with num number.
     */
      PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

  @Test
    public void getHTMLClassesFromCourseSemesterNumberTest() throws Exception {
    /*
     * "GET /courses/{acr}/classes/{sem}/{num}" - shows the classes of the acr course on the sem semester and with num number.
     */
       PathTest.runRequest(new String[]{"GET", "/courses/PG/classes/1617i/D1", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }
}