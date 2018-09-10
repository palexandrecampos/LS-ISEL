package pt.isel.ls.CommandTests;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Rafael on 31/03/2017.
 */
public class ProgrammeManagementTests extends InitDatabaseTests{

    @BeforeClass
    public static void init() throws SQLException {
        clearDatabase();
        insertDatabase();
    }

    /* **************** Phase 1 **************** */

    @Test
    public void getProgrammeTest() throws Exception {
    /*
     * "GET /programmes" - list all the programmes.
     */
        PathTest.runRequest(new String[]{"GET", "/programmes", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});
    }

    @Test
    public void getProgrammeWithAcronymTest() throws Exception {
    /*
     * "GET /programmes/{pid}" - shows the details of programme with pid acronym.
     */
        PathTest.runRequest(new String[]{"GET", "/programmes/LEIC", PathTest.ACCEPT_ARG+PathTest.PLAIN_ARG});

    }

    @Test
    public void postProgrammeTest() throws Exception {
    /*
     * "POST /programmes" - creates a new programme, given the following parameters
     * pid - programme acronym (e.g. "LEIC").
     * name - programme name.
     * length - number of semesters.
     */
        PathTest.runRequest(new String[]{"POST", "/programmes", "pid=LEQ&name=Licenciatura+Engenharia+Quimica&length=6"});

    }

    /* **************** Phase 2 **************** */

    @Test
    public void getJSONProgrammeTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/programmes", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLProgrammeTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/programmes", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }


    @Test
    public void getJSONProgrammeWithAcronymTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/programmes/LEIC", PathTest.ACCEPT_ARG+PathTest.JSON_ARG});
    }

    @Test
    public void getHTMLProgrammeWithAcronymTest() throws Exception {

        PathTest.runRequest(new String[]{"GET", "/programmes/LEIC", PathTest.ACCEPT_ARG+PathTest.HTML_ARG});
    }

}
