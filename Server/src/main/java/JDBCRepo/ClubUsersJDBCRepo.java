package JDBCRepo;

import Interfaces.IClubUsersRepo;
import com.google.gson.Gson;
import entities.ClubUsers;
import dbConnections.DatabaseConnection;
import java.sql.*;

public class ClubUsersJDBCRepo implements IClubUsersRepo {
    private Connection conn;
    private DatabaseConnection dbConn;

    public ClubUsersJDBCRepo(String connURL){ dbConn = new DatabaseConnection(connURL); }

    @Override
    public String getAllClubUsers() {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubUsers ");
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                ClubUsers cUser = new ClubUsers();
                cUser.setClubName(rs.getString("ClubName"));
                cUser.setUserEmail(rs.getString("UserEmail"));
                cUser.setRole(rs.getString("Role"));
                sb.append(gson.toJson(cUser)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in ClubUsersJDBCRepo: GetAllClubUsers: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getAllClubsGivenUser(String userEmail) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubUsers " +
                    "WHERE ClubUsers.UserEmail = ? ");
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                ClubUsers cUser = new ClubUsers();
                cUser.setClubName(rs.getString("ClubName"));
                cUser.setUserEmail(rs.getString("UserEmail"));
                cUser.setRole(rs.getString("Role"));
                sb.append(gson.toJson(cUser)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in ClubUsersJDBCRepo: GetClubsGivenUser: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getAllUsersGivenClub(String clubName) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubUsers " +
                    "WHERE ClubUsers.ClubName = ? ");
            stmt.setString(1, clubName);
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                ClubUsers cUser = new ClubUsers();
                cUser.setClubName(rs.getString("ClubName"));
                cUser.setUserEmail(rs.getString("UserEmail"));
                cUser.setRole(rs.getString("Role"));
                sb.append(gson.toJson(cUser)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in ClubUsersJDBCRepo: GetUsersGivenClub: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getRoleGivenNameAndClub(String clubName, String userEmail) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubUsers " +
                    "WHERE ClubUsers.ClubName = ? AND " +
                        "ClubUsers.UserEmail = ? ");
            stmt.setString(1, clubName);
            stmt.setString(2, userEmail);
            rs = stmt.executeQuery();

            //Append all data
            sb.append("[");
            while(rs.next()){
                ClubUsers cUser = new ClubUsers();
                cUser.setClubName(rs.getString("ClubName"));
                cUser.setUserEmail(rs.getString("UserEmail"));
                cUser.setRole(rs.getString("Role"));
                sb.append(gson.toJson(cUser)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in ClubUsersJDBCRepo: GetAllClubUsers: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String createClubUser(String clubName, String userEmail, String role) {
        return null;
    }

    @Override
    public String editClubUser(String clubName, String userEmail, String role){
        return null;
    }

    @Override
    public String deleteClubUser(String clubName, String userEmail) {
        return null;
    }

    @Override
    public boolean clubUserExist(String clubName, String userEmail) {
        return false;
    }
}
