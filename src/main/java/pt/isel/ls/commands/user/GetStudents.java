package pt.isel.ls.commands.user;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Student;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.student.HTMLStudentsView;
import pt.isel.ls.view.student.JSONStudentsView;
import pt.isel.ls.view.student.StudentsView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 25/03/2017.
 */
public class GetStudents implements ICommand {


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con=null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            List<Student> students = new LinkedList<>();
            con = dataSource.getConnection();

            String getStudents = "select top (?) studentID, nameStudent, emailStudent, acronymProgramme " +
                    "from (select *, row_number() over (order by studentID) as row from Student) " +
                    "Student where row>?";


            PreparedStatement statement = con.prepareStatement(getStudents);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setInt(2, Integer.parseInt(request.getParameterOrDefault("skip","0")));
            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()){
                Student student = new Student(resultSet.getInt("studentID"),
                        resultSet.getString("nameStudent"),
                        resultSet.getString("emailStudent"),
                        resultSet.getString("acronymProgramme"));
                students.add(student);
            }

            response.getLocationViews().views.put("text/html", new HTMLStudentsView(students));
            response.getLocationViews().views.put("text/plain", new StudentsView(students));
            response.getLocationViews().views.put("application/json", new JSONStudentsView(students));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));



        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new NoDataException("There is no Students");
        }
        finally {
            if(con!=null)
                con.close();
        }
    }
}
