import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClubJDBCRepo implements IClubRepo{
    private Connection conn;
    private DatabaseConnection dbConn;

    //Constructor + Establish connection
    public ClubJDBCRepo(String connURL) {
        dbConn = new DatabaseConnection(connURL);
    }

    @Override
    public boolean createClub(String clubName, String location, String clubEmail, String clubDesc) {
        String command = null;
        boolean result;
        try{
            conn = dbConn.connect();
            Statement stmt = conn.createStatement();
            command = "INSERT INTO Clubs "  +
                    " VALUES ( " +
                    clubName + ", " +
                    location + ", " +
                    clubEmail + ", " +
                    clubDesc + " ) ";
            result = stmt.execute(command);
            conn.close();
            return result;
        } catch (SQLException e) {
            System.out.println("Error in Create:Club " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getClubs() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String command = null;
        ResultSet resultSet = null;
        Gson gson = new Gson();

        try {
            conn = dbConn.connect();
            Statement stmt = conn.createStatement();
            command = "SELECT * " +
                        "FROM Clubs";
            resultSet = stmt.executeQuery(command);

            //Add clubsFromDB to list clubs to return
            while(resultSet.next()){
                Club c = new Club();
                c.setName(resultSet.getString("name"));
                c.setLocation(resultSet.getString("location"));
                c.setClubEmail(resultSet.getString("clubEmail"));
                c.setDescription(resultSet.getString("description"));
                sb.append( gson.toJson(c) ).append(",\n ");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in getClubs " + e.getMessage());
        }
        sb.deleteCharAt(sb.length() - 3);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String getClub(String name) {
        StringBuilder sb = new StringBuilder();
        String command = null;
        ResultSet rs = null;

        try{
            conn = dbConn.connect();
            Statement stmt = conn.createStatement();
            command = "SELECT * " +
                        "FROM Clubs " +
                        "WHERE Clubs.Name = " + name + " ";
            rs = stmt.executeQuery(command);
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

    @Override
    public String editClub(String cName, String cLoc, String cEmail, String cDesc) throws Exception{
        //TODO: Update the club in the database
//        try {
//            if (edit.getName() == null)
//                throw new Exception("Name cannot be blank");
//
//            Club toEdit = clubMap.get(edit.getName());
//
//            if(toEdit == null)
//                throw new Exception("Club not found");
//
//            if(edit.getLocation() != null) {
//                toEdit.setLocation(edit.getLocation());
//            }
//            if(edit.getClubEmail() != null) {
//                toEdit.setClubEmail(edit.getClubEmail());
//            }
//
//            if(edit.getDescription() != null) {
//                toEdit.setDescription(edit.getDescription());
//            }
//
//            return toEdit;
//
//        } catch(Exception e){
//            throw new Exception(e.getMessage());
//        }
        return "";
    }

    @Override
    public boolean deleteClub(String name) {
        return true;
    }

    @Override
    public boolean clubExist(String name) { return false; }
}
