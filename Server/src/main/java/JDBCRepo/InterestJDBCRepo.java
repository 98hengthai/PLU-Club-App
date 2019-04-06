package JDBCRepo;
import Interfaces.IInterestsRepo;
import entities.Interests;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbConnections.DatabaseConnection;

public class InterestJDBCRepo implements IInterestsRepo{
    private Connection conn;
    private DatabaseConnection dbConn;

    public InterestJDBCRepo(String connURL){ dbConn = new DatabaseConnection(connURL); }

    @Override
    public String getInterests() {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM Interests");
            rs = stmt.executeQuery();

            //Append all interests
            sb.append("[");
            while(rs.next()){
                Interests inter = new Interests();
                inter.setName(rs.getString("Name"));
                sb.append(gson.toJson(inter)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getInterests: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public boolean createInterest(String interest) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "INSERT INTO Interests VALUES(?)");
            stmt.setString(1, interest);
            stmt.execute();
            conn.close();
            return interestExist(interest);
        } catch (SQLException e){
            System.out.println("Error in createInterest " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteInterest(String interest) {
        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM Interests " +
                            "WHERE Interests.Name = ?");
            stmt.setString(1, interest);
            stmt.executeQuery();
            dbConn.close();

            return !interestExist(interest);
        } catch (SQLException e){
            System.out.println("error in deleteEvent : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean interestExist(String interest) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();

            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM Interests " +
                    "WHERE Interests.Name = ?");
            stmt.setString(1, interest);
            rs = stmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in Interest.InterestExist: " + e.getMessage());
        }
        return result;
    }
}
