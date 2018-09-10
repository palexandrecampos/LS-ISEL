package pt.isel.ls.application;

import pt.isel.ls.DB;
import pt.isel.ls.Request;
import pt.isel.ls.RouterPath;
import pt.isel.ls.commands.Response;

import java.util.Scanner;

/**
 * Created by palex on 28/03/2017.
 */
public class AcademicActivitiesApp {

    private RouterPath routerPath;

    public AcademicActivitiesApp(){
        routerPath = new RouterPath();
        DB.init();
    }

    public static void main(String[] args){
        try {
            AcademicActivitiesApp app = new AcademicActivitiesApp();
            if (args.length >= 2) {
                app.run(args);
            } else {
                app.run();
                System.out.println("usage: ./prog {method} {routerPath} {parameters}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private void run(String[] args) throws Exception {
        try {
            Request request = Request.create(args);
            Response response = Response.createResponse(request.getHeader("file-name"));
            routerPath.getPath().getCommand(request).execute(DB.getDatasrc(), request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   }

    private void run() {
        while (true) {
            try {
                System.out.print("Enter Request: ");
                String route = new Scanner(System.in).nextLine();
                Request request = Request.create(route.split(" "));
                Response response = Response.createResponse(request.getHeader("file-name"));
                routerPath.getPath().getCommand(request).execute(DB.getDatasrc(), request, response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
