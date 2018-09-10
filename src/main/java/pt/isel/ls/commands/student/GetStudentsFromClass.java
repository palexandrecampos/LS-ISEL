package pt.isel.ls.commands.student;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Student;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.exceptions.ParameterException;
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
 * Created by Rafae_000 on 04/04/2017.
 */
public class GetStudentsFromClass implements ICommand {
    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            List<Student> students = new LinkedList<>();
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

            String getStudentsForAClass = "select top (?) studentID, nameStudent, emailStudent, acronymProgramme " +
                    "from (select *, row_number() over (order by studentID) as row from Student where studentID in( " +
                    "select studentID from Attends where identifier = ? AND acronym  = ? " +
                    "AND yearSemester = ? AND semester = ?)) Student where row >?";

            PreparedStatement statement = con.prepareStatement(getStudentsForAClass);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setString(2, request.get("num"));
            statement.setString(3, request.get("acr"));
            statement.setString(4, semester[1]);
            statement.setString(5, semester[0]);
            statement.setInt(6, Integer.parseInt(request.getParameterOrDefault("skip","0")));

            ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
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
            throw new NoDataException("There are no Students to the given Class");
        }finally {
            if(con!=null)
                con.close();
        }

    }
}
