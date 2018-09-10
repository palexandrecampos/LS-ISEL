package pt.isel.ls.CommandTests;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Rafael on 31/03/2017.
 */
public class CourseManagementTests extends InitDatabaseTests{

    @BeforeClass
    public static void init() throws SQLException {
        clearDatabase();
        insertDatabase();
    }

    /* **************** Phase 1 **************** */

    @Test
    public void getCourseTest() throws Exception {
    /*
     * "GET /courses" - shows all courses.
     */
        PathTest.runRequest(new String[]{"GET", "/courses", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});

    }

    @Test
    public void getCourseWithAcronymTest() throws Exception {
    /*
     * "GET /courses/{acr}" - shows the course with the acr acronym (e.g. GET /courses/ls).
     */
        PathTest.runRequest(new String[]{"GET", "/courses/PG", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});

    }

    @Test
    public void postCourseTest() throws Exception {
    /*
     * "POST /courses" - creates a new course, given the following parameters
     * name - course name.
     * acr - course acronym.
     * teacher - number of the coordinator teacher.
     */
        PathTest.runRequest(new String[]{"POST", "/courses", "name=Probabilidade+Estatistica&acr=PE&teacher=12345"});
    }

    /* **************** Phase 2 **************** */

    @Test
    public void getJSONCourseTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLCourseTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

    @Test
    public void getTopNCourseTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses", PathTest.TOP_ARG+3});
    }

    @Test
    public void getSkipNCourseTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses", PathTest.SKIP_ARG+6});
    }

    @Test
    public void getJSONCourseWithAcronymTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/E", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLCourseWithAcronymTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/courses/PG", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }
}
