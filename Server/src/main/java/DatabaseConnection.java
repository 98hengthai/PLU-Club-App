import java.sql.*;

public class DatabaseConnection {
    private Connection conn = null;

    DatabaseConnection(String dbURL){
        connect(dbURL);
    }

    public void connect(String dbURL){
        try{
            //Create database Connection
            //Change this to the connection for your database
            String url = dbURL;
            //url = "jdbc:sqlite:/D:/SQLServer/ClubDatabase";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established to " + url);
        } catch (SQLException e){
            System.out.println("Error in connection: " + e.getMessage());
        }
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
