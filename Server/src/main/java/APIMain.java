import static spark.Spark.*;


public class APIMain {
    public static void main(String[] args){
        Controller controller = new Controller();

        //return JSONArray of userEmails
        get("/userEmails", controller.getAllUserEmails());

        //Clubs Commands
        get("/clubs", controller.getAllClubs());
        //TODO: Still need help from Wolff on this one
        //get("/clubs/:name", controller.getClub());

    }
}
