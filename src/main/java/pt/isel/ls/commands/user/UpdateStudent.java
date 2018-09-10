package pt.isel.ls.commands.user;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.commands.Response;
import pt.isel.ls.exceptions.InsertException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rafae_000 on 04/04/2017.
 */
public class UpdateStudent implements ICommand {
    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            con = dataSource.getConnection();

            String updateStudent = "update Student set nameStudent = ? , emailStudent = ? , acronymProgramme = ? " +
                    "where studentID = ?";

            PreparedStatement statement = con.prepareStatement(updateStudent);
            statement.setString(1, request.get("name"));
            statement.setString(2, request.get("email"));
            statement.setString(3, request.get("pid"));
            statement.setInt(4, Integer.parseInt(request.get("num")));
            int res = statement.executeUpdate();

            if(res >0)
                System.out.println("Update Successful");


            con.commit();


        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new InsertException("Cannot Update Student");
        }
    }
}
