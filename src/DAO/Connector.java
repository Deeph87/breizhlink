package DAO;

import services.DB;
import java.sql.Connection;

public class Connector {

    private Connection connector;
    private DB db;
    private String dbName;
    private String dbPort;
    private String dbUsername;
    private String dbPassword;

    public Connector(){
        this.dbName = "breizhlink";
        this.dbPort = "3307";
        this.dbUsername = "jhaudry";
        this.dbPassword = "jhaudry";

        this.db = new DB();

        try {
            this.connector = db.dbDriver(this.dbName, this.dbPort, this.dbUsername, this.dbPassword);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Connection getConnection()
    {
        return connector;
    }

    public void closeConnection()
    {
        try {
            db.dbDriver(this.dbName, this.dbPort, this.dbUsername, this.dbPassword).close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
