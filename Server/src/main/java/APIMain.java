import static spark.Spark.*;


public class APIMain {
    public static void main(String[] args){
        Controller controller = new Controller();

        //return JSONArray of userEmails
        get("/userEmails", controller.getAllUserEmails());

        //Clubs Commands
        get("/clubs", controller::getAllClubs);
        get("/clubs/:name", controller::getClub);
        post("/clubs",controller::createClub);
        put("/clubs/:name", controller::updateClub);
        delete("/clubs/:name/remove", controller::deleteClub);

        //Event Commands
        get("/event", controller::getAllEvents);
        get("/event/name/:name", controller::getEventGivenName);
        get("/event/id/:idNum", controller::getEventGivenID);
        get("/event/clubName/:cName", controller::getEventGivenClub);
        post("/event", controller::createEvent);
        put("/event/:idNum", controller::updateEvent);
        delete("/event/id/:idNum/remove", controller::deleteEventGivenID);
        delete("/event/name/:name/remove", controller::deleteEventGivenName);
    }
}
