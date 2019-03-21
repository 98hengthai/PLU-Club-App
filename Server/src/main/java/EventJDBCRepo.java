import com.google.gson.Gson;
import java.sql.*;

public class EventJDBCRepo implements IEventRepo{
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
                        "FROM Event ");
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
                e.setRepeat(rs.getString("Repeat"));
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
                            "WHERE Event.Event_Name = ?");
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
                e.setRepeat(rs.getString("Repeat"));
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
                            "WHERE Event.idNumber = ?");
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
                e.setRepeat(rs.getString("Repeat"));
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
                            "WHERE Event.ClubName = ?");
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
                e.setRepeat(rs.getString("Repeat"));
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
    public boolean createEvent(String idNum, String evName, String loc, String stTime, String endTime, String rep, String cName) {
        return false;
    }

    @Override
    public String editEvent(String idNum, String evName, String loc, String stTime, String endTime, String rep, String cName) {
        return null;
    }

    @Override
    public boolean deleteEvent(String idNum) {
        return false;
    }

    @Override
    public boolean deleteEventGivenName(String evName) {
        return false;
    }

    @Override
    public boolean eventExist(String idNum) {
        return false;
    }

    @Override
    public boolean eventExistGivenName(String evName) {
        return false;
    }
}
