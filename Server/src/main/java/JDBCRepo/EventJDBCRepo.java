package JDBCRepo;

import Interfaces.IEventRepo;
import com.google.gson.Gson;
import entities.Event;
import dbConnections.DatabaseConnection;

import java.sql.*;

public class EventJDBCRepo implements IEventRepo {
    private Connection conn;
    private DatabaseConnection dbConn;

    //Constructor + Create connection instnace
    public EventJDBCRepo(String connURL){ dbConn = new DatabaseConnection(connURL); }

    @Override
    public String getEvents() {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                        "FROM Event " +
                        "ORDER BY Event.Start_Time");
            rs = stmt.executeQuery();

            //Add all events to a string and return
            sb.append("[");
            while(rs.next()){
                Event e = new Event();
                e.setIdNumber(rs.getString("idNumber"));
                e.setName(rs.getString("Event_Name"));
                e.setLocation(rs.getString("Location"));
                e.setStart_time((rs.getString("Start_Time")));
                e.setEnd_time(rs.getString("End_Time"));
                e.setRepeat(rs.getString("Repeating"));
                e.setClubName(rs.getString("ClubName"));
                sb.append( gson.toJson(e) ).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getEvents " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getEventGivenName(String eventName) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM Event " +
                            "WHERE Event.Event_Name = ? " +
                            "ORDER BY Event.Start_Time ");
            stmt.setString(1, eventName);
            rs = stmt.executeQuery();

            //Add all events to a string and return
            sb.append("[");
            while(rs.next()){
                Event e = new Event();
                e.setIdNumber(rs.getString("idNumber"));
                e.setName(rs.getString("Event_Name"));
                e.setLocation(rs.getString("Location"));
                e.setStart_time((rs.getString("Start_Time")));
                e.setEnd_time(rs.getString("End_Time"));
                e.setRepeat(rs.getString("Repeating"));
                e.setClubName(rs.getString("ClubName"));
                sb.append( gson.toJson(e) ).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getEvents " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getEventGivenID(String idNum) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM Event " +
                            "WHERE Event.idNumber = ? " +
                            "ORDER BY Event.Start_Time");
            stmt.setString(1, idNum);
            rs = stmt.executeQuery();

            //Add all events to a string and return
            sb.append("[");
            while(rs.next()){
                Event e = new Event();
                e.setIdNumber(rs.getString("idNumber"));
                e.setName(rs.getString("Event_Name"));
                e.setLocation(rs.getString("Location"));
                e.setStart_time((rs.getString("Start_Time")));
                e.setEnd_time(rs.getString("End_Time"));
                e.setRepeat(rs.getString("Repeating"));
                e.setClubName(rs.getString("ClubName"));
                sb.append( gson.toJson(e) ).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getEvents " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getEventGivenClub(String clubName) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM Event " +
                            "WHERE Event.ClubName = ? " +
                            "ORDER BY Event.Start_Time");
            stmt.setString(1, clubName);
            rs = stmt.executeQuery();

            //Add all events to a string and return
            sb.append("[");
            while(rs.next()){
                Event e = new Event();
                e.setIdNumber(rs.getString("idNumber"));
                e.setName(rs.getString("Event_Name"));
                e.setLocation(rs.getString("Location"));
                e.setStart_time((rs.getString("Start_Time")));
                e.setEnd_time(rs.getString("End_Time"));
                e.setRepeat(rs.getString("Repeating"));
                e.setClubName(rs.getString("ClubName"));
                sb.append( gson.toJson(e) ).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getEvents " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    /**
     * Returns true if event was created, else returns false
     *  idNum auto populates so there is no need to pass that integer
     */
    public boolean createEvent(String idNum, String evName, String loc, String stTime, String endTime, String rep, String cName) {
        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Event VALUES( ?, ? , ? , ? , ? , ? , ? )");
            stmt.setInt(1, 0);
            stmt.setString(2, evName);
            stmt.setString(3, loc);
            stmt.setString(4, stTime);
            stmt.setString(5, endTime);
            stmt.setString(6, rep);
            stmt.setString(7, cName);
            stmt.execute();
            conn.close();
            return eventExistGivenName(cName);
        } catch (SQLException e){
            System.out.println("Error in createEvent " + e.getMessage());
        }
        return false;
    }

    @Override
    public String editEvent(String idNum, String evName, String loc, String stTime, String endTime, String rep, String cName) {
        String result = "";
        try {
            if (!eventExist(idNum)){
                return "Event did not exist";
            }

            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Event "+
                        "SET Event_Name = ?, " +
                            "Location = ?, " +
                            "Start_Time = ?, " +
                            "End_Time = ?, " +
                            "Repeating = ?, " +
                            "ClubName = ? " +
                        "WHERE Event.idNumber = ?");

            stmt.setString(1, evName);
            stmt.setString(2, loc);
            stmt.setString(3, stTime);
            stmt.setString(4, endTime);
            stmt.setString(5, rep);
            stmt.setString(6, cName);
            stmt.setString(7, idNum);

            stmt.executeUpdate();

            //Return the newly updated file
            result = getEventGivenID(idNum);

            dbConn.close();
        } catch(Exception e){
            System.out.println("Error in EventJDBCRepo: Edit:  " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteEvent(String idNum) {
        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM Event " +
                            "WHERE Event.idNumber = ?");
            stmt.setString(1, idNum);
            stmt.executeQuery();
            dbConn.close();

            return !eventExist(idNum);
        } catch (SQLException e){
            System.out.println("error in deleteEvent : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteEventGivenName(String evName) {
        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM Event " +
                            "WHERE Event.Event_Name= ?");
            stmt.setString(1, evName);
            stmt.executeQuery();
            dbConn.close();
            //TODO: Create eventExist given EventName
            return true;
        } catch (SQLException e){
            System.out.println("error in deleteEvent : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eventExist(String idNum) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();

            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM Event " +
                    "WHERE Event.idNumber = ?");
            stmt.setString(1, idNum);
            rs = stmt.executeQuery();
            if(rs.next()){
                result = true;
            }

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in EventJDBCRepo.EventExist: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean eventExistGivenName(String evName) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();

            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM Event " +
                    "WHERE Event.ClubName = ?");
            stmt.setString(1, evName);
            rs = stmt.executeQuery();
            if(rs.next()){
                result = true;
            }

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in EventJDBCRepo.EventExist: " + e.getMessage());
        }
        return result;
    }
}
