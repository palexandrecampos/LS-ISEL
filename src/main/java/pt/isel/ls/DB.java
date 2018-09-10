
package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.*;

import java.sql.*;

public class DB {

    private static SQLServerDataSource datasrc;
    private static SQLServerDataSource datasrcTest;

    public static SQLServerDataSource init() {
        datasrc = new SQLServerDataSource();
        datasrc.setDatabaseName(System.getenv("DATABASE_NAME"));
        datasrc.setServerName(System.getenv("SERVER_NAME"));
        datasrc.setUser(System.getenv("USER_NAME"));
        datasrc.setPassword(System.getenv("USER_PASSWORD"));
        return datasrc;
    }

    public static SQLServerDataSource initTest() {
        datasrcTest = new SQLServerDataSource();
        datasrcTest.setDatabaseName(System.getenv("DATABASE_NAME_TEST"));
        datasrcTest.setServerName(System.getenv("SERVER_NAME_TEST"));
        datasrcTest.setUser(System.getenv("USER_NAME_TEST"));
        datasrcTest.setPassword(System.getenv("USER_PASSWORD_TEST"));
        return datasrcTest;
    }

    public static SQLServerDataSource getDatasrc() {
        return datasrc;
    }

    public static SQLServerDataSource getDatasrcTest() {
        return datasrcTest;
    }

}