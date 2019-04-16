package example.club.plu.navbarlayout.request.response;

public class ClubResponseWork {
    private String name;
    private String location;
    private String clubEmail;
    private String description;

    public ClubResponseWork() {
    }

    public ClubResponseWork(String name, String location, String clubEmail, String description) {
        this.name = name;
        this.location = location;
        this.clubEmail = clubEmail;
        this.description = description;
    }

    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //toString

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", clubEmail='" + clubEmail + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
