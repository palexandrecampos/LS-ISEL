package pt.isel.ls.commands.programme;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.ResolveURL;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.commands.Response;
import pt.isel.ls.exceptions.InsertException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

/**
 * Created by palex on 25/03/2017.
 */
public class PostCourseToTheProgramme implements ICommand {
    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;

        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try {
            con = dataSource.getConnection();
            int res = 0;

            String getCurricularSemester = "select * from CurricularSemester where id = ?";
            PreparedStatement statement = con.prepareStatement(getCurricularSemester);
            statement.setString(1, request.get("semesters"));
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                String newCurricularSemester = "insert into CurricularSemester values(?)";

                statement = con.prepareStatement(newCurricularSemester);
                statement.setString(1, request.get("semesters"));
                statement.executeUpdate();
            }

            String getObrigation = "select * from Obrigation where acronymProgramme = ? AND acronym = ? AND mandatory = ?";
            statement = con.prepareStatement(getObrigation);
            statement.setString(1, request.get("pid"));
            statement.setString(2, request.get("acr"));
            statement.setBoolean(3, Boolean.parseBoolean(request.get("mandatory")));
            resultSet = statement.executeQuery();

            if ((request.get("mandatory").equals("true") && !resultSet.next()) || (request.get("mandatory").equals("false"))) {
                String newObrigation = "insert into Obrigation values (?, ?, ?, ?)";

                statement = con.prepareStatement(newObrigation);
                statement.setString(1, request.get("pid"));
                statement.setString(2, request.get("acr"));
                statement.setBoolean(3, Boolean.parseBoolean(request.get("mandatory")));
                statement.setInt(4, Integer.parseInt(request.get("semesters")));
                res = statement.executeUpdate();
            }

            else {
                System.out.println("Cannot Insert Obrigation");
            }

            if (res > 0)
                System.out.println("Insert Successful");

            con.commit();

            response.getLocationViews().setLocation(ResolveURL.getOrPostCourse(request.get("acr")));

        } catch (SQLException e) {
            logger.info(e.getMessage());
            throw new InsertException("Cannot Insert Obrigation");
        } finally {
            if (con != null)
                con.close();
        }
    }
}
