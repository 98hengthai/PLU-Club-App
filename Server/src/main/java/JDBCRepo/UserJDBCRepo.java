package JDBCRepo;

import Interfaces.IUsersRepo;
import common.References;
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
                            "FROM Users");
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
                            "FROM Users " +
                            "WHERE Users.Email = ?");
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
                            "FROM Users " +
                            "WHERE Users.Name = ?");
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
        String result = "";
        try{
            if(!userExist(email)){
                return References.ERROR_403_EDIT;
            }

            conn = dbConn.connect();
            //Given Email, update the User's attributes
            PreparedStatement stmt = conn.prepareStatement("" +
                    "UPDATE Users " +
                    "SET Email = ?, " +
                        "Name = ?, " +
                        "GraduationYear= ?, " +
                        "StudentBool = ? " +
                    "WHERE Users.Email = ?");

            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, gradYear);
            stmt.setString(4, studentBool);
            stmt.setString(5, email);
            stmt.executeUpdate();

            //Retrieve data is found
            result = getUserGivenEmail(email);
            dbConn.close();
        } catch (SQLException e){
            System.out.println("EditUser Failed " + e.getMessage());
        }
        return result;
    }

    @Override
    public String editUserGivenName(String email, String name, String gradYear, String studentBool) {
        String result = "";
        try{
            if(!userExist(email)){
                return References.ERROR_403_EDIT;
            }

            conn = dbConn.connect();
            //Given Email, update the User's attributes
            PreparedStatement stmt = conn.prepareStatement("" +
                    "UPDATE Users " +
                    "SET Email = ?, " +
                    "Name = ?, " +
                    "GraduationYear= ?, " +
                    "StudentBool = ? " +
                    "WHERE Users.Name = ?");

            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, gradYear);
            stmt.setString(4, studentBool);
            stmt.setString(5, name);
            stmt.executeUpdate();

            //Retrieve data is found
            result = getUserGivenEmail(email);
            dbConn.close();
        } catch (SQLException e){
            System.out.println("EditUser Failed " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteUser(String email) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "DELETE FROM Users " +
                    "WHERE Users.Email = ?");
            stmt.setString(1, email);
            stmt.executeQuery();
            dbConn.close();

            return !userExist(email);
        } catch (SQLException e){
            System.out.println("Error in deleteUser: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUserGivenName(String name) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement("" +
                    "DELETE FROM Users " +
                    "WHERE Users.Name = ?");
            stmt.setString(1, name);
            stmt.executeQuery();
            dbConn.close();

            return !userExistGivenName(name);
        } catch (SQLException e){
            System.out.println("Error in deleteUser: " + e.getMessage());
        }
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
