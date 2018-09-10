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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 25/03/2017.
 */
public class GetProgramme implements ICommand {

    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con=null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            List<Programme> programmes = new LinkedList<>();
            con = dataSource.getConnection();
            Programme programme;

            String getProgramme = "select top (?) acronymProgramme, name, numberSemester " +
            "from (select *, row_number() over (order by acronymProgramme) as row from Programme) Programme where row>?";
            String getCoursesFromProgramme = "select * from Course where acronym in (" +
                    "select acronym from Obrigation where acronymProgramme = ? )";

            PreparedStatement statement = con.prepareStatement(getProgramme);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setInt(2, Integer.parseInt(request.getParameterOrDefault("skip","0")));
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
                programmes.add(programme);
            }

            response.getLocationViews().views.put("text/html", new HTMLProgrammesView(programmes));
            response.getLocationViews().views.put("text/plain", new ProgrammesView(programmes));
            response.getLocationViews().views.put("application/json", new JSONProgrammesView(programmes));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));


        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new NoDataException("There is no Programmes");
        }finally {
            if(con!=null)
                con.close();
        }


    }
}
