package pt.isel.ls.commands.classManagement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.Request;
import pt.isel.ls.apps.http.FirstHttpServer;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.ICommand;
import pt.isel.ls.domain.Classs;
import pt.isel.ls.exceptions.NoDataException;
import pt.isel.ls.view.classManagement.ClassesView;
import pt.isel.ls.view.classManagement.HTMLClassesView;
import pt.isel.ls.view.classManagement.JSONClassesView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rafae_000 on 21/03/2017.
 */
public class GetClassesFromCourse implements ICommand {


    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            List<Classs> classes = new LinkedList<>();
            con = dataSource.getConnection();


            String getClassesFromCourse = "select top (?) identifier, acronym, yearSemester, semester " +
            "from (select *, row_number() over (order by identifier) as row from Class where acronym = ?) " +
                    "Class where row>?";

            PreparedStatement statement = con.prepareStatement(getClassesFromCourse);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setString(2, request.get("acr"));
            statement.setInt(3, Integer.parseInt(request.getParameterOrDefault("skip","0")));

            ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Classs classs = new Classs(resultSet.getString("identifier"),
                            resultSet.getString("acronym"),
                            resultSet.getString("yearSemester"),
                            resultSet.getString("semester"));
                classes.add(classs);
                }

            response.getLocationViews().views.put("text/html", new HTMLClassesView(classes));
            response.getLocationViews().views.put("text/plain", new ClassesView(classes));
            response.getLocationViews().views.put("application/json", new JSONClassesView(classes));

            response.write(response.getLocationViews().getView(request.getHeaderOrDefault("accept","text/html")));



        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new NoDataException("There is no Classes with the given Acronym!");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
