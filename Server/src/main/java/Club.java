public class Club {
    private String name;
    private String location;
    private String clubEmail;
    private String description;

    //Constructors
    public Club(){
    }

    public Club(String name, String location, String clubEmail, String description){
        super();
        this.name = name;
        this.location = location;
        this.clubEmail = clubEmail;
        this.description = description;
    }

    //Getters and Setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getClubEmail() { return clubEmail; }

    public void setClubEmail(String clubEmail) { this.clubEmail = clubEmail; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String toString() {
        return new StringBuilder("Name: %s\n" +
                "Location: %s\n" +
                "Club Email: %s\n" +
                "Description: %s").toString();
    }
}
