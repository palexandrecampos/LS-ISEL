package pt.isel.ls.commands.course;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Classs;
import pt.isel.ls.domain.Course;
import pt.isel.ls.domain.Programme;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.course.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by palex on 25/03/2017.
 */
public class GetCourseWithAcronym implements ICommand {

    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try {
            //Course course = null;
            Course course = null;
            //List<Course> courses = new LinkedList<>();
            con = dataSource.getConnection();

            String getCourseWithAcronym = "select * from Course where acronym = ? ";

            PreparedStatement statement = con.prepareStatement(getCourseWithAcronym);
            statement.setString(1, request.get("acr"));
            ResultSet resultSet = statement.executeQuery();

            String getClassesOfCourse = "select * from Class where acronym = ? ";
            String getProgramme = "select * from Programme as P inner join Obrigation as O on "+
                    "P.acronymProgramme = O.acronymProgramme where acronym = ? ";


            if (resultSet.next()) {
                course = new Course(resultSet.getString("nameCourse"),
                        resultSet.getString("acronym"),
                        resultSet.getInt("teacherID"));
                PreparedStatement statement1 = con.prepareStatement(getClassesOfCourse);
                statement1.setString(1, course.getAcronym());
                ResultSet resultSet1 = statement1.executeQuery();
                while(resultSet1.next()){
                    Classs classs = new Classs(resultSet1.getString("identifier"),
                            resultSet1.getString("acronym"),
                            resultSet1.getString("yearSemester"),
                            resultSet1.getString("semester"));
                    course.getClasses().add(classs);
                }
                PreparedStatement statement2 = con.prepareStatement(getProgramme);
                statement2.setString(1, course.getAcronym());
                ResultSet resultSet2 = statement2.executeQuery();

                if(resultSet2.next()){
                    Programme programme = new Programme(resultSet2.getString("acronymProgramme"),
                            resultSet2.getString("name"),Integer.parseInt(resultSet2.getString("numberSemester")));
                    course.setProgramme(programme);
                }

            }

            response.getLocationViews().views.put("text/html", new HTMLCourseView(course));
            response.getLocationViews().views.put("text/plain", new CourseView(course));
            response.getLocationViews().views.put("application/json", new JSONCourseView(course));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));


        } catch (SQLException e) {
            logger.info(e.getMessage());
            throw new NoDataException("There is no Courses with the given Acronym");
        } finally {
            if (con != null)
                con.close();
        }
    }
}
