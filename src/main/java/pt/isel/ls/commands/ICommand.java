package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import pt.isel.ls.Request;

/**
 * Created by Rafae_000 on 27/03/2017.
 */
public interface ICommand {

    void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception;

}
