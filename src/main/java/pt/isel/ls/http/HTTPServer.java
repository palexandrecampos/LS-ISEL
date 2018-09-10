package pt.isel.ls.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.*;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.exceptions.DataBaseException;
import pt.isel.ls.exceptions.InsertException;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.exceptions.ParameterException;
import pt.isel.ls.view.ErrorViewHtml;
import pt.isel.ls.view.HomeView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class HTTPServer extends HttpServlet {

    protected static RouterPath routerPath = new RouterPath();
    Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);

    public HTTPServer() {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Request request = new Request(req.getMethod(), req.getRequestURI(), null, null);
        Response response = new Response(new OutputStreamWriter(resp.getOutputStream()));
        if (req.getRequestURI().equals("/")) {
            resp.setStatus(HttpStatusCode.Ok.valueOf());
            response.write(new HomeView());
            logger.info("Change to the HomeView");
            return;
        }
        try {
            routerPath.getPath().getCommand(request).execute(DB.getDatasrc(), request, response);
            resp.setStatus(HttpStatusCode.Ok.valueOf());
            logger.info("Request And Query Executed");
            logger.info("Status Code Changed to 200");
        } catch (SQLException e) {
            resp.setStatus(HttpStatusCode.BadRequest.valueOf());
            response.write(new ErrorViewHtml(400, "Bad Request"));
            logger.info("Status Code Changed to 400");
        } catch (Exception e) {
            resp.setStatus(HttpStatusCode.NotFound.valueOf());
            response.write(new ErrorViewHtml(404, "Not Found"));
            logger.info("Status Code Changed to 404");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String parameters = URLDecoder.decode(req.getReader().lines().collect(Collectors.joining(System.lineSeparator())), "ISO-8859-1"); // get parameters from request body (forms)
        Request request = new Request(req.getMethod(), req.getRequestURI(), null, parameters);
        Response response = new Response(new OutputStreamWriter(resp.getOutputStream()));
        try {
            routerPath.getPath().getCommand(request).execute(DB.getDatasrc(), request, response);
            resp.setStatus(HttpStatusCode.SeeOther.valueOf());
            logger.info("Status Code Changed to 303");
            logger.info("Post Executed With Sucess");
            resp.setHeader("Location", response.getLocationViews().getLocation());
            logger.info("Set Header Location To The Created Entity");
        } catch (SQLException e) {
            resp.setStatus(HttpStatusCode.BadRequest.valueOf());
            logger.info("Status Code Changed to 400");
            response.write(new ErrorViewHtml(400, e.getMessage()));
        } catch (Exception e) {
            resp.setStatus(HttpStatusCode.NotFound.valueOf());
            logger.info("Status Code Changed to 404");
            response.write(new ErrorViewHtml(404, "Not Found"));
        }
    }
}