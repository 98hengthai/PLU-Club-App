package dbConnections;

import static spark.Spark.*;

public class APIMain {
    public static void main(String[] args){
        Controller controller = new Controller();

        //Clubs Commands
        get("/clubs", controller::getAllClubs);
        get("/clubs/:name", controller::getClub);
        post("/clubs", controller::createClub);
        put("/clubs/:name", controller::updateClub);
        delete("/clubs/:name", controller::deleteClub);  //remove remove

        //Event Commands
        get("/event", controller::getAllEvents);
        get("/event/name/:name", controller::getEventGivenName);
        get("/event/id/:idNum", controller::getEventGivenID);
        get("/event/clubName/:cName", controller::getEventGivenClub);
        post("/event", controller::createEvent);
        put("/event/:idNum", controller::updateEvent);
        delete("/event/id/:idNum", controller::deleteEventGivenID);
        delete("/event/name/:name", controller::deleteEventGivenName);

        //User Commands
        get("/user", controller::getAllUsers);
        get("/user/email/:email", controller::getUserGivenEmail);
        get("/user/name/:name", controller::getUserGivenName);
        post("/user", controller::createUser);
        put("/user", controller::updateUser);
        put("/user/name", controller::updateUserGivenName);
        delete("/user/email/:email", controller::deleteUser);
        delete("/user/name/:name", controller::deleteUserGivenName);

        //Interest Commands
        get("/interests", controller::getAllInterests);
        post("/interests", controller::createInterests);
        delete("/interests/:name", controller::deleteInterests);

        //ClubInterest Commands
        get("/clubInterests", controller::getAllClubInterests);
        get("/clubInterests/club/:club", controller::getClubInterestsGivenClub);  //Must specify club because no point in having a get for all entities
        get("/clubInterests/interest/:interest", controller::getClubInterestsGivenInt);
        post("/clubInterests", controller::createClubInterest);                //Requires club and Interest
        delete("/clubInterests/:club/:interest", controller::deleteClubInterest);  //Requires both club and interest to delete

        //UserInterest Commands
        get("/userInterests", controller::getAllUserInterests);
        get("/userInterests/user/:userEmail", controller::getUserInterestsGivenEmail);
        get("/userInterests/interest/:interest", controller::getUserInterestsGivenInt);
        post("/userInterests", controller::createUserInterest);                         //Requires User and Interest
        delete("/userInterests/:userEmail/:interest", controller::deleteUserInterest);  //Requires user and interest

        //UserEvents Commands
        get("/userEvents", controller::getAllUserEvents);
        get("/userEvents/eventID/:idNum", controller::getUserEventsGivenIDNum);
        get("/userEvents/userEmail/:userEmail", controller::getUserEventsGivenUserEmail);
        get("/userEvents/reply/:eventID/:userEmail", controller::getReply);
        post("/userEvents", controller::createUserEvents);
        put("/userEvents", controller::editUserEvents);
        delete("/userEvents/:eventID/:userEmail", controller::deleteUserEvent);

        //ClubUsers Commands
        get("/clubUsers", controller::getAllClubUsers);
        get("/clubUsers/userEmail/:userEmail", controller::getAllClubsGivenUser);
        get("/clubUsers/clubName/:clubName", controller::getAllClubsGivenClub);
        get("/clubUsers/role/:clubName/:userEmail", controller::getClubUsersRole);
        post("/clubUsers", controller::createClubUsers);
        put("/clubUsers", controller::editClubUsers);
        delete("/clubUsers/:clubName/:userEmail", controller::deleteClubUser);

        //For Homepage
        get("/homePageEvents/:userEmail", controller::getHomePageEventsForUser);
    }
}
