import org.json.JSONArray;
import spark.Request;
import spark.Response;
import spark.Route;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Controller {
    private DatabaseCommands dbCmd;
    private UserJDBCRepo userJDBCRepo;

    public Controller(){
        dbCmd = new DatabaseCommands();
        userJDBCRepo = new UserJDBCRepo(dbCmd);
    }

    public Route getAllUserEmails(){
       JSONArray jsonArray = userJDBCRepo.getAllUserEmails();
        return new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            response.type("application/json");
            return jsonArray;
        }
    };
    }


}
