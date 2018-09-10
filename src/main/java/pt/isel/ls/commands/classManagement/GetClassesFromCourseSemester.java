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
import pt.isel.ls.exceptions.ParameterException;
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
public class GetClassesFromCourseSemester implements ICommand {

    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Connection con = null;
        Logger logger = LoggerFactory.getLogger(FirstHttpServer.class);
        try{
            List<Classs> classes = new LinkedList<>();
            String sem = request.get("sem");
            String[] semester = new String[2];
            if(sem.length() == 5 && (sem.endsWith("i") || sem.endsWith("v"))){
                semester[0] = sem.endsWith("i") ? "winter" : "summer";
                semester[1] = sem.substring(0, sem.length()-1);
            }
            else {
                throw new ParameterException("Invalid Semester Parameter. The correct format is YEARi or YEARv");
            }
            con = dataSource.getConnection();

            String getClassesFromCourseSemester = "select top (?) identifier, acronym, yearSemester, semester " +
            "from (select *, row_number() over (order by identifier) as row from Class where acronym = ? and yearSemester = ? " +
                    "and semester = ? ) Class where row>?";

            PreparedStatement statement = con.prepareStatement(getClassesFromCourseSemester);
            statement.setInt(1, Integer.parseInt(request.getParameterOrDefault("top","10")));
            statement.setString(2, request.get("acr"));
            statement.setString(3, semester[1]);
            statement.setString(4, semester[0]);
            statement.setInt(5, Integer.parseInt(request.getParameterOrDefault("skip","0")));

            ResultSet resultSet = statement.executeQuery();


            while(resultSet.next()) {
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
            throw new NoDataException("There is no Classes with the given Acronym in this Semester!");
        }finally {
            if(con!=null)
                con.close();
        }
    }
}
