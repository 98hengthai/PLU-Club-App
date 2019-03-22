package prototypes;
import java.sql.*;


public class DatabaseCommands {
    private Connection conn = null;

    DatabaseCommands(){
        connect();
    }
    /**
     * Connect to a sample database
     */
    public void connect(){
        try{
            //Create database connection    //This url is where the file ClubDatabase is located. Mine is in my downloads folder.
//            String url = "jdbc:sqlite:/D:/SQLServer/ClubDatabase";
            String url = "jdbc:sqlite:/C:/Users/hengt/Documents/GitHub/pluclubapp/Databases/ClubDatabase";
            conn = DriverManager.getConnection(url);

            System.out.println("dbConnections to SQLite has been established");
        } catch (SQLException e) {
            System.out.println("Error in connection to SQLite, " + e.getMessage());
        }
    }

    /**
     * Queries the database for a ResultSet of all users from a given club
     * @param clubName The entities.Club that users belong to
     * @return Returns Names of Users from a given entities.Club
     */
    public ResultSet getUsersFromClub(String clubName){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT Name " +
                    "FROM Users as u JOIN ClubUsers as cu " +
                    "ON u.Email = cu.UserEmail " +
                    "WHERE cu.ClubName = \"" + clubName + "\" " +
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
    public ResultSet getAllClubs(){
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
    public ResultSet getAllClubsNameSearch(String name){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Clubs " +
                    "WHERE Name LIKE \""  + name + "\" " +
                    "ORDER BY Name ";
            rs = stmt.executeQuery(sql);
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
    public ResultSet getAllClubsIntSearch(String interest){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Clubs as c join ClubInterests as ci " +
                    "ON c.Name = ci.ClubName " +
                    "AND ci.InterestName = \"" + interest + "\" " +
                    "ORDER BY Name ";
            rs = stmt.executeQuery(sql);
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
    public ResultSet getMyClubs(String userName){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT Clubs.Name, " +
                         "Clubs.Location, " +
                         "Clubs.ClubEmail, " +
                         "Clubs.Description " +
                    "FROM Clubs " +
                         "JOIN ( " +
                            "SELECT ClubName " +
                            "FROM ClubUsers " +
                            "WHERE UserEmail = ( " +
                                "SELECT Email " +
                                "FROM Users " +
                                "WHERE Name = \"" + userName + "\" ) " +
                         ") " +
                    "AS myClubs ON Clubs.Name = myClubs.ClubName ";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetMyClubs error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Given a username, return a list of clubs that match that
     *  prototypes.User's interests and order it by the number of interests matched
     * @param userName
     * @return A ResultSet of club names that match prototypes.User Interests
     */
    public ResultSet getRecomClubs(String userName){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT ci.ClubName " +
                    "FROM ClubInterests AS ci " +
                    "       JOIN " +
                    "       ( " +
                    "           SELECT * " +
                    "             FROM UserInterests AS ui " +
                    "                  INNER JOIN " +
                    "                  Users AS u ON ui.UserEmail = u.Email " +
                    "            WHERE u.Name = \"" + userName + "\" " +
                    "       ) " +
                    "       AS ui ON ci.InterestName = ui.InterestName " +
                    " ORDER BY ci.ClubName ";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetAllClubsNameSearch error " + e.getMessage() + sql);
        }
        return rs;
    }



    //***********************************************************
    //THIS IS TRANSITIONING TO THE INTERESTS SECTION
    //***********************************************************
    /**
     * Returns the list of all interests registered so far
     * @return
     */
    public ResultSet getInterests(){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Interests " +
                    "ORDER BY Name";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetMyClubs error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Returns a ResultSet of all of my interests
     * @param name
     * @return
     */
    public ResultSet getMyInterests(String name){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT UserInterests.InterestName " +
                    "  FROM UserInterests " +
                    " WHERE UserInterests.UserEmail = ( " +
                    "                                     SELECT Users.Email " +
                    "                                       FROM Users " +
                    "                                      WHERE Users.Name = \"" + name + "\" " +
                    "                                  ) " +
                    " ORDER BY UserInterests.InterestName;";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetMyClubs error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Given a clubName, return a ResultSet of the interests belonging to that club
     * @param clubName
     * @return
     */
    public ResultSet getClubInterests(String clubName){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT ClubInterests.InterestName " +
                    "FROM ClubInterests " +
                    "WHERE ClubInterests.ClubName = \"" + clubName + "\" " +
                    "ORDER BY ClubInterests.InterestName";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetMyClubs error " + e.getMessage() + sql);
        }
        return rs;
    }


    //****************************************************************************
    //                  Code for Getting Users                                   *
    //****************************************************************************

    /**
     * Return a ResultSet of all Users
     * @return
     */
    public ResultSet getUsers(){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "FROM Users " +
                    "ORDER BY Users.Name asc";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetMyClubs error " + e.getMessage() + sql);
        }
        return rs;
    }

    /**
     * Given a name, return a ResultSet of clubs the user belongs to
     * @param name
     * @return
     */
    public ResultSet getClubsOfUser(String name){
        ResultSet rs = null;
        String sql = null;

        try{
            Statement stmt = conn.createStatement();
            sql = "SELECT * " +
                    "  FROM Clubs AS c JOIN ClubUsers AS cu ON c.Name = cu.ClubName " +
                    " WHERE cu.UserEmail = ( " +
                    "                          SELECT Users.Email " +
                    "                            FROM Users " +
                    "                           WHERE Users.Name = \"" + name + "\" " +
                    "                      );";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("GetMyClubs error " + e.getMessage() + sql);
        }
        return rs;
    }

    public ResultSet getAllUserEmails() {
        return getUsers();
    }
}
