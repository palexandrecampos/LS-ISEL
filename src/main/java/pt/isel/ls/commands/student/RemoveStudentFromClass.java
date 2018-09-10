package pt.isel.ls.commands.student;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.commands.Response;
import pt.isel.ls.exceptions.InsertException;
import pt.isel.ls.exceptions.ParameterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rafae_000 on 04/04/2017.
 */
public class RemoveStudentFromClass implements ICommand {
    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;

        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
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


            String newTeachToAClass = "delete From Attends where identifier = ? AND acronym = ? " +
                    "AND yearSemester = ? AND semester = ? AND studentID = ? ";

            PreparedStatement statement = con.prepareStatement(newTeachToAClass);
            statement.setString(1,request.get("num"));
            statement.setString(2,request.get("acr"));
            statement.setString(3,semester[1]);
            statement.setString(4, semester[0]);
            statement.setString(5,request.get("numStu"));
            int res = statement.executeUpdate();


            if(res >0)
                System.out.println("Delete Successful");

            con.commit();



        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new InsertException("Cannot Delete Student From Class");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
