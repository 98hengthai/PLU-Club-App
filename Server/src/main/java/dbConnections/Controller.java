package dbConnections;

import JDBCRepo.ClubJDBCRepo;
import JDBCRepo.EventJDBCRepo;
import JDBCRepo.UserJDBCRepo;
import common.References;
import spark.Request;
import spark.Response;
import spark.Route;

public class Controller {
    private UserJDBCRepo userRepo;
    private ClubJDBCRepo clubRepo;
    private EventJDBCRepo eventRepo;
    private String databaseURL = References.ON_CAMPUS_DB_URL;

    public Controller(){
        userRepo = new UserJDBCRepo(databaseURL);
        clubRepo = new ClubJDBCRepo(databaseURL);
        eventRepo = new EventJDBCRepo(databaseURL);
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
        //Encode as body instead of into URL
        String name = request.queryParams("name");
        String loc = request.queryParams("location");
        String cEmail = request.queryParams("clubEmail");
        String desc = request.queryParams("description");
        boolean temp = clubRepo.createClub(name, loc, cEmail, desc);
        if(temp){   //Return a status code
            return "Club successfully created";
        }
        return "Club Created";
    }

    public String updateClub(Request request, Response resp){
        //TODO: How to retrieve data from the request in the API link
        //Return the club information in JSON format
        return "Not yet implemented";
    }

    public String deleteClub(Request request, Response resp){
        //TODO: Return using Status Code
        //If club exists, say delete failed
        boolean temp = clubRepo.deleteClub(request.params(":name"));
        if(temp) {
            return "Delete successful";
        }
        return "Delete failed";
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
        //TODO: Encode as body instead of into URL
        String id = request.queryParams("idNum");
        String evName = request.queryParams("evName");
        String loc = request.queryParams("location");
        String stTime = request.queryParams("stTime");
        String endTime = request.queryParams("endTime");
        String rep = request.queryParams("repeat");
        String cName = request.queryParams("clubName");
        boolean temp = eventRepo.createEvent(id, evName, loc, stTime, endTime, rep, cName);
        if(temp){   //TODO: Return a status code
            return "Club successfully created";
        }
        return "Club Created";
    }

    public String updateEvent(Request request, Response resp){
        //TODO
        return "Not yet implemented";
    }

    public String deleteEventGivenID(Request request, Response resp){
        //TODO
        //TODO: Return using Status Code
        //If club exists, say delete failed
        boolean temp = eventRepo.deleteEvent(request.params(":idNumber"));
        if(temp) {
            return "Delete successful";
        }
        return "Delete failed";
    }

    public String deleteEventGivenName(Request request, Response resp){
        //TODO: Return using Status Code
        //If club exists, say delete failed
        boolean temp = eventRepo.deleteEventGivenName(request.params(":name"));
        if(temp) {
            return "Delete successful";
        }
        return "Delete failed";
    }

    //////////////////////////////////////////////////////////////
    //                  User Commands                           //
    //////////////////////////////////////////////////////////////
    public String getAllUsers(Request request, Response resp){
        return userRepo.getUsers();
    }

    public String getUserGivenEmail(Request request, Response resp){
        return userRepo.getUserGivenEmail(request.params(":email"));
    }

    public String getUserGivenName(Request request, Response resp){
        return userRepo.getUserGivenName(request.params(":name"));
    }

    public String createUser(Request request, Response resp){
        return "Not yet implemented";
    }

    public String updateUser(Request request, Response resp){
        return "Not yet implemented";
    }

    public String deleteUser(Request request, Response resp){
        return "Not yet implemented";
    }

    public String deleteUserGivenName(Request request, Response resp){
        return "Not yet implemented";
    }

    //////////////////////////////////////////////////////////////
    //                   Interests Commands                     //
    //////////////////////////////////////////////////////////////
    public String getAllInterests(Request request, Response resp){
        return "Not yet implemented";
    }

    public String createInterests(Request request, Response resp){
        return "Not yet implemented";
    }

    public String deleteInterests(Request request, Response resp){
        return "Not yet implemented";
    }
}
