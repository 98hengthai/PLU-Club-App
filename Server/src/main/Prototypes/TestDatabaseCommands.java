import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestDatabaseCommands {
    public static void main(String[] args) {
        DatabaseCommands db = new DatabaseCommands();

        //Test Commands
        Scanner scan = new Scanner(System.in);
        String input = "wumbo";
        ResultSet results = null;

        while(!input.equals("q")) {
            System.out.println("--------------------------------------");
            System.out.println("What SQL command would you like to test?");
            System.out.println("Enter a number to indicate your command.");
            System.out.println("1) GetUsersFromClub( String clubName )");
            System.out.println("2) GetAllClubs");
            System.out.println("3) GetAllClubsNameSearch( String name )");
            System.out.println("4) GetAllClubsIntSearch( String interest )");
            System.out.println("5) GetMyClubs( String UserName )");
            System.out.println("6) GetRecomClubs( String userName )");
            System.out.println("--------------------------------------");
            System.out.println("7) GetInterests");
            System.out.println("8) GetMyInterests( String name )");
            System.out.println("9) GetClubInterests( String clubName )");
            System.out.println("--------------------------------------");
            System.out.println("10) GetUsers");
            System.out.println("11) GetClubsOfUser( String name )");
            System.out.println("Enter q to quit");

            input = scan.nextLine();
            String[] command = input.split(" ", 2);
            if(!command[0].equals("q")) {
                switch (Integer.parseInt(command[0])) {
                    case 0:
                        System.out.println("Try again");
                        break;
                    case 1:
                        results = db.getUsersFromClub(command[1]);
                        break;
                    case 2:
                        results = db.getAllClubs();
                        break;
                    case 3:
                        results = db.getAllClubsNameSearch(command[1]);
                        break;
                    case 4:
                        results = db.getAllClubsIntSearch(command[1]);
                        break;
                    case 5:
                        results = db.getMyClubs(command[1]);
                        break;
                    case 6:
                        results = db.getRecomClubs(command[1]);
                        break;
                    case 7:
                        results = db.getInterests();
                        break;
                    case 8:
                        results = db.getMyInterests(command[1]);
                        break;
                    case 9:
                        results = db.getClubInterests(command[1]);
                        break;
                    case 10:
                        results = db.getUsers();
                        break;
                    case 11:
                        results = db.getClubsOfUser(command[1]);
                        break;
                }
            }

            //Print out Results
            try {
                while (results.next()) {
                    System.out.println(results.getString(1));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }
}
