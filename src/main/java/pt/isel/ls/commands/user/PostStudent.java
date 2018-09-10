package pt.isel.ls.commands.user;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.ResolveURL;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.commands.Response;
import pt.isel.ls.exceptions.InsertException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by palex on 25/03/2017.
 */
public class PostStudent implements ICommand {


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            con = dataSource.getConnection();

            String newStudent = "insert into Student values (?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(newStudent);
            statement.setInt(1, Integer.parseInt(request.get("num")));
            statement.setString(2, request.get("name"));
            statement.setString(3, request.get("email"));
            statement.setString(4,request.get("pid"));
            int res = statement.executeUpdate();

            con.commit();

            if(res >0)
                System.out.println("Insert Successful");

            response.getLocationViews().setLocation(ResolveURL.getOrPostStudent(Integer.parseInt(request.get("num"))));


        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new InsertException("Cannot Insert Student");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
