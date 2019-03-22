package dbConnections;

import common.References;
import java.sql.*;

public class DatabaseConnection {
    private Connection conn = null;
    private String databaseURL;

    public DatabaseConnection(String dbURL){
        databaseURL = dbURL;
    }

    public Connection connect(){
        try{
            //Create database dbConnections
            conn = DriverManager.getConnection(databaseURL, References.USERNAME, References.PASSWORD);
            System.out.println("dbConnections established to " + databaseURL);
        } catch (SQLException e){
            System.out.println("Error in connection: " + e.getMessage());
        }
        return conn;
    }

    public Connection getConnection(){
        return conn;
    }

    public void close(){
        if(conn != null){
            try{
                System.out.println("Closing");
                conn.close();
            } catch (SQLException e){
                System.out.println("Could not close: " + e.getMessage());
            }
        }
    }
}
