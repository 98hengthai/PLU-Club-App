package JDBCRepo;

import Interfaces.IUsersRepo;
import dbConnections.DatabaseConnection;
import com.google.gson.Gson;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJDBCRepo implements IUsersRepo {
    private Connection conn;
    private DatabaseConnection dbConn;

    public UserJDBCRepo(String connURL ){ dbConn = new DatabaseConnection(connURL); }

    @Override
    public String getUsers() {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM User");
            rs = stmt.executeQuery();

            //Add all Users to list
            sb.append("[");
            while(rs.next()){
                User u = new User();
                u.setEmail(rs.getString("Email"));
                u.setName(rs.getString("Name"));
                u.setGradYear(rs.getString("GraduationYear"));
                u.setStudent(rs.getBoolean("StudentBool"));
                sb.append(gson.toJson(u)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getUsers: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getUserGivenEmail(String email) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM User " +
                            "WHERE User.Email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            //Add all Users to list
            sb.append("[");
            while(rs.next()){
                User u = new User();
                u.setEmail(rs.getString("Email"));
                u.setName(rs.getString("Name"));
                u.setGradYear(rs.getString("GraduationYear"));
                u.setStudent(rs.getBoolean("StudentBool"));
                sb.append(gson.toJson(u)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getUsers: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getUserGivenName(String name) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;
        Gson gson = new Gson();

        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM User " +
                            "WHERE User.Name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            //Add all Users to list
            sb.append("[");
            while(rs.next()){
                User u = new User();
                u.setEmail(rs.getString("Email"));
                u.setName(rs.getString("Name"));
                u.setGradYear(rs.getString("GraduationYear"));
                u.setStudent(rs.getBoolean("StudentBool"));
                sb.append(gson.toJson(u)).append(",\n");
            }
            if(sb.length() > 3) sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            dbConn.close();
        } catch (SQLException e){
            System.out.println("Error in getUsers: " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public boolean createUser(String email, String name, String gradYear, String studentBool) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Users VALUES( ? , ? , ? , ? )");
            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, gradYear);
            stmt.setString(4, studentBool);
            stmt.execute();
            conn.close();
            return userExist(email);
        } catch (SQLException e){
            System.out.println("Error in createUser: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String editUser(String email, String name, String gradYear, String studentBool) {
        //TODO
        return null;
    }

    @Override
    public String editUserGivenName(String email, String name, String gradYear, String studentBool) {
        //Never Used
        return null;
    }

    @Override
    public boolean deleteUser(String email) {
        //TODO
        return false;
    }

    @Override
    public boolean deleteUserGivenName(String name) {
        //TODO
        return false;
    }

    @Override
    public boolean userExist(String email) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();

            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * " +
                    "FROM Users " +
                    "WHERE Users.Email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if(rs.next())
                result = true;
        } catch (SQLException e){
            System.out.println("Error in UserJDBCRepo.userExist: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean userExistGivenName(String name) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM Users " +
                            "WHERE Users.Name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if(rs.next())
                result = true;
        } catch (SQLException e){
            System.out.println("Error in UserJDBCRepo.userExistGivenName: " + e.getMessage());
        }
        return result;
    }
}
