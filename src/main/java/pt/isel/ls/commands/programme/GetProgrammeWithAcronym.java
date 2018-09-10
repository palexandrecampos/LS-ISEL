package pt.isel.ls.commands.programme;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Course;
import pt.isel.ls.domain.Programme;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.programme.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by palex on 25/03/2017.
 */
public class GetProgrammeWithAcronym implements ICommand {

    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con=null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            Programme programme = null;
            con = dataSource.getConnection();

            String getProgrammeWithAcronym = "select * from Programme where acronymProgramme = ?";
            String getCoursesFromProgramme = "select * from Course where acronym in (" +
                    "select acronym from Obrigation where acronymProgramme = ? )";

            PreparedStatement statement = con.prepareStatement(getProgrammeWithAcronym);
            statement.setString(1,request.get("pid"));
            ResultSet resultSet = statement.executeQuery();

            PreparedStatement statement1 = con.prepareStatement(getCoursesFromProgramme);

            while(resultSet.next()){
                programme = new Programme(resultSet.getString("acronymProgramme"),
                        resultSet.getString("name"),
                        resultSet.getInt("numberSemester"));
                statement1.setString(1, programme.getAcronym());
                ResultSet resultSet1 = statement1.executeQuery();
                while(resultSet1.next()){
                    Course course = new Course(resultSet1.getString("nameCourse"),
                            resultSet1.getString("acronym"),
                            resultSet1.getInt("teacherID"));
                    programme.getCourses().add(course);
                }
            }


            response.getLocationViews().views.put("text/html", new HTMLProgrammeView(programme));
            response.getLocationViews().views.put("text/plain", new ProgrammeView(programme));
            response.getLocationViews().views.put("application/json", new JSONProgrammeView(programme));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));




        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new NoDataException("There is no Programmes to this given Acronym");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
