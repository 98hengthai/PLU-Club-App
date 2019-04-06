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
        return null;
    }

    @Override
    public String getClubInterestsGivenInterest(String interestName) {
        return null;
    }

    @Override
    public boolean createClubInterest(String clubName, String interestName) {
        return false;
    }

    @Override
    public boolean deleteClubInterest(String clubName, String interestName) {
        return false;
    }
}
