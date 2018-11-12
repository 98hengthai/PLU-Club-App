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
            String url = "jdbc:sqlite:/C:/Users/JimmyTN/Downloads/ClubDatabase";
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
            System.out.println("GetUsesFromClubError " + e.getMessage() + sql);
        }
        return rs;
    }
}
