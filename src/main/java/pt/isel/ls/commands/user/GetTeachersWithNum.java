package pt.isel.ls.commands.user;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Classs;
import pt.isel.ls.domain.Course;
import pt.isel.ls.domain.Teacher;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.teacher.HTMLTeacherView;
import pt.isel.ls.view.teacher.JSONTeacherView;
import pt.isel.ls.view.teacher.TeacherView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by palex on 25/03/2017.
 */
public class GetTeachersWithNum implements ICommand {

    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try {
            Teacher teacher = null;
            con = dataSource.getConnection();

            String getTeacherWithNum = "select * from Teacher where teacherID = ? ";

            String getClassesForATeacher = "select * from Teach where teacherID = ? ";

            String getCoursesForATeacher = "select * from Course where teacherID = ? ";

            PreparedStatement statement = con.prepareStatement(getTeacherWithNum);
            statement.setInt(1, Integer.parseInt(request.get("num")));

            ResultSet resultSet = statement.executeQuery();
            PreparedStatement statement1 = con.prepareStatement(getClassesForATeacher);


            if (resultSet.next()) {
                teacher = new Teacher(resultSet.getInt("teacherID"),
                        resultSet.getString("nameTeacher"),
                        resultSet.getString("emailTeacher"));
                statement1.setInt(1, teacher.getTeacherID());

                ResultSet resultSet1 = statement1.executeQuery();

                while (resultSet1.next()) {
                    Classs classs = new Classs(resultSet1.getString("identifier"),
                            resultSet1.getString("acronym"),
                            resultSet1.getString("yearSemester"),
                            resultSet1.getString("semester"));
                    teacher.getClasses().add(classs);
                }
                PreparedStatement statement2 = con.prepareStatement(getCoursesForATeacher);
                statement2.setInt(1, Integer.parseInt(request.get("num")));
                ResultSet resultSet2 = statement2.executeQuery();
                while(resultSet2.next()) {
                    Course course = new Course(resultSet2.getString("nameCourse"),
                            resultSet2.getString("acronym"),
                            teacher.getTeacherID());
                    teacher.getCourses().add(course);
                }
            }

            response.getLocationViews().views.put("text/html", new HTMLTeacherView(teacher));
            response.getLocationViews().views.put("text/plain", new TeacherView(teacher));
            response.getLocationViews().views.put("application/json", new JSONTeacherView(teacher));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept", "text/html")));


        } catch (SQLException e) {
            logger.info(e.getMessage());
            throw new NoDataException("There is no Students with the given ID");
        } finally {
            if (con != null)
                con.close();
        }
    }
}
