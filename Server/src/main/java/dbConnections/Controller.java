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
    private UserEventsJDBCRepo userEventRepo;
    private ClubUsersJDBCRepo clubUsersRepo;
    private String databaseURL = References.ON_CAMPUS_DB_URL;

    public Controller(){
        userRepo = new UserJDBCRepo(databaseURL);
        clubRepo = new ClubJDBCRepo(databaseURL);
        eventRepo = new EventJDBCRepo(databaseURL);
        intRepo = new InterestJDBCRepo(databaseURL);
        clubIntRepo = new ClubInterestJDBCRepo(databaseURL);
        userIntRepo = new UserInterestJDBCRepo(databaseURL);
        userEventRepo = new UserEventsJDBCRepo(databaseURL);
        clubUsersRepo = new ClubUsersJDBCRepo(databaseURL);
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
        //TODO: Beautify the split process
        String[] strTemp = request.body().split("&");
        String name = strTemp[0].split("=")[1];
        String loc = strTemp[0].split("=")[1];
        String cEmail = strTemp[0].split("=")[1];
        String desc = strTemp[0].split("=")[1];
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
        String[] strTemp = request.body().split("&");
        String id = strTemp[0].split("=")[1];
        String evName = strTemp[1].split("=")[1];
        String loc = strTemp[2].split("=")[1];
        String stTime = strTemp[3].split("=")[1];
        String endTime = strTemp[4].split("=")[1];
        String rep = strTemp[5].split("=")[1];
        String cName = strTemp[6].split("=")[1];
        System.out.printf("%s, %s, %s, %s, %s, %s, %s", id, evName, loc, stTime, endTime, rep ,cName);
        boolean temp = eventRepo.createEvent(id, evName, loc, stTime, endTime, rep, cName);
        if(temp)
            return References.API_CODE_201;
        return References.ERROR_403_CREATE + " " + request.body();
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
        System.out.println(request.body());
        String[] strTemp = request.body().split("&");
        String email = strTemp[0].split("=")[1];
        String name = strTemp[1].split("=")[1];
        String gradYear = strTemp[2].split("=")[1];
        String isStudent = strTemp[3].split("=")[1];
        System.out.printf("%s, %s, %s, %s", email, name, gradYear, isStudent);

        boolean temp = userRepo.createUser(email, name, gradYear, isStudent);
        if(temp)
            return References.API_CODE_201;
        else
            return References.ERROR_403_CREATE + " " + request.body();
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
        String[] strTemp = request.body().split("&");
        String interest = strTemp[0].split("=")[1];

        boolean createSuccess = intRepo.createInterest(interest);
        if(createSuccess)
            return References.API_CODE_201;
        else
            return References.ERROR_403_CREATE + " " + request.body();
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
        String[] strTemp = request.body().split("&");
        String intName = strTemp[0].split("=")[1];
        String clubName = strTemp[1].split("=")[1];

        boolean createSuccess = clubIntRepo.createClubInterest(intName, clubName);

        if(createSuccess)
            return References.API_CODE_201;
        else
            return References.ERROR_403_CREATE;
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
        String[] strTemp = request.body().split("&");
        String userEmail = strTemp[0].split("=")[1];
        String intName = strTemp[1].split("=")[1];

        boolean createSuccess = userIntRepo.createUserInterest(userEmail, intName);

        if(createSuccess)
            return References.API_CODE_201;
        else
            return References.ERROR_403_CREATE;
    }

    public String deleteUserInterest(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    //////////////////////////////////////////////////////////////
    //                   UserEvents Commands                    //
    //////////////////////////////////////////////////////////////
    public String getAllUserEvents(Request request, Response resp){
        return userEventRepo.getAllUserEvents();
    }

    public String getUserEventsGivenIDNum(Request request, Response resp){
        return userEventRepo.getUserEventsGivenIDNum(request.params(":idNum"));
    }

    public String getUserEventsGivenUserEmail(Request request, Response resp){
        return userEventRepo.getUserEventsGivenUserEmail(request.params(":userEmail"));
    }

    public String getReply(Request request, Response resp){
        return userEventRepo.getReplyGivenUserAndEvent(request.params(":eventID"), request.params(":userEmail"));
    }

    public String createUserEvents(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    public String editUserEvents(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    public String deleteUserEvent(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    //////////////////////////////////////////////////////////////
    //                   ClubUsers Commands                     //
    //////////////////////////////////////////////////////////////
    public String getAllClubUsers(Request request, Response resp){
        return clubUsersRepo.getAllClubUsers();
    }

    public String getAllClubsGivenUser(Request request, Response resp){
        return clubUsersRepo.getAllClubsGivenUser(request.params(":userEmail"));
    }

    public String getAllClubsGivenClub(Request request, Response resp){
        return clubUsersRepo.getAllUsersGivenClub(request.params(":clubName"));
    }

    public String getClubUsersRole(Request request, Response resp){
        return clubUsersRepo.getRoleGivenNameAndClub(request.params(":clubName"), request.params(":userEmail"));
    }

    public String createClubUsers(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    public String editClubUsers(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

    public String deleteClubUser(Request request, Response resp){
        return References.ERROR_CODE_503;
    }

}
