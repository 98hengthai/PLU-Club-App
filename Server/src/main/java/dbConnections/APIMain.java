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
        get("/user/:email", controller::getUserGivenEmail);
        get("/user/:name", controller::getUserGivenName);
        post("/user", controller::createUser);
        put("/user", controller::updateUser);
        delete("/user/email/:email", controller::deleteUser);
        delete("/user/name/:name", controller::deleteUserGivenName);

        //Interest Commands
        get("/interests", controller::getAllInterests);
        post("/interests", controller::createInterests);
        delete("/interests/:name", controller::deleteInterests);
    }
}