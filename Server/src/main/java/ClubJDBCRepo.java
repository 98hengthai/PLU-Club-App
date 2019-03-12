import com.google.gson.Gson;

import java.sql.*;

public class ClubJDBCRepo implements IClubRepo{
    private Connection conn;
    private DatabaseConnection dbConn;

    //Constructor + Establish connection
    public ClubJDBCRepo(String connURL) {
        dbConn = new DatabaseConnection(connURL);
    }

    @Override
    public boolean createClub(String clubName, String location, String clubEmail, String clubDesc) {
        try{
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO Clubs VALUES ( ? , ? , ? , ? )");
            stmt.setString(1, clubName);
            stmt.setString(2, location);
            stmt.setString(3, clubEmail);
            stmt.setString(4, clubDesc);
            stmt.execute();
            conn.close();
            return clubExist(clubName);
        } catch (SQLException e) {
            System.out.println("Error in Create:Club " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getClubs() {
        StringBuilder sb = new StringBuilder();
        ResultSet resultSet;
        Gson gson = new Gson();

        try {
            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                        "FROM Clubs");
            resultSet = stmt.executeQuery();

            //Add clubsFromDB to list clubs to return
            sb.append("[");
            while(resultSet.next()){
                Club c = new Club();
                c.setName(resultSet.getString("name"));
                c.setLocation(resultSet.getString("location"));
                c.setClubEmail(resultSet.getString("clubEmail"));
                c.setDescription(resultSet.getString("description"));
                sb.append( gson.toJson(c) ).append(",\n ");
            }
            sb.deleteCharAt(sb.length() - 3);
            sb.append("]");

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in getClubs " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String getClub(String name) {
        StringBuilder sb = new StringBuilder();
        ResultSet rs;

        try{
            conn = dbConn.connect();

            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * " +
                    "FROM Clubs " +
                    "WHERE Clubs.Name = ? ");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            sb.append("[");
            while(rs.next()){
                Club c = new Club();
                c.setName(rs.getString("name"));
                c.setLocation(rs.getString("location"));
                c.setClubEmail(rs.getString("clubEmail"));
                c.setDescription(rs.getString("description"));
                sb.append( new Gson().toJson(c));
            }
            sb.append("]");

            conn.close();
        } catch (SQLException e){
            System.out.println("Error in getClub " + e.getMessage());
        }
        return sb.toString();
    }

    //Updates the club based on key cName and uses getClub to return updated data
    @Override
    public String editClub(String cName, String cLoc, String cEmail, String cDesc){
        String result = "";
        try {
            if (!clubExist(cName)){
                return "Club Name did not exist";
            }

            conn = dbConn.connect();
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Clubs "+
                        "SET Location = ? ," +
                        "ClubEmail = ? ," +
                        "Description = ? " +
                        "WHERE Clubs.Name = ?");

            stmt.setString(1, cLoc);
            stmt.setString(2, cEmail);
            stmt.setString(3, cDesc);
            stmt.setString(4, cName);

            stmt.executeUpdate();

            //Return the newly updated file
            result = getClub(cName);

            conn.close();
        } catch(Exception e){
            System.out.println("Error in CLUBJDBCRepo: editClub: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteClub(String name) {
        return true;
    }

    @Override
    public boolean clubExist(String name) {
        boolean result = false;
        ResultSet rs;

        try{
            conn = dbConn.connect();

            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * " +
                    "FROM Clubs " +
                    "WHERE Clubs.Name = ?");

            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            conn.close();
        } catch(SQLException e){
            System.out.println("Error in ClubJDBCRepo:ClubExist, " + e.getMessage());
        }
        return result;
    }
}
