import java.sql.*;

public class DatabaseConnection {
    private Connection conn = null;
    private String databaseURL;

    DatabaseConnection(String dbURL){
        databaseURL = dbURL;
    }

    public Connection connect(){
        try{
            //Create database Connection
            //Change this to the connection for your database
            //url = "jdbc:sqlite:/D:/SQLServer/ClubDatabase";
            conn = DriverManager.getConnection(databaseURL, "clubuser_499", "changeme");
            System.out.println("Connection established to " + databaseURL);
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
            try{ conn.close(); } catch (SQLException e){
                System.out.println("Could not close: " + e.getMessage());
            }
        }
    }
}
