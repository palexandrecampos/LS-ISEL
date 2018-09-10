package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.http.HTTPServer;

public class Listen implements ICommand {

    private static int LISTEN_PORT = 8080;


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {

        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        logger.info("Starting main...");
        int port = Integer.parseInt(request.getParameterOrDefault("port", String.valueOf(LISTEN_PORT)));
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(HTTPServer.class, "/");
        server.start();
        logger.info("Server started");
        server.join();

        logger.info("main ends.");

    }
}
