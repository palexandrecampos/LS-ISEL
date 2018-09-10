package pt.isel.ls.commands.teacher;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Teacher;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.exceptions.ParameterException;
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
public class GetTeachersForAClass implements ICommand {


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            List<Teacher> teachers = new LinkedList<>();
            String sem = request.get("sem");
            String[] semester = new String[2];
            if(sem.length() == 5 && (sem.endsWith("i") || sem.endsWith("v"))){
                semester[0] = sem.endsWith("i") ? "winter" : "summer";
                semester[1] = sem.substring(0, sem.length()-1);
            }
            else {
                throw new ParameterException("Invalid Semester Parameter. The correct format is YEARi or YEARv");
            }
            con = dataSource.getConnection();

            String getTeachersForAClass = "select top (?) teacherID, nameTeacher, emailTeacher " +
                    "from (select *, row_number() over (order by teacherID) as row from Teacher where teacherID in( select teacherID " +
                    "from Teach where identifier = ? AND acronym = ? AND yearSemester = ? AND semester = ?)) " +
                    "Teacher where row>?";

            PreparedStatement statement = con.prepareStatement(getTeachersForAClass);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setString(2, request.get("num"));
            statement.setString(3, request.get("acr"));
            statement.setString(4,semester[1]);
            statement.setString(5,semester[0]);
            statement.setInt(6, Integer.parseInt(request.getParameterOrDefault("skip","0")));


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
            throw new NoDataException("There is no Teachers to the given Class");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
