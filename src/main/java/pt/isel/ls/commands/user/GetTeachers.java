package pt.isel.ls.commands.user;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Teacher;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.teacher.HTMLTeachersView;
import pt.isel.ls.view.teacher.JSONTeachersView;
import pt.isel.ls.view.teacher.TeachersView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 25/03/2017.
 */
public class GetTeachers implements ICommand {

    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con=null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            List<Teacher> teachers = new LinkedList<>();
            con = dataSource.getConnection();

            String getTeachers = "select top (?) teacherID, nameTeacher, emailTeacher " +
            "from (select *, row_number() over (order by teacherID) as row from Teacher) Teacher where row>?";

            PreparedStatement statement = con.prepareStatement(getTeachers);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setInt(2, Integer.parseInt(request.getParameterOrDefault("skip","0")));
            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()){
                Teacher teacher = new Teacher(resultSet.getInt("teacherID"),
                        resultSet.getString("nameTeacher"),
                        resultSet.getString("emailTeacher"));
                teachers.add(teacher);
            }

            response.getLocationViews().views.put("text/html", new HTMLTeachersView(teachers));
            response.getLocationViews().views.put("text/plain", new TeachersView(teachers));
            response.getLocationViews().views.put("application/json", new JSONTeachersView(teachers));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));


        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new NoDataException("There is no Teacher");
        }finally {
            if(con!=null)
                con.close();
        }


    }
}
