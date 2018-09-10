package pt.isel.ls.commands.course;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Course;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.course.CoursesView;
import pt.isel.ls.view.course.HTMLCoursesView;
import pt.isel.ls.view.course.JSONCoursesView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 25/03/2017.
 */
public class GetCourse implements ICommand {

    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try {
            List<Course> courses = new LinkedList<>();
            con = dataSource.getConnection();

            String getCourse = "select top (?) nameCourse, acronym, teacherID " +
            "from (select *, row_number() over (order by acronym) as row from Course) Course where row>?";

            PreparedStatement statement = con.prepareStatement(getCourse);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setInt(2, Integer.parseInt(request.getParameterOrDefault("skip","0")));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("nameCourse"),
                        resultSet.getString("acronym"),
                        resultSet.getInt("teacherID"));
                courses.add(course);
            }

            response.getLocationViews().views.put("text/html", new HTMLCoursesView(courses));
            response.getLocationViews().views.put("text/plain", new CoursesView(courses));
            response.getLocationViews().views.put("application/json", new JSONCoursesView(courses));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));

        } catch (SQLException e) {
            logger.info(e.getMessage());
            throw new NoDataException("There is no Courses");
        } finally {
            if (con != null)
                con.close();
        }
    }
}
