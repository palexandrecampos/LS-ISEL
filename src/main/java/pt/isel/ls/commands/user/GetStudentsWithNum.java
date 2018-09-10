package pt.isel.ls.commands.user;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Classs;
import pt.isel.ls.domain.Student;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.student.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by palex on 25/03/2017.
 */
public class GetStudentsWithNum implements ICommand {

    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            Student student = null;
            con = dataSource.getConnection();

            String getStudentWithNum = "select * from Student where studentID = ?";

            String getClassesForAStudent = "select * from Attends where studentID = ? ";

            PreparedStatement statement = con.prepareStatement(getStudentWithNum);
            statement.setInt(1, Integer.parseInt(request.get("num")));
            ResultSet resultSet = statement.executeQuery();

            PreparedStatement statement1 = con.prepareStatement(getClassesForAStudent);

            while(resultSet.next()){
                student = new Student(resultSet.getInt("studentID"),
                        resultSet.getString("nameStudent"),
                        resultSet.getString("emailStudent"),
                        resultSet.getString("acronymProgramme"));
                statement1.setInt(1, student.getStudentID());
                ResultSet resultSet1 = statement1.executeQuery();
                while(resultSet1.next()){
                    Classs classs = new Classs(resultSet1.getString("identifier"),
                            resultSet1.getString("acronym"),
                            resultSet1.getString("yearSemester"),
                            resultSet1.getString("semester"));
                    student.getClasses().add(classs);
                }
            }

            response.getLocationViews().views.put("text/html", new HTMLStudentView(student));
            response.getLocationViews().views.put("text/plain", new StudentView(student));
            response.getLocationViews().views.put("application/json", new JSONStudentView(student));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));


        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new NoDataException("There is no Students with the given ID");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
