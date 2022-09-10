package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://192.168.1.11:3306/pp_1_1_3";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "uLBjAK^!8V";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            con.setAutoCommit(false);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
