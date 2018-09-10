package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import pt.isel.ls.Request;

/**
 * Created by Rafae_000 on 04/04/2017.
 */
public class Exit implements ICommand {
    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        System.out.println("Application Close.");
        System.exit(0);
    }
}
