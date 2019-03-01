import spark.Request;
import spark.Response;
import spark.Route;

public class Controller {
    private UserJDBCRepo userJDBCRepo;
    private ClubJDBCRepo clubRepo;
    private final String databaseURL = "jdbc:sqlite:/D:/SQLServer/ClubDatabase";

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


}
