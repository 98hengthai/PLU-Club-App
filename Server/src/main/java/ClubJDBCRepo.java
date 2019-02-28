import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.*;

public class ClubJDBCRepo implements IClubRepo{
    private Connection conn;

    //Constructor + Establish connection
    public ClubJDBCRepo() { conn = new DatabaseConnection().getConnection(); }

    @Override
    public boolean createClub(Club club) {
        String command = null;
        try{
            Statement stmt = conn.createStatement();
            command = "INSERT INTO Clubs "  +
                    " VALUES ( " +
                    club.getName() + ", " +
                    club.getLocation() + ", " +
                    club.getClubEmail() + ", " +
                    club.getDescription() + " ) ";
            stmt.executeQuery(command);
            return true;
        } catch (SQLException e) {
            System.out.println("Error in Create:Club " + e.getMessage());
            return false;
        }
    }

    @Override
    public Collection<Club> getClubs() {
        List<Club> clubs = new ArrayList<Club>();
        String command = null;
        ResultSet resultSet = null;

        try {
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
                clubs.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error in getClubs " + e.getMessage());
        }

        return clubs;
    }

    @Override
    public Club getClub(String name) { return new Club(); }

    @Override
    public Club editClub(Club edit) throws Exception{
        //TODO: Update the club in the database as well as the HashMap
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
        return edit;
    }

    @Override
    public boolean deleteClub(String name) {
        return true;
    }

    @Override
    public boolean clubExist(String name) { return false; }
}
