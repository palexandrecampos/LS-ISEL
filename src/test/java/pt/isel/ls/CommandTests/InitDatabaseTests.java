package pt.isel.ls.CommandTests;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitDatabaseTests {

    public static void clearDatabase() throws SQLException {
        Connection con;
        SQLServerDataSource dataSourceTest = DB.initTest();
        con = dataSourceTest.getConnection();

        String deleteTables = "delete from Obrigation\n" +
                "delete from Attends\n" +
                "delete from Teach\n" +
                "delete from CurricularSemester\n" +
                "delete from Class\n" +
                "delete from Student\n" +
                "delete from AcademicSemester\n" +
                "delete from Programme\n" +
                "delete from Course\n" +
                "delete from Teacher\n";

        PreparedStatement statement = con.prepareStatement(deleteTables);
        statement.executeUpdate();

        con.commit();
        con.close();
    }

    public static void insertDatabase() throws SQLException {
        Connection con;
        SQLServerDataSource dataSourceTest = DB.initTest();
        con = dataSourceTest.getConnection();

        String insertInTables = "insert into Programme values ('LEIC', 'Licenciatura Engenharia Informatica Computadores', 6)\n" +
                "insert into Teacher values (123, 'Jose Antunes', 'ja@isel.ipt')\n" +
                "insert into Teacher values (1234, 'Manuel Fernandes', 'mf@isel.ipt')\n" +
                "insert into Teacher values (12345, 'Joao Filipe', 'jf@isel.ipt')\n" +
                "insert into Teacher values (123456, 'Rui Santos', 'rs@isel.ipt')\n" +
                "insert into Course values ('Electronica', 'E', 1234)\n" +
                "insert into Course values ('Laboratorio Software', 'LS', 123)\n" +
                "insert into Course values ('Programacao', 'PG', 123)\n" +
                "insert into CurricularSemester values (1)\n" +
                "insert into CurricularSemester values (2)\n" +
                "insert into CurricularSemester values (3)\n" +
                "insert into CurricularSemester values (4)\n" +
                "insert into CurricularSemester values (5)\n" +
                "insert into CurricularSemester values (6)\n" +
                "insert into Student values (123, 'Ze Manel', 'zm@isel.pt', 'LEIC')\n" +
                "insert into Student values (1234, 'Bruno Gomes', 'bg@isel.pt', 'LEIC')\n" +
                "insert into AcademicSemester values ('1617', 'winter')\n" +
                "insert into AcademicSemester values ('1617', 'summer')\n" +
                "insert into Class values ('D1', 'E', '1617', 'winter')\n" +
                "insert into Class values ('D1', 'PG', '1617', 'winter')\n" +
                "insert into Class values ('D1', 'E', '1617', 'summer')\n" +
                "insert into Class values ('D1', 'PG', '1617', 'summer')\n" +
                "insert into Teach values ('D1', 'E', '1617', 'winter', 123)\n" +
                "insert into Teach values ('D1', 'PG', '1617', 'winter', 123)\n" +
                "insert into Teach values ('D1', 'PG', '1617', 'winter', 1234)\n" +
                "insert into Obrigation values ('LEIC', 'PG', 1, 1)\n" +
                "insert into Obrigation values ('LEIC', 'E', 1, 1)\n" +
                "insert into Attends values ('D1', 'PG', '1617', 'winter', 123)\n" +
                "insert into Attends values ('D1', 'PG', '1617', 'winter', 1234)\n" +
                "insert into Attends values ('D1', 'E', '1617', 'winter', 123)\n" +
                "insert into Attends values ('D1', 'E', '1617', 'winter', 1234)\n" +
                "insert into Attends values ('D1', 'PG', '1617', 'summer', 123)"+
                "insert into Attends values ('D1', 'PG', '1617', 'summer', 1234)\n" +
                "insert into Attends values ('D1', 'E', '1617', 'summer', 123)\n" +
                "insert into Attends values ('D1', 'E', '1617', 'summer', 1234)\n";

        PreparedStatement statement = con.prepareStatement(insertInTables);
        statement.executeUpdate();

        con.commit();
        con.close();

    }



}
