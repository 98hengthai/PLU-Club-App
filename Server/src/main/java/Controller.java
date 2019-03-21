import spark.Request;
import spark.Response;
import spark.Route;

public class Controller {
    private UserJDBCRepo userJDBCRepo;
    private ClubJDBCRepo clubRepo;
    private EventJDBCRepo eventRepo;
    private String databaseURL = References.OFF_CAMPUS_DB_URL;

    public Controller(){
        userJDBCRepo = new UserJDBCRepo(databaseURL);
        clubRepo = new ClubJDBCRepo(databaseURL);
        eventRepo = new EventJDBCRepo(databaseURL);
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
        return clubRepo.getClub(request.params(":name"));
    }

    public String createClub(Request request, Response resp){
        //TODO: Ask how to best implement this return statement
        System.out.println(request.queryParams("name"));
        return "Club Created";
    }

    public String updateClub(Request request, Response resp){
        //TODO: How to retrieve data from the request in the API link
        return "Not yet implemented";
    }

    public String deleteClub(Request request, Response resp){
        //TODO: How to best return data
        clubRepo.deleteClub(request.params(":name"));
        return "Delete finished";
    }

    //////////////////////////////////////////////////////////////
    //                  Event Commands                          //
    //////////////////////////////////////////////////////////////
    public String getAllEvents(Request request, Response resp){
        return eventRepo.getEvents();
    }

    public String getEventGivenName(Request request, Response resp){
        return eventRepo.getEventGivenName(request.params(":name"));
    }

    public String getEventGivenID(Request request, Response resp){
        return eventRepo.getEventGivenID(request.params(":idNum"));
    }

    public String getEventGivenClub(Request request, Response resp){
        return eventRepo.getEventGivenClub(request.params(":cName"));
    }

    public String createEvent(Request request, Response resp){
        //TODO
        return "Not yet Implemented";
    }

    public String updateEvent(Request request, Response resp){
        //TODO
        return "Not yet implemented";
    }

    public String deleteEventGivenID(Request request, Response resp){
        //TODO
        return "Not yet implemented";
    }

    public String deleteEventGivenName(Request request, Response resp){
        //TODO
        return "Not yet implemented";
    }

}
