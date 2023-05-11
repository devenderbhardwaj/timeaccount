package com.myblog.helper;

import java.sql.*;

/**
 *
 * @author Devender
 */
public class ConnectionProvider {

    static Connection con;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timeaccount", "root", "Root@12345");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();;
        }
    }
    public static Connection getConnection() {
        return con;
    }
}
