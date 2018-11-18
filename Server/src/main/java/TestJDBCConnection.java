import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBCConnection {

    private static Connection conn = null;
    /**
     * Connect to a sample database
     */
    public static void connect(){
        try{
            //Create database connection    //This url is where the file ClubDatabase is located. Mine is in my downloads folder.
            String url = "jdbc:sqlite:/D:/SQLServer/ClubDatabase";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established");
        } catch (SQLException e) {
            System.out.println("Error in connection to SQLite, " + e.getMessage());
        } finally {
            try{
                if (conn != null) {
                    ResultSet rs = getUsersFromClub("Computer Science Club");
                    while(rs.next()){
                        System.out.println("Name: " +  rs.getString(1));
                    }
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args){
        connect();
    }

    /**
     * Queries the database for a ResultSet of all users from a given club
     * @param clubName The Club that users belong to
     * @return Returns Names of Users from a given Club
     */
    public static ResultSet getUsersFromClub(String clubName){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT Name " +
                    "FROM Users as u join ClubUsers as cu " +
                    "on u.Email = cu.UserEmail " +
                    "ORDER BY u.Email asc";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetUsersFromClubError " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Queries and returns a ResultSet of all clubs from the database
     * @return Returns all data for clubs
     */
    public static ResultSet getAllClubs(){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Clubs " +
                    "ORDER BY Name ";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetClubs Error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Queries and returns a ResultSet of all clubs from database
     *  that match or contain the name
     * @param name
     * @return
     */
    public static ResultSet getAllClubsNameSearch(String name){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Clubs " +
                    "WHERE Name LIKE "  + name + " " +
                    "ORDER BY Name ";
        } catch (SQLException e) {
            System.out.println("GetAllClubsNameSearch error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Queries and returns a ResultSet of all clubs that relate to a given interest
     * @param interest
     * @return
     */
    public static ResultSet getAllClubsIntSearch(String interest){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Clubs as c join ClubInterests as ci " +
                    "ON c.Name = ci.ClubName " +
                        "AND ci.InterestName = " + interest + " " +
                    "ORDER BY Name ";
        } catch (SQLException e) {
            System.out.println("GetAllClubsIntSearch error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Queries and returns a ResultSet of my clubs from the database given a Username
     * @param userName
     * @return
     */
    public static ResultSet getMyClubs(String userName){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Clubs " +
                    "WHERE Name = ( " +
                            "SELECT ClubName " +
                            "FROM ClubUsers " +
                            "WHERE UserEmail =  (SELECT Email FROM Users WHERE Name = " + userName + " ) )";
        } catch (SQLException e) {
            System.out.println("GetMyClubs error " + e.getMessage() + sql);
        }
        return rs;
    }

    //TODO
    public static ResultSet getMyClubsNameSearch(String name){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "";
        } catch (SQLException e) {
            System.out.println("GetMyClubsNameSearch error " + e.getMessage() + sql);
        }
        return rs;
    }

    //TODO
    public static ResultSet getMyClubsIntSearch(String interest){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "";
        } catch (SQLException e) {
            System.out.println("GetMyClubsIntSearch error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * TODO
     * Given a username, return a list of clubs that match that
     *  User's interests and order it by the number of interests matched
     * @param userName
     * @return A ResultSet of club names that match User Interests
     */
    public static ResultSet getRecomClubs(String userName){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "";
        } catch (SQLException e) {
            System.out.println("GetAllClubsNameSearch error " + e.getMessage() + sql);
        }
        return rs;
    }
}
