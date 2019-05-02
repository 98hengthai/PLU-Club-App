package JDBCRepo;

import dbConnections.DatabaseConnection;
import java.sql.*;
import com.google.gson.Gson;
import entities.HomePageEvent;

public class HomepageJDBCRepo {
    private Connection conn;
    private DatabaseConnection dbConn;

    public HomepageJDBCRepo(String connURL) { dbConn = new DatabaseConnection(connURL);}

    public String getUserEventsAndTime(String userEmail){
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT Event.Event_Name, Event.Start_Time " +
                    "FROM Event " +
                    "Inner Join UserEvents on Event.idNumber=UserEvents.EventIDNumber " +
                    "WHERE UserEvents.UserEmail = ? AND " +
                        "DATE(Event.Start_Time) = CURDATE() " +
                    "ORDER BY Event.Start_Time; ");
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();

            sb.append("[");
            while(rs.next()){
                HomePageEvent hpe = new HomePageEvent();
                hpe.setEventName(rs.getString("Event_Name"));
                hpe.setEventTime(rs.getString("Start_Time"));
                sb.append(gson.toJson(hpe)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");
        } catch (SQLException e){
            System.out.println("Error in getUserEventsAndTime");
        }
        return sb.toString();
    }
}
