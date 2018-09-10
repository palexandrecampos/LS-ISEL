package pt.isel.ls.application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.DB;
import pt.isel.ls.RouterPath;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.http.HTTPServer;

public class HerokuApp {

    private static final int LISTEN_PORT = 8080;

    public static void main(String[] args) throws Exception {
        DB.init();
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        logger.info("Starting main...");
        String portDef = System.getenv("PORT");
        int port = portDef != null ? Integer.valueOf(portDef) : LISTEN_PORT;
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
