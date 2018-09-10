package pt.isel.ls.commands.student;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.ResolveURL;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.commands.Response;
import pt.isel.ls.exceptions.InsertException;
import pt.isel.ls.exceptions.ParameterException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Stack;

/**
 * Created by Rafae_000 on 04/04/2017.
 */
public class PostStudentToAClass implements ICommand {

    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        int res=0;
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


            String newStudentToAClass = "insert into Attends values (?, ?, ? ,? ,?) ";

            Stack<String> numStuValues = request.createComplexParameterValueList("numStu");
            if(numStuValues.isEmpty())throw new Exception();

            while(!numStuValues.isEmpty()) {
                PreparedStatement statement = con.prepareStatement(newStudentToAClass);
                statement.setString(1, request.get("num"));
                statement.setString(2, request.get("acr"));
                statement.setString(3, semester[1]);
                statement.setString(4, semester[0]);
                statement.setInt(5, Integer.parseInt(numStuValues.pop()));
                res = statement.executeUpdate();


                con.commit();
            }

            if (res > 0)
                System.out.println("Insert Successful");

            response.getLocationViews().setLocation(ResolveURL.getOrPostStudent(Integer.parseInt(request.get("numStu"))));


        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new InsertException("Cannot Insert Student to a Class. Student name or acronym Course or Semester doesn't exist in database!");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
