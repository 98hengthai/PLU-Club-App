import spark.Request;
import spark.Response;
import spark.Route;

public class Controller {
    private UserJDBCRepo userJDBCRepo;
    private ClubJDBCRepo clubRepo;
    private final String databaseURL = "jdbc:mysql://localhost:2000/clubs_499_2019";
    //"jdbc:sqlite:/D:/SQLServer/ClubDatabase"

    public Controller(){
        userJDBCRepo = new UserJDBCRepo(databaseURL);
        clubRepo = new ClubJDBCRepo(databaseURL);
    }

    public Route getAllUserEmails(){
        String json = userJDBCRepo.getAllUserEmails();
        return new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            response.type("application/json");
            return json;
        }
    };
    }

    //////////////////////////////////////////////////////////////
    //          Club Commands                                   //
    //////////////////////////////////////////////////////////////
    public String getAllClubs(Request request, Response resp){
        return clubRepo.getClubs();
    }

    public String getClub(Request request, Response resp){
        System.out.println(request.params(":name"));
        String temp = clubRepo.getClub(request.params(":name"));
        System.out.println(temp);
        return temp;
    }

    public String createClub(Request request, Response resp){
        System.out.println(request.queryParams("name"));
        return "";
    }

    public String updateClub(Request request, Response resp){
        return "";
    }

    public String deleteClub(Request request, Response resp){
        return "";
    }


}
