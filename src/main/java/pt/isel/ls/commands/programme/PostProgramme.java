package pt.isel.ls.commands.programme;

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
public class PostProgramme implements ICommand {


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;

        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            con = dataSource.getConnection();

            String newProgramme = "insert into Programme values (?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(newProgramme);
            statement.setString(1,request.get("pid"));
            statement.setString(2,request.get("name"));
            statement.setInt(3, Integer.parseInt(request.get("length")));
            int res = statement.executeUpdate();


            if(res >0)
                System.out.println("Insert Successful");

            con.commit();


            response.getLocationViews().setLocation(ResolveURL.getOrPostSpecificProgramme(request.get("pid")));



        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new InsertException("Bad Request! Cannot Insert Programme. " +
                    "The acronym name already exist in database or number semesters must be a number!");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
