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
        delete("/clubs/:name", controller::deleteClub);
    }
}
