package entities;

public class ClubUsers {
    private String clubName;
    private String userEmail;
    private String role;

    public ClubUsers(){

    }

    public ClubUsers(String clubName, String userEmail, String role){
        this.clubName = clubName;
        this.userEmail = userEmail;
        this.role = role;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
