package JDBCRepo;

import Interfaces.IUserEventsRepo;
import com.google.gson.Gson;
import entities.UserEvents;
import dbConnections.DatabaseConnection;
import java.sql.*;

public class UserEventsJDBCRepo implements IUserEventsRepo {
    private Connection conn;
    private DatabaseConnection dbConn;

    public UserEventsJDBCRepo(String connURL){ dbConn = new DatabaseConnection(connURL); }

    @Override
    public String getAllUserEvents() {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserEvents ");
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                UserEvents uEvent = new UserEvents();
                uEvent.setEventidNumber(rs.getString("EventIDNumber"));
                uEvent.setClubName(rs.getString("ClubName"));
                uEvent.setUserEmail(rs.getString("UserEmail"));
                uEvent.setReply(rs.getString("Reply"));
                sb.append(gson.toJson(uEvent)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in UserEventsJDBCRepo: GetAllUserEvents: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    //Doubles as GetReply
    public String getUserEventsGivenIDNum(String eventIDNum) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserEvents " +
                    "WHERE EventIDNumber = ? ");
            stmt.setString(1, eventIDNum);
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                UserEvents uEvent = new UserEvents();
                uEvent.setEventidNumber(rs.getString("EventIDNumber"));
                uEvent.setClubName(rs.getString("ClubName"));
                uEvent.setUserEmail(rs.getString("UserEmail"));
                uEvent.setReply(rs.getString("Reply"));
                sb.append(gson.toJson(uEvent)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in UserEventsJDBCRepo: getUserEventsGivenIDNum " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getUserEventsGivenUserEmail(String userEmail) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserEvents " +
                    "WHERE UserEmail = ? ");
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                UserEvents uEvent = new UserEvents();
                uEvent.setEventidNumber(rs.getString("EventIDNumber"));
                uEvent.setClubName(rs.getString("ClubName"));
                uEvent.setUserEmail(rs.getString("UserEmail"));
                uEvent.setReply(rs.getString("Reply"));
                sb.append(gson.toJson(uEvent)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in UserEventsJDBCRepo: getUserEventsGivenUserEmail " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getReplyGivenUserAndEvent(String eventIDNum, String userEmail) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserEvents " +
                    "WHERE EventIDNumber = ? AND " +
                    "UserEmail = ? ");
            stmt.setString(1, eventIDNum);
            stmt.setString(2, userEmail);
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                UserEvents uEvent = new UserEvents();
                uEvent.setEventidNumber(rs.getString("EventIDNumber"));
                uEvent.setClubName(rs.getString("ClubName"));
                uEvent.setUserEmail(rs.getString("UserEmail"));
                uEvent.setReply(rs.getString("Reply"));
                sb.append(gson.toJson(uEvent)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in UserEventsJDBCRepo: getUserEventsGivenIDNum " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public boolean createUserEvents(String eventIDNum, String clubName, String userEmail, String reply) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "INSERT INTO UserEvents VALUES( ? , ? , ? , ?)");
            stmt.setString(1, eventIDNum);
            stmt.setString(2, clubName);
            stmt.setString(3, userEmail);
            stmt.setString(4, reply);
            stmt.execute();
            conn.close();
            return userEventsExist(eventIDNum, userEmail);
        } catch (SQLException e ){
            System.out.println("Error in createUserEvents " + e.getMessage());
        }
        return false;
    }

    public String editUserEvents(String eventIDNum, String clubName, String userEmail, String reply){
        return null;
    }
    @Override
    public String deleteUserEvents(String eventIDNum, String userEmail) {
        return null;
    }

    @Override
    public boolean userEventsExist(String eventIDNum, String userEmail) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserEvents " +
                    "WHERE UserEvents.EventIDNumber = ? " +
                    "AND UserEvents.UserEmail = ?");
            stmt.setString(1, eventIDNum);
            stmt.setString(2, userEmail);
            rs = stmt.executeQuery();

            if(rs.next()){
                result = true;
            }
            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in UserEvents Exist " + e.getMessage());
        }
        return result;
    }
}
