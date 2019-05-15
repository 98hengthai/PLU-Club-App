package JDBCRepo;

import Interfaces.IClubUsersRepo;
import com.google.gson.Gson;
import entities.ClubUsers;
import dbConnections.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("getAllClubsGivenUser: userEmail - " + userEmail);
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();
        List<ClubUsers> clubUsersList = new ArrayList<>();


        try{
            conn = dbConn.connect();
            while(conn.isClosed()){
                conn = dbConn.connect();
            }
            PreparedStatement stmt = conn.prepareStatement("" +
                    "SELECT * " +
                    "FROM ClubUsers " +
                    "WHERE ClubUsers.UserEmail = ? ");
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();

            //Append all data
//            sb.append("[");

            while(rs.next()){
                System.out.println(rs.getString("ClubName"));
                ClubUsers cUser = new ClubUsers();
                cUser.setClubName(rs.getString("ClubName"));
                cUser.setUserEmail(rs.getString("UserEmail"));
                cUser.setRole(rs.getString("Role"));
                clubUsersList.add(cUser);
//                sb.append(gson.toJson(cUser)).append(",\n");
            }
//            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
//            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in ClubUsersJDBCRepo: GetClubsGivenUser: " + e.getMessage());

        }

        if(clubUsersList.isEmpty())
            return "";

        return gson.toJson(clubUsersList);
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
        List<ClubUsers> clubUsersList = new ArrayList<>();

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
                clubUsersList.add(cUser);
                sb.append(gson.toJson(cUser)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
            return gson.toJson(clubUsersList);
        } catch (SQLException e){
            System.out.println("Error in ClubUsersJDBCRepo: GetAllClubUsers: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public boolean createClubUser(String clubName, String userEmail, String role) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO ClubUsers VALUES( ? , ? , ?)");
            stmt.setString(1, clubName);
            stmt.setString(2, userEmail);
            stmt.setString(3, role);
            stmt.execute();
            conn.close();
            return clubUserExist(clubName, userEmail);
        } catch (SQLException e ) {
            System.out.println("Error in createClubUsers " + e.getMessage());
            System.out.println(clubName + "-" + userEmail+"-" + role);
        }
        return false;
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
        boolean result = false;
        ResultSet rs;

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
            if(rs.next()){
                result = true;
            }
            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in ClubUsers.exist: " + e.getMessage());
        }
        return result;
    }
}
