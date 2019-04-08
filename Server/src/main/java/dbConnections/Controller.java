package dbConnections;

import JDBCRepo.*;
import common.References;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Ref;

public class Controller {
    private UserJDBCRepo userRepo;
    private ClubJDBCRepo clubRepo;
    private EventJDBCRepo eventRepo;
    private InterestJDBCRepo intRepo;
    private ClubInterestJDBCRepo clubIntRepo;
    private UserInterestJDBCRepo userIntRepo;
    private String databaseURL = References.OFF_CAMPUS_DB_URL;

    public Controller(){
        userRepo = new UserJDBCRepo(databaseURL);
        clubRepo = new ClubJDBCRepo(databaseURL);
        eventRepo = new EventJDBCRepo(databaseURL);
        intRepo = new InterestJDBCRepo(databaseURL);
        clubIntRepo = new ClubInterestJDBCRepo(databaseURL);
        userIntRepo = new UserInterestJDBCRepo(databaseURL);
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
            return References.API_CODE_201;
        }
        return References.ERROR_403_CREATE;
    }

    public String updateClub(Request request, Response resp){
        //TODO: How to retrieve data from the request in the API link
        //Return the club information in JSON format
        return "Not yet implemented";
    }

    public String deleteClub(Request request, Response resp){
        //If club exists, say delete failed
        boolean temp = clubRepo.deleteClub(request.params(":name"));
        if(temp) {
            return References.API_CODE_200;
        }
        return References.ERROR_403_DELETE;
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
        if(temp)
            return References.API_CODE_201;
        return References.ERROR_403_CREATE;
    }

    public String updateEvent(Request request, Response resp){
        //TODO
        return "Not yet implemented";
    }

    public String deleteEventGivenID(Request request, Response resp){
        //If club exists, say delete failed
        boolean temp = eventRepo.deleteEvent(request.params(":idNumber"));
        if(temp) {
            return References.API_CODE_200;
        }
        return References.ERROR_403_DELETE;
    }

    public String deleteEventGivenName(Request request, Response resp){
        //If club exists, say delete failed
        boolean temp = eventRepo.deleteEventGivenName(request.params(":name"));
        if(temp) {
            return References.API_CODE_200;
        }
        return References.ERROR_403_DELETE;
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
        //TODO: Get parameters using body
        //TODO: Ask Wolff which parameter usage is best way to interpret data
        System.out.println(request.body());
        return References.ERROR_CODE_503;
    }

    public String updateUser(Request request, Response resp){
        //TODO
        return References.ERROR_CODE_503;
    }

    public String updateUserGivenName(Request request, Response resp){
        //TODO
        return References.ERROR_CODE_503;}

    public String deleteUser(Request request, Response resp){
        boolean temp = userRepo.deleteUser(request.params(":email"));
        if(temp)
            return References.API_CODE_200;
        return References.ERROR_403_DELETE;
    }

    public String deleteUserGivenName(Request request, Response resp){
        boolean temp = userRepo.deleteUser(request.params(":name"));
        if(temp)
            return References.API_CODE_200;
        return References.ERROR_403_DELETE;
    }

    //////////////////////////////////////////////////////////////
    //                   Interests Commands                     //
    //////////////////////////////////////////////////////////////
    public String getAllInterests(Request request, Response resp){
        return intRepo.getInterests();
    }

    public String createInterests(Request request, Response resp){
        //TODO: Get info from body
        return References.ERROR_CODE_503;
    }

    public String deleteInterests(Request request, Response resp){
        boolean temp = intRepo.deleteInterest(request.params(":name"));
        if(temp)
            return References.API_CODE_200;
        return References.ERROR_403_DELETE;
    }

    //////////////////////////////////////////////////////////////
    //                   ClubInterests Commands                 //
    //////////////////////////////////////////////////////////////
    public String getAllClubInterests(Request request, Response resp){
        return clubIntRepo.getAllClubInterests();
    }
    public String getClubInterestsGivenClub(Request request, Response resp){
        return clubIntRepo.getClubInterestsGivenClub(request.params(":club"));
    }

    public String getClubInterestsGivenInt(Request request, Response resp){
        return clubIntRepo.getClubInterestsGivenInterest(request.params(":interest"));
    }

    public String createClubInterest(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    public String deleteClubInterest(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    //////////////////////////////////////////////////////////////
    //                   UserInterests Commands                 //
    //////////////////////////////////////////////////////////////
    public String getAllUserInterests(Request request, Response resp){
        return userIntRepo.getAllUserInterests();
    }
    public String getUserInterestsGivenEmail(Request request, Response resp){
        return userIntRepo.getUserInterestsGivenUser(request.params(":userEmail"));
    }

    public String getUserInterestsGivenInt(Request request, Response resp){
        return userIntRepo.getUserInterestsGivenInterest(request.params(":interest"));
    }

    public String createUserInterest(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    public String deleteUserInterest(Request request, Response resp){
        return References.ERROR_CODE_503;
    }
}
