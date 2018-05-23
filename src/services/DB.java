package services;

import java.sql.*;

public class DB {

    public Connection dbDriver(String dbname, String port, String username, String password){
        String URL = "jdbc:mysql://localhost:" + port + "/" + dbname + "?autoReconnect=true&useSSL=false";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            try {
                Connection connexion = DriverManager.getConnection(URL, username, password);

                return connexion;

            } catch (SQLException exception){
                exception.printStackTrace();
            }
        } catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }

        return null;
    }
}