package pt.isel.ls.commands.classManagement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.ResolveURL;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.commands.Response;
import pt.isel.ls.exceptions.InsertException;
import pt.isel.ls.exceptions.ParameterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rafae_000 on 21/03/2017.
 */
public class PostClass implements ICommand {


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try {
            String sem = request.get("sem");
            String[] semester = new String[2];
            if(sem.length() == 5){
                semester[0] = sem.endsWith("i") ? "winter" : "summer";
                semester[1] = sem.substring(0, sem.length()-1);
            }
            else {
                throw new ParameterException("Invalid Semester Parameter. The correct format is YEARi or YEARv");
            }

            con = dataSource.getConnection();

            String getAcademicSemester = "select * from AcademicSemester where yearSemester = ? AND semester = ?";
            PreparedStatement statement = con.prepareStatement(getAcademicSemester);
            statement.setString(1, semester[1]);
            statement.setString(2, semester[0]);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                String newAcademicSemester = "insert into AcademicSemester values(?,?)";

                statement = con.prepareStatement(newAcademicSemester);
                statement.setString(1, semester[1]);
                statement.setString(2, semester[0]);
                statement.executeUpdate();
            }

            String newClass = "insert into Class values (?,?,?,?)";

            statement = con.prepareStatement(newClass);
            statement.setString(1, request.get("num"));
            statement.setString(2, request.get("acr"));
            statement.setString(3, semester[1]);
            statement.setString(4, semester[0]);

            int res = statement.executeUpdate();


            if (res > 0)
                System.out.println("Insert Successful");

            con.commit();

            response.getLocationViews().setLocation(
                    ResolveURL.getOrPostSpecificClass(request.get("acr"), semester[1], semester[0], request.get("num")));

        } catch (SQLException e) {
            logger.info(e.getMessage());
            throw new InsertException("Cannot Insert Class. The acronym Course not exist in database or invalid format of Parameters!");
        } finally {
            if (con != null)
                con.close();
        }
    }
}
