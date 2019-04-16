package JDBCRepo;

import Interfaces.IUserInterestsRepo;
import entities.UserInterest;
import com.google.gson.Gson;
import dbConnections.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInterestJDBCRepo implements IUserInterestsRepo {
    private Connection conn;
    private DatabaseConnection dbConn;

    public UserInterestJDBCRepo(String connURL){ dbConn = new DatabaseConnection(connURL); }

    @Override
    public String getAllUserInterests() {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserInterests");
            rs = stmt.executeQuery();

            //Append all interests
            sb.append("[");
            while(rs.next()){
                UserInterest uInt = new UserInterest();
                uInt.setInterestName(rs.getString("InterestName"));
                uInt.setUserEmail(rs.getString("UserEmail"));
                sb.append(gson.toJson(uInt)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getUserInterests: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getUserInterestsGivenUser(String userEmail) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserInterests " +
                    "WHERE UserInterests.UserEmail = ? ");
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();

            //Append all interests
            sb.append("[");
            while(rs.next()){
                UserInterest uInt = new UserInterest();
                uInt.setInterestName(rs.getString("InterestName"));
                uInt.setUserEmail(rs.getString("UserEmail"));
                sb.append(gson.toJson(uInt)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getUserInterestsWithUser: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getUserInterestsGivenInterest(String interestName) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserInterests " +
                    "WHERE UserInterests.InterestName = ? ");
            stmt.setString(1, interestName);
            rs = stmt.executeQuery();

            //Append all interests
            sb.append("[");
            while(rs.next()){
                UserInterest uInt = new UserInterest();
                uInt.setInterestName(rs.getString("InterestName"));
                uInt.setUserEmail(rs.getString("UserEmail"));
                sb.append(gson.toJson(uInt)).append(",\n ");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getUserInterestsWithInterestName: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public boolean createUserInterest(String userEmail, String interestName) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "INSERT INTO UserInterests VALUES( ? , ? )");
            stmt.setString(1, userEmail);
            stmt.setString(2, interestName);
            stmt.execute();
            conn.close();
            return userInterestExist(userEmail, interestName);
        } catch (SQLException e){
            System.out.println("Error in createUserInterest " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUserInterest(String userEmail, String interestName) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "DELETE FROM UserInterests " +
                    "WHERE UserInterests.InterestName = ? AND " +
                    "ClubInterest.ClubName = ? ");
            stmt.setString(1, userEmail);
            stmt.setString(2, interestName);
            stmt.executeQuery();
            dbConn.close();

            return !userInterestExist(userEmail, interestName);
        } catch (SQLException e){
            System.out.println("Error in deleteUserInterest: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean userInterestExist(String userEmail, String interestName) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM UserInterests " +
                    "WHERE UserInterests.UserEmail = ? AND " +
                    "UserInterests.InterestName = ?");
            stmt.setString(1, userEmail);
            stmt.setString(2, interestName);
            rs = stmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in UserInterest.Exist: " + e.getMessage());
        }
        return result;
    }
}
