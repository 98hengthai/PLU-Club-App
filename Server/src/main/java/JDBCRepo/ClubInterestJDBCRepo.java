package JDBCRepo;

import Interfaces.IClubInterestsRepo;
import entities.ClubInterest;
import com.google.gson.Gson;
import dbConnections.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubInterestJDBCRepo implements IClubInterestsRepo {
    private Connection conn;
    private DatabaseConnection dbConn;

    public ClubInterestJDBCRepo(String connURL){ dbConn = new DatabaseConnection(connURL); }

    @Override
    public String getAllClubInterests() {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubInterests");
            rs = stmt.executeQuery();

            //Append all interests
            sb.append("[");
            while(rs.next()){
                ClubInterest cInt = new ClubInterest();
                cInt.setInterestName(rs.getString("InterestName"));
                cInt.setClubName(rs.getString("ClubName"));
                sb.append(gson.toJson(cInt)).append(",\n ");
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
    public String getClubInterestsGivenClub(String clubName) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubInterests " +
                    "WHERE ClubInterests.ClubName = ? ");
            stmt.setString(1, clubName);
            rs = stmt.executeQuery();

            //Append all interests
            sb.append("[");
            while(rs.next()){
                ClubInterest cInt = new ClubInterest();
                cInt.setInterestName(rs.getString("InterestName"));
                cInt.setClubName(rs.getString("ClubName"));
                sb.append(gson.toJson(cInt)).append(",\n ");
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
    public String getClubInterestsGivenInterest(String interestName) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubInterests " +
                    "WHERE ClubInterests.InterestName = ? ");
            stmt.setString(1, interestName);
            rs = stmt.executeQuery();

            //Append all interests
            sb.append("[");
            while(rs.next()){
                ClubInterest cInt = new ClubInterest();
                cInt.setInterestName(rs.getString("InterestName"));
                cInt.setClubName(rs.getString("ClubName"));
                sb.append(gson.toJson(cInt)).append(",\n ");
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
    public boolean createClubInterest(String clubName, String interestName) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                "INSERT INTO ClubInterests VALUES( ? , ? )");
            stmt.setString(1, interestName);
            stmt.setString(2, clubName);
            stmt.execute();
            conn.close();
            return clubInterestExist(clubName, interestName);
        } catch (SQLException e){
            System.out.println("Error in createClubInterest " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteClubInterest(String clubName, String interestName) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "DELETE FROM ClubInterests " +
                    "WHERE ClubInterest.InterestName = ? AND " +
                        "ClubInterest.ClubName = ? ");
            stmt.setString(1, interestName);
            stmt.setString(2, clubName);
            stmt.executeQuery();
            dbConn.close();

            return !clubInterestExist(clubName, interestName);
        } catch (SQLException e){
            System.out.println("Error in deleteClubInterest: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean clubInterestExist(String clubName, String interestName) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                "SELECT * " +
                "FROM ClubInterests " +
                "WHERE ClubInterests.InterestName = ? AND " +
                    "ClubInterests.ClubName = ?");
            stmt.setString(1, interestName);
            stmt.setString(2, clubName);
            rs = stmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in ClubInterest.Exist: " + e.getMessage());
        }
        return result;
    }
}
