package pt.isel.ls.commands.classManagement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Classs;
import pt.isel.ls.domain.Student;
import pt.isel.ls.domain.Teacher;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.exceptions.ParameterException;
import pt.isel.ls.view.classManagement.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rafae_000 on 21/03/2017.
 */
public class GetClassesFromCourseSemesterNumber implements ICommand {


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;

        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try {
            Classs classs = null;
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

            String getClassesFromCouseSemesterNum = "select top (?) identifier, acronym, yearSemester, semester " +
            "from (select *, row_number() over (order by identifier) as row from Class where acronym = ? and yearSemester = ? " +
                    "and semester = ? and identifier = ? ) Class where row>?";

            String getStudents = "select * from Student where studentID in( select studentID from Attends " +
                    "where acronym = ? AND identifier = ? AND yearSemester = ? AND semester = ? )";

            String getTeachers = "select * from Teacher where teacherID in( select teacherID from Teach " +
                    "where acronym = ? AND identifier = ? AND yearSemester = ? AND semester = ? )";

            PreparedStatement statement = con.prepareStatement(getClassesFromCouseSemesterNum);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setString(2, request.get("acr"));
            statement.setString(3, semester[1]);
            statement.setString(4, semester[0]);
            statement.setString(5, request.get("num"));
            statement.setInt(6, Integer.parseInt(request.getParameterOrDefault("skip","0")));

            ResultSet resultSet = statement.executeQuery();

            PreparedStatement statementToGetStudents = con.prepareStatement(getStudents);
            PreparedStatement statementToGetTeachers = con.prepareStatement(getTeachers);


            if (resultSet.next()) {
                classs = new Classs(resultSet.getString("identifier"),
                        resultSet.getString("acronym"),
                        resultSet.getString("yearSemester"),
                        resultSet.getString("semester"));
                statementToGetStudents.setString(1, classs.getAcronym());
                statementToGetStudents.setString(2, classs.getIdentifier());
                statementToGetStudents.setString(3, classs.getYearSemester());
                statementToGetStudents.setString(4, classs.getSemester());
                ResultSet resultSetToGetStudents = statementToGetStudents.executeQuery();
                while(resultSetToGetStudents.next()){
                    Student student = new Student(resultSetToGetStudents.getInt("studentID"),
                            resultSetToGetStudents.getString("nameStudent"),
                            resultSetToGetStudents.getString("emailStudent"),
                            resultSetToGetStudents.getString("acronymProgramme"));
                    classs.getStudents().add(student);
                }
                statementToGetTeachers.setString(1, classs.getAcronym());
                statementToGetTeachers.setString(2, classs.getIdentifier());
                statementToGetTeachers.setString(3, classs.getYearSemester());
                statementToGetTeachers.setString(4, classs.getSemester());
                ResultSet resultSetToGetTeachers = statementToGetTeachers.executeQuery();
                while(resultSetToGetTeachers.next()){
                    Teacher teacher = new Teacher(resultSetToGetTeachers.getInt("teacherID"),
                            resultSetToGetTeachers.getString("nameTeacher"),
                            resultSetToGetTeachers.getString("emailTeacher"));
                    classs.getTeachers().add(teacher);
                }
            }

            response.getLocationViews().views.put("text/html", new HTMLClassView(classs));
            response.getLocationViews().views.put("text/plain", new ClassView(classs));
            response.getLocationViews().views.put("application/json", new JSONClassView(classs));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));


        } catch (SQLException e) {
            logger.info(e.getMessage());
            throw new NoDataException("There is no Classes with the given Acronym in this Semester Number!");
        } finally {
            if (con != null)
                con.close();
        }
    }
}
