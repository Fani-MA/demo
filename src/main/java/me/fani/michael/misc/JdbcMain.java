package me.fani.michael.misc;

import java.sql.*;

public class JdbcMain {

    static final String DB_URL = "jdbc:mysql://localhost/demo1";
    static final String USER = "demo1";
    static final String PASS = "1111";
    static final String QUERY = "SELECT id, first, last, age FROM Employees";

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

//        Statement stmt = conn.createStatement();
//        var q = "INSERT INTO USER(USERNAME, PASSWORD, EMAIL) VALUES('robert', '1111', 'robert@gmail.com')";
//        var updated = stmt.executeUpdate(q);
//        System.out.println("Updated " + updated + " records");
//        stmt.close();
//        conn.close();


        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT username, email FROM USER");

        while (rs.next()) {
            System.out.println("login: " + rs.getString("username") + ", email: " + rs.getString("email"));
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
